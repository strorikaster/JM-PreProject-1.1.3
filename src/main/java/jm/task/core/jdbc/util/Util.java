package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String MAIN_DB_ADMIN = "root";
    private static final String PASSWORD = "2lKB$smH";

    public Util() {
    }

    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return MAIN_DB_ADMIN;
    }

    public static String getPassword() {
        return PASSWORD;
    }

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());
//                if (connection != null) {
//                   System.out.println("Connection ok");
//                }
            } catch (SQLException sqlEx) {
                System.out.println("Connection failure! Please check username, password and url string");
            }
        return connection;
    }
}
