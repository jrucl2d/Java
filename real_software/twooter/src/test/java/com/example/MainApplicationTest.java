package com.example;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MainApplicationTest {

    @Test
    void shouldBeAbleToAuthenticateUser() {
        Twooter twooter = new Twooter();
        ReceiverEndPoint receiverEndPoint = new ReceiverEndPoint();
        Optional<SenderEndPoint> endPoint = twooter.onLogon("userID", "bad password", receiverEndPoint);
        assertThat(endPoint.isPresent()).isFalse();
    }
}
