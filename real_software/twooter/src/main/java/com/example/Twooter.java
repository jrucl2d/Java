package com.example;

import java.util.Optional;

public class Twooter {

    public Optional<SenderEndPoint> onLogon(
            String userId, String password, ReceiverEndPoint receiver
    ) {
        return Optional.empty();
    }

    public FollowStatus onFollow(String userId) {
        return FollowStatus.SUCCESS;
    }
}
