package com.hehetenya.test_forms.dao.impl;

import com.hehetenya.test_forms.dao.ConnectionPool;
import com.hehetenya.test_forms.dao.Dao;
import com.hehetenya.test_forms.entity.Role;
import com.hehetenya.test_forms.entity.User;
import com.hehetenya.test_forms.exeptions.DBException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Dao interface implementation for PostgresSQL database using JDBC API
 * that performs CRUD operations on objects of type User.
 */
public class UserDaoImpl implements Dao<User> {

    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String UPDATE_USER = "UPDATE users SET login = ?, password = ?, role = ? WHERE id = ?";
    private static final String CREATE_USER = "INSERT INTO users (login, password, role_id) VALUES (?, ?, ?)";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";

    @Override
    public List<User> getAll() throws DBException {
        List<User> users = new ArrayList<>();
        try(Connection con = ConnectionPool.getInstance().getConnection();
            Statement stmt = con.createStatement()){
            ResultSet rs = stmt.executeQuery(GET_ALL_USERS);
            while (rs.next()){
                users.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        Role.getRole(rs.getInt(4))));
            }
        }catch (SQLException e){
            throw new DBException("Cannot get all users", e);
        }
        return users;
    }

    @Override
    public User getById(int id) throws DBException {
        User user = null;
        try(Connection con = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(GET_USER_BY_ID)){
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    user = new User(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            Role.getRole(rs.getInt(4)));
                }
            }
        }catch (SQLException e){
            throw new DBException("Cannot get user by id", e);
        }
        return user;
    }

    @Override
    public void update(User user) throws DBException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
        PreparedStatement ps = con.prepareStatement(UPDATE_USER)){
            int k = 0;
            ps.setString(++k, user.getLogin());
            ps.setString(++k, user.getPassword());
            ps.setInt(++k, user.getRole().getId());
            ps.setInt(++k, user.getId());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Update user failed");
            }
        }catch (SQLException e){
            throw new DBException("Cannot update user", e);
        }
    }

    @Override
    public void delete(User user) throws DBException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(DELETE_USER)){
            ps.setInt(1, user.getId());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Delete user failed");
            }
        }catch (SQLException e){
            throw new DBException("Cannot delete user", e);
        }
    }

    @Override
    public void create(User user) throws DBException {
        try(Connection con = ConnectionPool.getInstance().getConnection();
            PreparedStatement ps = con.prepareStatement(CREATE_USER)){
            int k = 0;
            ps.setString(++k, user.getLogin());
            ps.setString(++k, user.getPassword());
            ps.setInt(++k, user.getRole().getId());
            if(ps.executeUpdate() == 0){
                throw new SQLException("Create user failed");
            }
        }catch (SQLException e){
            throw new DBException("Cannot create user", e);
        }
    }
}


