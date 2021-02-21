package com.yu.book.domain;

// 단위 테스트 : DB 관련 Bean이 IoC에 등록되면 됨

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // 가짜 DB. Replace.None이면 실제 DB
@DataJpaTest // DB 관련된 객체만 메모리에 올라감. Repository들을 다 IoC에 등록해줌.
public class BookRepositoryUnitTest {

    @Autowired
    private BookRepository bookRepository;
}
