package com.kodilla.libraryapi.service;

import com.kodilla.libraryapi.domain.User;
import com.kodilla.libraryapi.exceptions.UserNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Before
    public void cleanup() {
        userService.deleteAllUsers();
    }

    @Test
    @Transactional
    public void testGettingUserById() {
        //Given
        User testUser = new User();
        testUser.setName("John");
        testUser.setSurname("Rambo");
        testUser.setHasAdminRights(false);
        testUser.setPrefferedCurrency("PLN");
        testUser.setEmailAddress("ala@ala.pl");
        testUser.setRegistrationDate(LocalDate.now());

        //When
        userService.addUser(testUser);
        long userId = testUser.getId();
        User loadedUser = userService.getUserById(userId);

        //Then
        Assert.assertTrue(loadedUser.getName().equals("John"));
    }

    @Test(expected = UserNotFoundException.class)
    @Transactional
    public void testGettingUserByIdException() {
        //Given
        long userId = 1L;

        //When & Then
        User loadedUser = userService.getUserById(userId);
    }

}