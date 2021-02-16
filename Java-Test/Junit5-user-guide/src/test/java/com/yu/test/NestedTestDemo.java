package com.yu.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("스택")
public class NestedTestDemo {
    Stack<Object> stack;

    @Test
    @DisplayName("스택이 new 되었나")
    void isInstantiatedWithNew() {
        new Stack<>();
    }

    @Nested
    @DisplayName("스택이 new 됨")
    class WhenNew {
        
        // BeforeEach 메소드는 하위 중첩 테스트 트리 전체에 영향을 준다.
        // setUp 코드이므로 하위 중첩 테스트만 실행해도 이곳이 실행 됨(테스트 성공)
        @BeforeEach
        void createNewStack() {
            System.out.println("스택을 new!");
            stack = new Stack<>();
        }

        @Test
        @DisplayName("비어 있나")
        void isEmpty() {
            assertTrue(stack.isEmpty());
        }

        @Test
        @DisplayName("pop 하면 EmptyStackException 발생")
        void throwsExceptionPopped() {
            assertThrows(EmptyStackException.class, stack::pop);
        }

        @Test
        @DisplayName("peek 하면 EmptyStackException 발생")
        void throwsExceptionPeeked() {
            assertThrows(EmptyStackException.class, stack:: peek);
        }

        @Nested
        @DisplayName("객체 push 후")
        class AfterPushing {
            String theElem = "껄껄맨";

            @BeforeEach
            void pushAnElement() {
                stack.push(theElem);
            }

            @Test
            @DisplayName("이제 비어있지 않음")
            void notEmtpy() {
                assertFalse(stack.isEmpty());
            }

            @Test
            @DisplayName("pop 하면 원소를 리턴하고 empty가 됨")
            void doPop() {
                assertEquals(theElem, stack.pop());
                assertTrue(stack.isEmpty());
            }

            @Test
            @DisplayName("peek 하면 원소를 리턴하지만 empty가 아님")
            void doPeek() {
                assertEquals(theElem, stack.peek());
                assertFalse(stack.isEmpty());
            }
        }
    }
}
