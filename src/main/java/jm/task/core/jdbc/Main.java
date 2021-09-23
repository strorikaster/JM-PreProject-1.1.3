package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        UserDaoJDBCImpl daoObject = new UserDaoJDBCImpl();
        daoObject.createUsersTable();
        daoObject.saveUser("Alex", "Ivanov", (byte)23);
        daoObject.saveUser("Ivan", "Petrov", (byte)20);
        daoObject.saveUser("Victor", "Sergeev", (byte)42);
        daoObject.saveUser("Sergei", "Sidorov", (byte)15);
        System.out.println(daoObject.getAllUsers());
        daoObject.cleanUsersTable();
        daoObject.dropUsersTable();
    }

}
