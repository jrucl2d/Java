package com.example;

import java.util.Optional;

public class Twooter {

    Optional<SenderEndPoint> onLogon(
            String userId, String password, ReceiverEndPoint receiver
    ) {
        return Optional.empty();
    }
}
