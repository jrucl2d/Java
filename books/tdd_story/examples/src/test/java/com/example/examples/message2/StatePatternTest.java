package com.example.examples.message2;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StatePatternTest {
    @Test
    void name() {
        // given
        State state3G = new State3G();
        MessageSender messageSender = new MessageSender(state3G);
        State stateWifi = new StateWifi();
        MessageSender messageSender1 = new MessageSender(stateWifi);

        // when
        boolean result3G = messageSender.sendText();
        boolean resultWifi = messageSender1.sendText();

        // then
        Assertions.assertThat(result3G).isTrue();
        Assertions.assertThat(resultWifi).isTrue();
    }

    private static class MessageSender {
        private State state; // 상태에 따른 행동을 캡슐화한다.
        public MessageSender(State state) {
            this.state = state;
        }

        public boolean sendText() {
            return state.sendText();
        }

        public boolean sendPhoto() {
            return state.sendPhoto();
        }

        public boolean sendBigFile() {
            return state.sendBigFile();
        }
    }

    private interface State {
        boolean sendText();
        boolean sendPhoto();
        boolean sendBigFile();
    }

    private static class State3G implements State {

        @Override
        public boolean sendText()
        {
            return true;
        }

        @Override
        public boolean sendPhoto()
        {
            return true;
        }

        @Override
        public boolean sendBigFile()
        {
            return true;
        }
    }

    private static class StateWifi implements State {

        @Override
        public boolean sendText()
        {
            return true;
        }

        @Override
        public boolean sendPhoto()
        {
            return true;
        }

        @Override
        public boolean sendBigFile()
        {
            return true;
        }
    }
}
