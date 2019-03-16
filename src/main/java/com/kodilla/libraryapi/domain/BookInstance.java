package com.kodilla.libraryapi.domain;

import com.kodilla.libraryapi.enumerics.BookInstanceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="BOOK_INSTANCES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookInstance {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @NotNull
    @Column(name="BOOK_ID")
    private long bookId;

    @NotNull
    @Column(name="STATUS")
    private BookInstanceStatus status;

    @NotNull
    @Column(name="AVAILABLE_FOR_RENT")
    private boolean isAvailableForRent;

}
