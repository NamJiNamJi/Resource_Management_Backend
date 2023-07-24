package com.douzone.wehago.config;

import com.douzone.wehago.jwt.JwtFilter;
import com.douzone.wehago.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
// springboot 에서 이미 default 로 SecurityFilterChain 을 등록하는데,
// @Bean 을 통해 또다시 객체 주입을 시도하게 되면 둘 중 하나만 이용하라고 경고를 보냄
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    // 패스워드 암호화 security BCryptPassword
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // CORS 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        // origin 현재는 port 를 "*" 로 전부 다 열어주었고 추후에 변경시
        // Arrays.asList("<http://localhost:3000>") 같이 포트를 넣어 열어주자.
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList("*"));
        // 허용할 method "*" 로 "GET", "POST", "PUT", "DELETE", "OPTIONS" 현재 다 열어놓음
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        // 허용할 header
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        // 응답요청으로 허용할 header
        corsConfiguration.setExposedHeaders(Arrays.asList("Authorization", "RefreshToken", "Access_Control-Allow-Origin"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

    // http security 설정
    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER) // Bean 충돌 방지
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors().configurationSource(corsConfigurationSource());
        http.csrf().disable(); // jwt 토큰 사용으로 구현한다고 해서 disable 처리
        http.headers().frameOptions().sameOrigin(); // 동일 도메인에서는 iframe 접근이 가능하도록 함

        http
                .httpBasic().disable(); // /login 으로 api 요청시 loadUserName() 이 동작하지 않도록 함.

        http
                .formLogin().disable()
                // jwt Filter 설정
                .addFilterBefore(new JwtFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class)

                // session 사용 안하는 설정
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 스프링 시큐리티가 세션을 생성하지도 않고 기존것을 사용하지도 않는 방식 (jwt 토큰 방식에서 사용 (STATELESS))


        // 아직 인증없이 사용 가능한 api 가 나오지 않아서 추가적인 http 설정을 잠시 멈춤

                .and()
                .authorizeRequests() // 인증없이 사용가능한 api
                .antMatchers("/*").permitAll();
//                .antMatchers("/api/user/login").permitAll()
//                .antMatchers("/api/user/signup").permitAll() // api 전부 열어둠
//
//                .anyRequest().authenticated();

        return http.build();
    }
}
