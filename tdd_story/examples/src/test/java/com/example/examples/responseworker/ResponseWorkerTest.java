package com.example.examples.responseworker;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ResponseWorkerTest {
    @Test
    void name() {
        // given
        String responseData = "XML Data";
        ResponseWorker responseParser = new ResponseWorker(responseData);
        Parser parser = Mockito.mock(XMLParser.class);
        Mockito.when(parser.parsing(responseData)).thenReturn(new Object());

        // when
        Object parsedObject = responseParser.parse(parser);

        // then
        Assertions.assertThat(parsedObject).isNotNull();
        Mockito.verify(parser).parsing(responseData); // 전략패턴은 행위에 대한 검증이 필요. parsing 메소드의 호출 여부
    }

    private static class ResponseWorker {
        private String responseData;

        public ResponseWorker(String responseData) {
            this.responseData = responseData;
        }

        public Object parse(Parser parser) {
            Object parsedObject = parser.parsing(this.responseData);
            // 파싱된 Object 처리 로직
            return parsedObject;
        }
    }

    private interface Parser {
        Object parsing(String data);
    }

    public class JsonParser implements Parser {

        @Override
        public Object parsing(String data) {
            // JSON 파싱
            return null;
        }
    }

    public class XMLParser implements Parser {

        @Override
        public Object parsing(String data) {
            // XML 파싱
            return null;
        }
    }
}
