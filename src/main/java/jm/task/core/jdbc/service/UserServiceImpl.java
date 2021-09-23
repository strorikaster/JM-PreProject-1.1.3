package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.*;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoJDBCImpl userDAOObject = new UserDaoJDBCImpl();

    public void createUsersTable() {
        userDAOObject.createUsersTable();
    }

    public void dropUsersTable() {
        userDAOObject.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDAOObject.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDAOObject.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDAOObject.getAllUsers();
    }

    public void cleanUsersTable() {
        userDAOObject.cleanUsersTable();
    }
}
