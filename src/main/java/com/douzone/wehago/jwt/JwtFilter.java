package com.douzone.wehago.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Security;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    // AUTHORIZATION_HEADER 이건, 리액트에서 axios 로 요청을 보낼떄 토큰 값을 보내면서 같이 작성해 보내야됨.
    // 약속임.. "Authorization" 이 값은 바뀌어도 됨.
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // request에서 토큰 뽑아오기
        String jwt = resolveToken(request);

        // 적합한 토큰이라면 authentication 에 추가
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            // 토큰으로부터 Authenication 객체 얻어오기
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            // 받아온 Authentication 객체를 Security Context Holder 에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    // 헤더에 Authorization 가 없거나 Bearer 로 시작하지 않으면 null을 반환한다.
    private String resolveToken(HttpServletRequest request) {
        // Authorization 헤더에서 토큰 추출
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        // 접두사 Authorization 분리
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
