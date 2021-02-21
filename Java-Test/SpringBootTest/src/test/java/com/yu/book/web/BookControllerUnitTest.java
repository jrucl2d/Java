package com.yu.book.web;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yu.book.domain.Book;
import com.yu.book.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 단위 테스트 : Controller 관련 로직만 테스트(Filter, ControllerAdvice)
@Slf4j
@WebMvcTest // 스프링과 연계된 테스트에 필요
public class BookControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // Controller에서 사용하는 BookService가 가짜로 IoC 환경에 등록됨
    private BookService bookService;

    // BDDMockito 패턴 : given, when, then
    @Test
    void save_테스트() throws Exception {
        // given
        Book mockBook = new Book(null, "aa", "aa1");
        Book resBook = new Book(1L, "aa", "aa1");
        String content = new ObjectMapper().writeValueAsString(mockBook);
        when(bookService.save(mockBook)).thenReturn(resBook);

        // when
        ResultActions perform = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .accept(MediaType.APPLICATION_JSON));

        // then
        perform.andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void findAll_테스트() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "aa", "a1"));
        books.add(new Book(2L, "bb", "b2"));
        when(bookService.getAll()).thenReturn(books);

        ResultActions perform = mockMvc.perform(get("/book")
                .accept(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.[0].title").value("aa"))
                .andDo(print());
    }

    @Test
    void findById_테스트() throws Exception {
        Book book = new Book(1L, "aa", "a1");
        when(bookService.getOne(1L)).thenReturn(book);

        Long id = 1L;
        ResultActions perform = mockMvc.perform(get("/book/{id}", id)
                .accept(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("aa"))
                .andExpect(jsonPath("$.author").value("a1"))
                .andDo(print());
    }

    @Test
    void update_테스트() throws Exception {
        Long id = 1L;
        Book book = new Book(null, "aa", "a1");
        String content = new ObjectMapper().writeValueAsString(book);
        when(bookService.save(book)).thenReturn(new Book(1L, "aa", "a1"));

        ResultActions perform = mockMvc.perform(put("/book/{id}", id)
        .contentType(MediaType.APPLICATION_JSON).content(content));

        perform.andDo(print());
    }

    @Test
    void delete_테스트() throws Exception{
        Long id = 1L;
        when(bookService.delete(id)).thenReturn("ok");

        ResultActions perform = mockMvc.perform(delete("/book/{id}", id)
            .accept(MediaType.TEXT_PLAIN));

        perform.andExpect(status().isOk())
                .andDo(print());

        MvcResult requestResult = perform.andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertThat(result).isEqualTo("ok");
    }
}
