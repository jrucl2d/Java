package com.example.examples.delievery;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Deliver {
    private IVehicle vehicle;

    public Deliver(IVehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean makeADelivery() {
        // TODO 배달을 위한 준비
        return vehicle.deliver();
    }
}
