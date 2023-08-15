package com.douzone.wehago.webSocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/*
    server-client 1:n 관계
    한 서버에 여러 클라이언트가 접속할 수 있으므로 여러 클라이언트가 전송한 메시지를 받아 처리할 Handler 필요
    클라이언트로 받은 메시지를 콘솔에 출력하고 클라이언트에게 "웹소켓 테스트" 메시지를 전송함
 */
@Slf4j
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

        String input = message.getPayload();
        log.info(input);
        TextMessage textMessage = new TextMessage("웹소켓 테스트");
        session.sendMessage(textMessage);
    }
}
