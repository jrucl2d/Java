package com.example;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class BusinessRoleEngineTest {

    @Test
    void shouldPerformAnActionWithFacts() {
        var mockAction = mock(Action.class);
        var mockFacts = mock(Facts.class);
        var businessRoleEngine = new BusinessRoleEngine(mockFacts);

        businessRoleEngine.addAction(mockAction);
        businessRoleEngine.run();

        verify(mockAction).execute(mockFacts);
    }
}