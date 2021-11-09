package com.example.examples.deliever;

import com.example.examples.delievery.Deliver;
import com.example.examples.delievery.IVehicle;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DeliverTest {

    @DisplayName("배달에 대한 케이스")
    @Test
    void name() {
        // given
        IVehicle mockVehicle = Mockito.mock(IVehicle.class);
        Mockito.when(mockVehicle.deliver()).thenReturn(true);
        Deliver deliver = new Deliver(mockVehicle);

        // when
        boolean result = deliver.makeADelivery();

        // then
        Assertions.assertThat(result).isTrue();
    }

}
