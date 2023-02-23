package com.endava.tmd.bookclubproject.repository;

import com.endava.tmd.bookclubproject.entity.BookOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookOwnerRepository extends JpaRepository<BookOwner, Long> {
    @Query("select b.user.id from BookOwner b where b.book.id=:id ")
    Long getBookOwnerByID(Long id);
}
