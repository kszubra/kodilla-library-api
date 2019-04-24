package com.kodilla.libraryapi.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(
                name= "Book.getBooksByTitle",
                query= "FROM Book WHERE title = :TITLE"
        )

})

@Entity
@Table(name="BOOKS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
public class Book {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @NotNull
    @Column(name="TITLE")
    private String title;

    @NotNull
    @Column(name="AUTHOR")
    private String author;

    @NotNull
    @Column(name="PUBLICATION_DATE")
    private LocalDate publicationDate;
}
