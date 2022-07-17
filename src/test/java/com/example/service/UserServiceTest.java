package com.example.service;

import com.example.dao.UserDao;
import com.example.model.User;
import com.example.util.Role;
import com.example.util.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserDao userDao;

    @Mock
    private UserService userService;

    private User user;

    private final Integer EXISTING_ID = 3;
    private final long NON_EXISTING_ID = 100L;
    private final boolean IS_STATUS = true;

    private final Role ROLE = Role.MANAGER;
    private final String EXISTING_EMAIL = "admin@email.com";
    private final String NON_EXISTING_EMAIL = "not_admin@email.com";
    private final String EXISTING_FULLNAME = "Admin Adminovicho";
    private final String EXISTING_USERNAME = "admin";
    private final String PASSWORD = "admin";

    @BeforeEach
    void init(){
        user = User.builder()
                .id(EXISTING_ID)
                .email(EXISTING_EMAIL)
                .username(EXISTING_USERNAME)
                .password(PASSWORD)
                .status(Status.ACTIVE)
                .fullName(EXISTING_FULLNAME)
                .role(ROLE)
                .build();
    }

    @Test
    void getUserByIdTest(){
        when(userService.findById(EXISTING_ID)).thenReturn(user);

        User actual = userService.findById(EXISTING_ID);
        assertEquals(actual,user);
    }
}
