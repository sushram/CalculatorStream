package com.example.calculatorstream.configuration;

import com.example.calculatorstream.beans.CalculatorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * MessageDispathcher class will maintain list of subscribers and publish the calculations
 */
@Service
public class MessageDispather {

    @Autowired
    private CalcLogger calcLogger;
    private ConcurrentHashMap<String,WebSocketSession> subscriberList;

    public MessageDispather() {
        this.subscriberList = new ConcurrentHashMap<>();

    }

    public void addSubscriber(WebSocketSession session) {
        //pump latest 10 calculations.
        for (String message: calcLogger.getAllEntries()) {
            sendMessage(new TextMessage(message),session);
        }
        subscriberList.put(session.getId(), session);
    }

    public void removeSubscriber(WebSocketSession session) {
        subscriberList.remove(session.getId());
    }

    public void broadcastMessage(CalculatorMessage message) {
        String payload = message.toString();
        calcLogger.addCalculation(payload);
        broadcastMessage(new TextMessage(payload));
    }

    private void broadcastMessage(TextMessage message) {
        for (Map.Entry<String,WebSocketSession> webSocketSession : subscriberList.entrySet()) {
            sendMessage(message, webSocketSession.getValue());
        }
    }

    private void sendMessage(TextMessage message, WebSocketSession webSocketSession) {
        try {
            webSocketSession.sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
