package com.endava.tmd.bookclubproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "wishlist")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, unique = true)

    private Long id;
    @JoinColumn()
    @ManyToOne
    private User user;
    @JoinColumn()
    @ManyToOne
    private Book book;

}
