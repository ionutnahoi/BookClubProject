package com.endava.tmd.bookclubproject.controller;

import com.endava.tmd.bookclubproject.entity.Book;
import com.endava.tmd.bookclubproject.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("book")
@CrossOrigin
public class BookController {
    @Autowired
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Object getbyid(@PathVariable Long id) {
        return bookService.getbyid(id).isPresent() ? bookService.getbyid(id).get() : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public void addBook(@RequestBody final Book book) {
        bookService.addBook(book);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        bookService.deleteById(id);
    }

    @PutMapping
    public void update(@RequestParam Book book) {
        bookService.update(book);
    }

    @GetMapping(value = "/title")
    public Book getBookByTitle(@RequestParam(value = "title") String title) {
        return bookService.getBookByTitle(title);
    }

    @GetMapping(value = "/available")
    public List<Book> getAvailableBooks(@RequestParam(value = "id") long id) {
        return bookService.getAvailableBooks(id);
    }

    @GetMapping(value = "/unavailable")
    public List<Book> getUnavailableBooks(@RequestParam(value = "id") long id) {
        return bookService.getUnavailableBooks(id);
    }
    @GetMapping(value = "myBooks")
    public List<Book>getMyBooks(@RequestParam(value = "idOwner")long id){
        return bookService.getMyBooks(id);
    }
    @GetMapping(value = "/mywaitinglist")
    public List<Book>getBooks(@RequestParam(value = "id") long idUser){
        return bookService.getWaitingList(idUser);
    }


}
