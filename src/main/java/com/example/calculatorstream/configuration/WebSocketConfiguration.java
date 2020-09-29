package com.example.calculatorstream.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    public static final String CALCULATOR_STREAM_ENDPOINT = "/calcstream";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getWebSocketHandler(), CALCULATOR_STREAM_ENDPOINT).setAllowedOrigins("*");
    }

   @Bean
    public CalculatorWebSocketHandler getWebSocketHandler() {
        return new CalculatorWebSocketHandler();
    }
}
