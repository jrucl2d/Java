package com.yu.book.web;

import com.yu.book.domain.Book;
import com.yu.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BookController {

    private final BookService bookService;

    @GetMapping("/book")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK); // 200
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getOne(id), HttpStatus.OK); // 200
    }

    @PostMapping("/book")
    public ResponseEntity<?> save(@RequestBody Book book) {
        Book savedBook = bookService.save(book);
        return new ResponseEntity<Book>(savedBook, HttpStatus.CREATED); // 201
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Book book) {
        Book updatedBook = bookService.update(id, book);
        return new ResponseEntity<Book>(updatedBook, HttpStatus.OK); // 200
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.delete(id), HttpStatus.OK); // 200
    }
}
