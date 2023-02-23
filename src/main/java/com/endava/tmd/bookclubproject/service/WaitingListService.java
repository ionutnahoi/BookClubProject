package com.endava.tmd.bookclubproject.service;

import com.endava.tmd.bookclubproject.entity.Book;
import com.endava.tmd.bookclubproject.entity.User;
import com.endava.tmd.bookclubproject.entity.WaitingList;
import com.endava.tmd.bookclubproject.repository.WaitingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaitingListService {
    @Autowired
    public final WaitingListRepository waitingListRepository;
    public final UserService userService;
    public final BookService bookService;

    public WaitingListService(WaitingListRepository waitingListRepository, UserService userService, BookService bookService) {
        this.waitingListRepository = waitingListRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    public List<WaitingList> getAll() {
        return waitingListRepository.findAll();
    }

    public void addWaitingList(Long idUser, String title) {
        Book book = bookService.getBookByTitle(title);
        User user = userService.getbyid(idUser).get();
        WaitingList waitingList = new WaitingList();
        waitingList.setUser(user);
        waitingList.setBook(book);
        waitingListRepository.save(waitingList);
    }

    public void delete(Long idUser, String title) {
        Book book=bookService.getBookByTitle(title);
        WaitingList waitingList = waitingListRepository.findByUserId(idUser, book.getId());
        waitingListRepository.deleteById(waitingList.getId());
    }
}
