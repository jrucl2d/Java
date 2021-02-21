package com.yu.book.service;

import com.yu.book.domain.Book;
import com.yu.book.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    @Transactional
    public Book save(Book book){
        return bookRepository.save(book);
    }
    @Transactional(readOnly = true)
    public Book getOne(Long id){
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요."));
    }
    @Transactional(readOnly = true)
    public List<Book> getAll(){
        return bookRepository.findAll();
    }
    @Transactional
    public Book update(Long id, Book book){
        Book findBook = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요."));
        findBook.setTitle(book.getTitle());
        findBook.setAuthor(book.getAuthor());
        return findBook;
    }
    @Transactional
    public String delete(Long id){
        bookRepository.deleteById(id);
        return "OK";
    }
}
