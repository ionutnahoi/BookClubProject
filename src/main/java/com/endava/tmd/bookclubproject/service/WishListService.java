package com.endava.tmd.bookclubproject.service;

import com.endava.tmd.bookclubproject.entity.Book;
import com.endava.tmd.bookclubproject.entity.User;
import com.endava.tmd.bookclubproject.entity.WaitingList;
import com.endava.tmd.bookclubproject.entity.WishList;
import com.endava.tmd.bookclubproject.repository.WaitingListRepository;
import com.endava.tmd.bookclubproject.repository.WishListRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WishListService {
    @Autowired
    public final WishListRepository wishListRepository;
    public final UserService userService;
    public final BookService bookService;

    public List<WishList> getAll() {
        return wishListRepository.findAll();
    }

    public void addWishList(Long idUser, String title) {
        Book book = bookService.getBookByTitle(title);
        User user = userService.getbyid(idUser).get();
        WishList wishList = new WishList();
        wishList.setBook(book);
        wishList.setUser(user);
        wishListRepository.save(wishList);
    }

    public void delete(Long idUser, String title) {
        WishList wishList = wishListRepository.getWishListByUserAndBook(idUser, title);
        wishListRepository.deleteById(wishList.getId());
    }

    public List<Book> getAllAvailable() {
        List<WishList> wishListList = wishListRepository.findAll();
        List<Book> bookList = bookService.getAll();
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : bookList) {
            boolean ok = true;
            for (WishList wishList : wishListList) {
                if (book.getId() == wishList.getBook().getId()) {
                    ok = false;
                }
            }
            if (ok) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public List<Book> myWishList(Long idUser) {
        return wishListRepository.getWishListByUserID(idUser);
    }
}
