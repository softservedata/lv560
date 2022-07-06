package com.hehetenya.test_forms.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static ConnectionPool instance;
    private static final HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private final String url = "jdbc:postgresql://localhost/test_forms";
    private final String user = "postgres";
    private final String password = "root";

    private ConnectionPool(){
        /*try{
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/test_forms");

        }catch (NamingException e){
            throw new IllegalStateException("Cannot init Connection Pool", e);
        }*/
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

    public static void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void close(AutoCloseable closeable) {
        try {
            closeable.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
