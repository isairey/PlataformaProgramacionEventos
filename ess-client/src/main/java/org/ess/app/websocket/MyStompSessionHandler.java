package org.ess.app.websocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    private final Logger logger = LogManager.getLogger(MyStompSessionHandler.class);

    public static StompSession session;

    @Override
    public void afterConnected(StompSession session, @NotNull StompHeaders connectedHeaders) {
        MyStompSessionHandler.session = session;
        logger.info("New session established : " + session.getSessionId());
//        session.subscribe("/topic/messages", this);
        session.subscribe("/message", this);
        logger.info("Subscribed to /topic/messages");
//        session.send("/message", getSampleMessage());
        session.send("/app/message", getSampleMessage());
//        session.send("/topic/messages", getSampleMessage());
        logger.info("Message sent to websocket server");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, @NotNull StompHeaders headers, byte @NotNull [] payload, @NotNull Throwable exception) {
        logger.error("Got an exception", exception);
    }

    @Override
    public @NotNull Type getPayloadType(StompHeaders headers) {
        logger.info(headers.entrySet());
        return Message.class;
    }

    @Override
    public void handleFrame(@NotNull StompHeaders headers, Object payload) {
        logger.info("Message: {}", payload);
        Message msg = (Message) payload;
        logger.info("Received : " + msg.getText() + " from : " + msg.getFrom());
    }

    /**
     * A sample message instance.
     *
     * @return instance of <code>Message</code>
     */
    private Message getSampleMessage() {
        Message msg = new Message();
        msg.setFrom("Nicky");
        msg.setText("Howdy!!");
        return msg;
    }
}
