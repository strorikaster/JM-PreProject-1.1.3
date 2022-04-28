package jm.task.core.jdbc.dao;

import com.mysql.cj.jdbc.DatabaseMetaData;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {}



    public void createUsersTable() {
        try (Connection conn = Util.getMySQLConnection();
                Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE users (id INTEGER NOT NULL AUTO_INCREMENT, name VARCHAR(45), lastname VARCHAR(45), age TINYINT(3), PRIMARY KEY (id))");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Create table SQL error occured");
        }
    }

    public void dropUsersTable() {
        try(Connection conn = Util.getMySQLConnection();) {
            DatabaseMetaData metaData = (DatabaseMetaData) conn.getMetaData();
            ResultSet resultSet;
            resultSet = metaData.getTables(null, null, "users", null);
            if (resultSet != null) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate("DROP TABLE users");
                } catch (SQLException e) {}
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Drop table SQL error occured");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
       try (Connection conn = Util.getMySQLConnection();
            PreparedStatement preStmt = conn.prepareStatement("INSERT INTO users VALUES (default, ?, ?, ?)")) {
            preStmt.setString(1, name);
            preStmt.setString(2, lastName);
            preStmt.setByte(3, age);
            preStmt.execute();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Save user SQL error occured");
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getMySQLConnection();
            PreparedStatement preStmt = conn.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preStmt.setLong(1, id);
            preStmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Remove user by ID SQL error occured");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try(Connection conn = Util.getMySQLConnection();
            Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()) {
                userList.add(new User(resultSet.getString(2),
                                      resultSet.getString(3),
                                      resultSet.getByte(4)));
            }
        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Get all users SQL error occured");
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getMySQLConnection();
            Statement statement = conn.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Clean user table SQL error occured");
        }
    }
}
