package com.kodilla.libraryapi.domain;

import com.kodilla.libraryapi.enumerics.BookCopyStatus;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "BOOK_COPIES")
@Data
public class BookCopy {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @NotNull
    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private BookCopyStatus status;

    @NotNull
    @Column(name = "AVAILABLE_FOR_RENT")
    private boolean isAvailableForRent;

    @OneToMany(mappedBy = "bookCopy")
    private List<Rent> rents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCopy bookCopy = (BookCopy) o;
        return id == bookCopy.id &&
                isAvailableForRent == bookCopy.isAvailableForRent &&
                Objects.equals(book, bookCopy.book) &&
                Objects.equals(status, bookCopy.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, status, isAvailableForRent);
    }
}

