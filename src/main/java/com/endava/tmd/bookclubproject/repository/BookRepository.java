package com.endava.tmd.bookclubproject.repository;

import com.endava.tmd.bookclubproject.entity.Book;
import com.endava.tmd.bookclubproject.entity.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b from Book b where b.title=:bookTitle")
    Book getBookByTitle(String bookTitle);

    @Query("SELECT b from Book b  where b.id NOT IN(SELECT borrowed_book from Borrow )")
    List<Book> getAvailableBooks();

    @Query("SELECT b.id from Book b where b.title=:title")
    Long getIdByTitle(String title);

    @Query("SELECT b from Book  b where b.id in(SELECT borrowed_book from Borrow )")
    List<Book> getUnavailableBooks();

    @Query("SELECT b.book from BookOwner b where b.user.id=:id ")
    List<Book> getMyBooks(long id);

    @Query("SELECT b.book from WaitingList b where b.user.id=:idUser")
    List<Book> getWaitingList(long idUser);
}
