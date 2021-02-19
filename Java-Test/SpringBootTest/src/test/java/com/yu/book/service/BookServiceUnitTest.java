package com.yu.book.service;

import com.yu.book.domain.BookRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

// 단위 테스트 : 서비스 관련 것들만 메모리에 등록되면 됨(Repository만)
// 근데 Repository를 실제 메모리에 올리면 단위가 아닌 통합 테스트이다.
// 따라서 가짜 객체를 생성하기 위해 Mockito를 사용
@ExtendWith(MockitoExtension.class)
public class BookServiceUnitTest {

    @Mock // Spring IoC가 아닌 Mockito 환경에 올라감
    private BookRepository bookRepository;

    @InjectMocks // 이 객체가 생성될 때 해당 파일에 @Mock으로 등록된 모든 객체를 주입받음
    private BookService bookService;


}
