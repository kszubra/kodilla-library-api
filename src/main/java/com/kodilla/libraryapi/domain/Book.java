package com.kodilla.libraryapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(
                name= "Book.getBookByTitle",
                query= "FROM Book WHERE title = :TITLE"
        )

})

@Entity
@Table(name="BOOKS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @NotNull
    @Column(name="TITLE", unique = true)
    private String title;

    @NotNull
    @Column(name="AUTHOR")
    private String author;

    @NotNull
    @Column(name="PUBLICATION_DATE")
    private LocalDate publicationDate;


}
