package com.kodilla.libraryapi.domain;

import com.kodilla.libraryapi.enumerics.BookCopyStatus;
import com.kodilla.libraryapi.exceptions.BookCopyStatusDoesNotExistException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "BOOK_COPIES")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
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
    private boolean availableForRent;

    @OneToMany(mappedBy = "bookCopy")
    private List<Rent> rents;

    public void setStatusByString(String status) {
        status = status.toLowerCase();

        switch(status) {
            case "in use":
                this.status = BookCopyStatus.IN_USE;
                this.availableForRent=true;
                break;
            case "in renovation":
                this.status = BookCopyStatus.IN_RENOVATION;
                this.availableForRent = false;
                break;
            case "lost":
                this.status = BookCopyStatus.LOST;
                this.availableForRent = false;
                break;
            case "destroyed":
                this.status = BookCopyStatus.DESTROYED;
                this.availableForRent = false;
                break;
            default:
                throw new BookCopyStatusDoesNotExistException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCopy bookCopy = (BookCopy) o;
        return id == bookCopy.id &&
                availableForRent == bookCopy.availableForRent &&
                Objects.equals(book, bookCopy.book) &&
                Objects.equals(status, bookCopy.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, status, availableForRent);
    }
}

