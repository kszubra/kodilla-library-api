package com.kodilla.libraryapi.mapper;

import com.kodilla.libraryapi.domain.User;
import com.kodilla.libraryapi.domain.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUser(UserDto dto) {
        return User.builder()
                .name( dto.getName() )
                .surname( dto.getSurname() )
                .registrationDate( dto.getRegistrationDate() )
                .prefferedCurrency( dto.getPrefferedCurrency() )
                .emailAddress( dto.getEmailAddress() )
                .hasAdminRights( dto.isHasAdminRights() )
                .build();
    }
}
