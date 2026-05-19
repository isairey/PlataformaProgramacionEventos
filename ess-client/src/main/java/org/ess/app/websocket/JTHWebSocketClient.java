package org.ess.app.websocket;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class JTHWebSocketClient {

    public static void connect() {

//        https://stackoverflow.com/questions/36456458/simpmessagingtemplate-not-sending-messages-in-spring-boot

        String serverUrl = "ws://localhost:8080/websocket"; // Replace with your server's WebSocket URL
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);

        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        stompClient.connectAsync(serverUrl, sessionHandler);
//        new Scanner(System.in).nextLine(); // Don't close immediately.
    }
}
