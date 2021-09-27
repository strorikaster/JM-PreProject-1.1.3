package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
    private static final String MAIN_DB_ADMIN = "root";
    private static final String PASSWORD = "2lKB$smH";
    private static SessionFactory sessionFactory;

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
            } catch (SQLException sqlEx) {
                System.out.println("Connection failure! Please check username, password and url string");
            }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, MAIN_DB_ADMIN);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                config.setProperties(settings);
                config.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
                sessionFactory = config.buildSessionFactory(builder.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
