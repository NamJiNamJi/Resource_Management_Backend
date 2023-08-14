package com.douzone.wehago.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {

    // Spring에서 Bean은 싱글톤으로 관리되지만,
    // @ServerEndPoint 클래스는 WebSocket 이 생성될 때마다 인스턴스가 생성된다.
    // 때문에 이를 초기화 해주는 클래스가 필요하다.
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
