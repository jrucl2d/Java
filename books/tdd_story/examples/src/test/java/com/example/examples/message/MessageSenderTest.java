package com.example.examples.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MessageSenderTest {
    @Test
    void name() {
        // given
        MessageSender messageSender = new MessageSender();
        MessageFactory.MessageType messageType = MessageFactory.MessageType.MESSAGE_TYPE_SMS;
        String text = "Message for SMS";

        // when
        boolean result = messageSender.send(messageType, text);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void name1() {
        // given
        MessageFactory.MessageType messageType = MessageFactory.MessageType.MESSAGE_TYPE_SMS;

        // when
        Message message = MessageFactory.generateMessage(messageType);

        // then
        Assertions.assertThat(message).isNotNull();
        Assertions.assertThat(message).isInstanceOf(SMSMessage.class); // 생성 관련 패턴이므로 생성을 확인
    }

    private static class MessageSender {
        public boolean send(MessageFactory.MessageType messageType, String text) {
            Message message = MessageFactory.generateMessage(messageType);
            return dispatch(message);
        }

        private boolean dispatch(Message message) {
            // 작업
            return true;
        }
    }

    private static class MessageFactory {
        public enum MessageType {
            MESSAGE_TYPE_SMS,
            MESSAGE_TYPE_MMS,
            MESSAGE_TYPE_PUSH,
            MESSAGE_TYPE_EMAIL
        }

        public static Message generateMessage(MessageType messageType) {
            Message message = null;
            switch (messageType) {
                case MESSAGE_TYPE_SMS:
                    message = new SMSMessage();
                    break;
                case MESSAGE_TYPE_MMS:
                    message = new MMSMessage();
                    break;
                case MESSAGE_TYPE_PUSH:
                    message = new PushMessage();
                    break;
                case MESSAGE_TYPE_EMAIL:
                    message = new EmailMessage();
                    break;
                default:
                    break;
            }
            return message;
        }
    }

    private static class Message {}
    private static class SMSMessage extends Message {}
    private static class MMSMessage extends Message {}
    private static class PushMessage extends Message {}
    private static class EmailMessage extends Message {}
}
