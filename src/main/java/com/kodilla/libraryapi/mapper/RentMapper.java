package com.kodilla.libraryapi.mapper;

import com.kodilla.libraryapi.domain.Rent;
import com.kodilla.libraryapi.domain.dto.RentDto;
import com.kodilla.libraryapi.service.BookCopyService;
import com.kodilla.libraryapi.service.FineService;
import com.kodilla.libraryapi.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class RentMapper {
    private final UserService userService;
    private final BookCopyService bookCopyService;
    private final FineService fineService;

    public RentMapper(final UserService userService, final BookCopyService bookCopyService, final FineService fineService) {
        this.userService = userService;
        this.bookCopyService = bookCopyService;
        this.fineService = fineService;
    }

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
                .isReturned( dto.isReturned() )
                .build();
    }
}
