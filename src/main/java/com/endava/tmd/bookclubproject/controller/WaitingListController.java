package com.endava.tmd.bookclubproject.controller;

import com.endava.tmd.bookclubproject.entity.WaitingList;
import com.endava.tmd.bookclubproject.service.BookService;
import com.endava.tmd.bookclubproject.service.UserService;
import com.endava.tmd.bookclubproject.service.WaitingListService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("waitinglist")
@CrossOrigin
public class WaitingListController {
    private final WaitingListService waitingListService;
    private final BookService bookService;
    private final UserService userService;

    public WaitingListController(WaitingListService waitingListService, BookService bookService, UserService userService) {
        this.waitingListService = waitingListService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @PostMapping("/waiting")
    public void addWaiting(@RequestParam(value = "idUserWhoBorrow") long idUser, @RequestParam(value = "title") String title) {
        waitingListService.addWaitingList(idUser, title);
    }

    @DeleteMapping("/delete")
    public void deleteWaiting(@RequestParam(value = "id") long idUser, @RequestParam(value = "title") String title) {
        waitingListService.delete(idUser,title);
    }
}

