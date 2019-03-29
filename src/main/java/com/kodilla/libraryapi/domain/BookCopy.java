package com.kodilla.libraryapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name="BOOK_COPIES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookCopy {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @NotNull
    @Column(name="STATUS")
    private String status;

    @NotNull
    @Column(name="AVAILABLE_FOR_RENT")
    private boolean isAvailableForRent;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCopy bookCopy = (BookCopy) o;
        return id == bookCopy.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
