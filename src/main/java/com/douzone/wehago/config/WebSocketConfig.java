package com.douzone.wehago.config;

import com.douzone.wehago.webSocket.WebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;


/*
    WebSocketHandler 활성화를 위한 Configuration
    @EnableWbSocket : WebSocket 활성화
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketHandler, "/ws/check")
                .setAllowedOrigins("*");

    }
}
