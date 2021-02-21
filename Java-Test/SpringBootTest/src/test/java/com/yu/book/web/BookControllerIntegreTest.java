package com.yu.book.web;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yu.book.domain.Book;
import com.yu.book.domain.BookRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 통합 테스트 : 모든 Bean들을 똑같이 IoC에 올리고 테스트
/**
 * SpringBootTest.WebEnvironment.MOCK : 실제 톰캣이 아닌 가짜 톰캣
 * SpringBootTest.WebEnvironment.RANDOM_PORT : 실제 톰캣으로 테스트
 */
@Transactional
@AutoConfigureMockMvc // MockMvc를 IoC에 등록해줌
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // 모든 것들이 메모리에 다 올라옴
public class BookControllerIntegreTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void init() {
        entityManager.createNativeQuery("alter table book alter column id restart with 1").executeUpdate();
    }

    @Test
    void save_테스트() throws Exception {
        Book mockBook = new Book(null, "aa", "aa1");
        String content = new ObjectMapper().writeValueAsString(mockBook);

        ResultActions perform = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON));
        perform.andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("aa"))
                .andDo(print());
    }

    @Test
    public void findAll_테스트() throws Exception {
        List<Book> books = new ArrayList<>();
        // 이 테스트만 실행하면 id가 1번부터 생기지만 위 테스트와 같이 진행하면 auto increment 때문에 2부터 시작한다 -> BeforeEach로 초기화
        books.add(new Book(null, "aa", "a1"));
        books.add(new Book(null, "bb", "b2"));

        bookRepository.saveAll(books);

        ResultActions perform = mockMvc.perform(get("/book")
                .accept(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.[0].title").value("aa"))
                .andDo(print());
    }

    @Test
    void findById_테스트() throws Exception {
        Book book = new Book(null, "aa", "a1");
        bookRepository.save(book);

        ResultActions perform = mockMvc.perform(get("/book/1")
                .accept(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("aa"))
                .andExpect(jsonPath("$.author").value("a1"))
                .andDo(print());
    }
}
