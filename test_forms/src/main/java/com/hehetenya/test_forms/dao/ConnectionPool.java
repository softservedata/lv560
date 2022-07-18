package com.hehetenya.test_forms.dao;

import com.hehetenya.test_forms.exeptions.AppException;
import com.hehetenya.test_forms.exeptions.DBException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Singleton implementation of connection pool using Hikari and configuring it with data source.
 */

public class ConnectionPool {

    private static ConnectionPool instance;
    private static final HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private ConnectionPool(){
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("on classpath");
            //on classpath
        } catch(ClassNotFoundException e) {
            // not on classpath
            System.out.println("not on classpath");
        }
        config.setJdbcUrl("jdbc:postgresql:///test_forms");
        config.setUsername("postgres");
        config.setPassword("root");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource(config);
    }

    public static ConnectionPool getInstance(){
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * Custom static rollback method to handle exceptions thrown by Connection::rollback.
     * @param con Connection to rollback
     */
    public static void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException e) {
            throw new AppException("Cannot rollback", e);
        }
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * Custom static close method to handle exceptions thrown by AutoCloseable::close.
     * @param closeable Any class that implements AutoCloseable interface to close.
     */
    public static void close(AutoCloseable closeable) {
        try {
            closeable.close();
        } catch (Exception e) {
            throw new AppException("Cannot rollback", e);
        }
    }
}
