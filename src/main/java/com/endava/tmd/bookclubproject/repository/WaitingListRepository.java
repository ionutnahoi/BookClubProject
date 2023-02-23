package com.endava.tmd.bookclubproject.repository;

import com.endava.tmd.bookclubproject.entity.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {

    @Query("SELECT b FROM WaitingList b where  b.book.id=:idBook and b.user.id=:idUser")
    WaitingList findByUserId(Long idUser, Long idBook);
}
