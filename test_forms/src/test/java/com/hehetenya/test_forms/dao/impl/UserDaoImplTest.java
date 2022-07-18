package com.hehetenya.test_forms.dao.impl;

import com.hehetenya.test_forms.dao.ConnectionPool;
import com.hehetenya.test_forms.entity.Role;
import com.hehetenya.test_forms.entity.User;
import com.hehetenya.test_forms.exeptions.DBException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImplTest {

    ResultSet resultSet = Mockito.mock(ResultSet.class);
    PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
    Statement statement = Mockito.mock(Statement.class);
    Connection connection = Mockito.mock(Connection.class);
    ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);

    @Test
    void getAll() throws SQLException, DBException {
        List<User> usersExpected = getUsers(4);
        Mockito.when(resultSet.next()).thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(true)
                .thenReturn(false);
        mockResultSetForGetAll(usersExpected, resultSet);
        mockOtherFields();
        Mockito.when(statement.executeQuery(Mockito.anyString())).thenReturn(resultSet);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        try (MockedStatic<ConnectionPool> conMock = Mockito.mockStatic(ConnectionPool.class)) {
            conMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            List<User> usersActual = DaoFactory.getUserDao().getAll();
            assertEquals(usersExpected.size(), usersActual.size());
            usersActual.sort(Comparator.comparing(User::getId));
            usersExpected.sort(Comparator.comparing(User::getId));
            assertEquals(usersExpected, usersActual);
        }
    }

    private static void mockResultSetForGetAll(List<User> users, ResultSet resultSet) throws SQLException {
        Mockito.when(resultSet.getInt(1)).thenReturn(users.get(0).getId())
                .thenReturn(users.get(1).getId())
                .thenReturn(users.get(2).getId())
                .thenReturn(users.get(3).getId());
        Mockito.when(resultSet.getString(2)).thenReturn(users.get(0).getLogin())
                .thenReturn(users.get(1).getLogin())
                .thenReturn(users.get(2).getLogin())
                .thenReturn(users.get(3).getLogin());
        Mockito.when(resultSet.getString(3)).thenReturn(users.get(0).getPassword())
                .thenReturn(users.get(1).getPassword())
                .thenReturn(users.get(2).getPassword())
                .thenReturn(users.get(3).getPassword());
        Mockito.when(resultSet.getInt(4)).thenReturn(1);
    }

    private void mockOtherFields() throws SQLException {
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(connection.prepareStatement(Mockito.anyString())).thenReturn(preparedStatement);
        Mockito.when(connectionPool.getConnection()).thenReturn(connection);
    }

    @Test
    void getById() throws SQLException, DBException {
        User userExpected = getUsers(1).get(0);
        mockResultSetForGetOne(userExpected, resultSet);
        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(false);
        mockOtherFields();
        try (MockedStatic<ConnectionPool> conMock = Mockito.mockStatic(ConnectionPool.class)) {
            conMock.when(ConnectionPool::getInstance).thenReturn(connectionPool);
            User userActual = DaoFactory.getUserDao().getById(0);
            assertEquals(userExpected, userActual);
        }
    }

    private void mockResultSetForGetOne(User userActual, ResultSet resultSet) throws SQLException {
        Mockito.when(resultSet.getInt(1)).thenReturn(userActual.getId());
        Mockito.when(resultSet.getString(2)).thenReturn(userActual.getLogin());
        Mockito.when(resultSet.getString(3)).thenReturn(userActual.getPassword());
        Mockito.when(resultSet.getInt(4)).thenReturn(1);
    }



    private static List<User> getUsers(int amount) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < amount; ++i) {
            users.add(new User(i, "login" + i, "password" + i, Role.USER));
        }
        return users;
    }
}