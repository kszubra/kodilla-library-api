package com.kodilla.libraryapi.mapper;

import com.kodilla.libraryapi.domain.Rent;
import com.kodilla.libraryapi.domain.dto.RentDto;
import com.kodilla.libraryapi.service.BookCopyService;
import com.kodilla.libraryapi.service.FineService;
import com.kodilla.libraryapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RentMapper {
    private final UserService userService;
    private final BookCopyService bookCopyService;
    private final FineService fineService;

    public Rent mapToRent(final RentDto dto) {
        return Rent.builder()
                .id( dto.getId() )
                .user( userService.getUserById(dto.getUserId()) )
                .bookCopy( bookCopyService.getBookCopyById(dto.getBookCopyId()) )
                .rentDate( dto.getRentDate() )
                .returnDeadline( dto.getReturnDeadline() )
                .rentCode( dto.getRentCode() )
                .fine(
                        (dto.getFineId() > 0)? fineService.getFineById( dto.getFineId() ) : null
                )
                .returned( dto.isReturned() )
                .build();
    }
}
