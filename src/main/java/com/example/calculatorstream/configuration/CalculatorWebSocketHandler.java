package com.example.calculatorstream.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * WebScoketHandler to accept incoming websocket requests
 */
public class CalculatorWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private MessageDispather dispatcher;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
       dispatcher.addSubscriber(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        dispatcher.removeSubscriber(session);
    }
}
