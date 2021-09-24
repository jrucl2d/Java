package com.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class BusinessRoleEngineTest {
    @Test
    void shouldHaveNoRulesInitially() {
        final BusinessRoleEngine businessRoleEngine = new BusinessRoleEngine();

        assertThat(businessRoleEngine.count()).isZero();
    }

    @Test
    void shouldAddTowActions() {
        final BusinessRoleEngine businessRoleEngine = new BusinessRoleEngine();

        businessRoleEngine.addAction(() -> {});
        businessRoleEngine.addAction(() -> {});

        assertThat(businessRoleEngine.count()).isEqualTo(2);
    }

    @Test
    void shouldExecuteOneAction() {
        final BusinessRoleEngine businessRoleEngine = new BusinessRoleEngine();
        final Action mockAction = mock(Action.class);

        businessRoleEngine.addAction(mockAction);
        businessRoleEngine.run();

        verify(mockAction, times(1)).perform();
    }
}