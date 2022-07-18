package com.hehetenya.test_forms.service;

import com.hehetenya.test_forms.dao.impl.DaoFactory;
import com.hehetenya.test_forms.dao.impl.UserDaoImpl;
import com.hehetenya.test_forms.dto.UserDTO;
import com.hehetenya.test_forms.entity.Role;
import com.hehetenya.test_forms.entity.User;
import com.hehetenya.test_forms.exeptions.DBException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserDaoImpl userDao = Mockito.mock(UserDaoImpl.class);


    @Test
    void loginWithExistingUser() throws DBException {
        List<User> users = getUsers(6);
        UserDTO expectedUser = getUserDTOS(1).get(0);
        try (MockedStatic<DaoFactory> daoFactoryMock = Mockito.mockStatic(DaoFactory.class)) {
            daoFactoryMock.when(DaoFactory::getUserDao).thenReturn(userDao);
            Mockito.when(userDao.getAll()).thenReturn(users);
            UserDTO actualUser = UserService.login(expectedUser);
            assertEquals(expectedUser, actualUser);
        }
    }

    @Test
    void loginWithNonExistingUser() throws DBException {
        List<User> users = getUsers(6);
        UserDTO expectedUser = getUserDTOS(7).get(6);
        try (MockedStatic<DaoFactory> daoFactoryMock = Mockito.mockStatic(DaoFactory.class)) {
            daoFactoryMock.when(DaoFactory::getUserDao).thenReturn(userDao);
            Mockito.when(userDao.getAll()).thenReturn(users);
            UserDTO actualUser = UserService.login(expectedUser);
            assertNull(actualUser);
        }
    }

    @Test
    void transform() {
        User user = getUsers(1).get(0);
        UserDTO expectedUserDTO = getUserDTOS(1).get(0);
        UserDTO actualUserDTO = UserService.transform(user);
        assertEquals(expectedUserDTO, actualUserDTO);
    }

    @Test
    void transformDTO() throws DBException {
        List<User> users = getUsers(6);
        UserDTO userDTO = getUserDTOS(1).get(0);
        User expectedUser = getUsers(1).get(0);
        try (MockedStatic<DaoFactory> daoFactoryMock = Mockito.mockStatic(DaoFactory.class)) {
            daoFactoryMock.when(DaoFactory::getUserDao).thenReturn(userDao);
            Mockito.when(userDao.getAll()).thenReturn(users);
            User actualUser = UserService.transformDTO(userDTO);
            assertEquals(expectedUser, actualUser);
        }
    }

    private static List<User> getUsers(int amount) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < amount; ++i) {
            users.add(new User(i, "login" + i, "password" + i, Role.USER));
        }
        return users;
    }

    private static List<UserDTO> getUserDTOS(int amount) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (int i = 0; i < amount; ++i) {
            userDTOS.add(new UserDTO("login" + i, "password" + i, Role.USER));
        }
        return userDTOS;
    }
}