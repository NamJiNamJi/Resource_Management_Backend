package com.douzone.wehago.jwt;

import com.douzone.wehago.domain.User;
import com.douzone.wehago.security.PrincipalDetailsService;
import com.douzone.wehago.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class TokenProvider {

    private static final String BEARER_TYPE = "Bearer";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;

    private final Key key;

    private final UserDetailsService userDetailsService;

    // todo :: refreshToken 은 따로 만들지 않고 AccessToken 만 만듬
    public TokenProvider(@Value("${jwt.secret}") String secretKey,
                         PrincipalDetailsService principalDetailsService) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.userDetailsService = principalDetailsService;
    }

    // todo :: 토큰 생성
    public TokenDTO generateTokenDto(Authentication authentication) {

        User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();

        long now = (new Date().getTime());

        // jwt headers
        Map<String, Object> headers = new HashMap<>();
        headers.put("alg", "HS256");
        headers.put("typ", "Bearer");

        // claims token 본문 커스텀
        Map<String ,String> claims = new HashMap<>();
        claims.put("userId", user.getUserId()); // 유저 아이디
        claims.put("userName", user.getUserName()); // 유저 이름

        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);

        // accessToken 생성
        String accessToken = Jwts.builder()
                .setHeader(headers)
                .setClaims(claims)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return TokenDTO.builder()
                .authorization(BEARER_TYPE + " " + accessToken)
                .build();
    }

    // 현재 사용자 정보 가져오기
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);
        String userId = (String) claims.get("userId");
        UserDetails principal = userDetailsService.loadUserByUsername(userId);
        return new UsernamePasswordAuthenticationToken(principal, "", null);
    }

    // jwt key 를 가지고 accessToken 를 복호화함.
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    // 현재 인증된 User 의 객체를 반환하거나 인증되지 않은 사용자나 익명 사용자인 경우 null 반환
    public User getUserFromAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return null;
        }
        return ((UserDetailsImpl) authentication.getPrincipal()).getUser();
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT signature, 유효하지 않은 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token, 만료된 JWT 입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token, 지원되지 않는 JWT 입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT claims is empty, 잘못된 JWT 입니다.");
        }
        return false;
    }
}
