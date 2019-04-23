package com.kodilla.libraryapi.domain.dto;

import com.kodilla.libraryapi.domain.Rent;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookCopyDto {

    private long id;
    private long bookId;
    private String status;
    private boolean availableForRent;
    private List<Rent> rents;

    @Override
    public String toString() {
        return "BookCopyDto{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", status='" + status + '\'' +
                ", isAvailableForRent=" + availableForRent +
                ", rents=" + rents +
                '}';
    }
}
