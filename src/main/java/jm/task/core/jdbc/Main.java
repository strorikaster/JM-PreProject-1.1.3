package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService serviceObject = new UserServiceImpl();
        serviceObject.createUsersTable();
        serviceObject.saveUser("Alex", "Ivanov", (byte)23);
        serviceObject.saveUser("Ivan", "Petrov", (byte)20);
        serviceObject.saveUser("Victor", "Sergeev", (byte)42);
        serviceObject.saveUser("Sergei", "Sidorov", (byte)15);
        System.out.println(serviceObject.getAllUsers());
        serviceObject.cleanUsersTable();
        serviceObject.dropUsersTable();
    }
}
