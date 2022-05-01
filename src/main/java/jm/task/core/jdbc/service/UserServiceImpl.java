package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.*;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDaoHibernateObject = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDaoHibernateObject.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoHibernateObject.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernateObject.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoHibernateObject.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoHibernateObject.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoHibernateObject.cleanUsersTable();
    }
}
