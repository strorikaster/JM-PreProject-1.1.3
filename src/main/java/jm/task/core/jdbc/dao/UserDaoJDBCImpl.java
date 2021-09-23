package jm.task.core.jdbc.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }



    @Override
    public void createUsersTable() {
        try (Connection conn = Util.connect();
             Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("CREATE TABLE users (id INTEGER NOT NULL AUTO_INCREMENT, name VARCHAR(45), lastname VARCHAR(45), age TINYINT(3), PRIMARY KEY (id))");
        } catch (SQLException e) {
            System.out.println("SQL error occured");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection conn = Util.connect();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("DROP TABLE users");
        } catch (SQLException e) {
            System.out.println("SQL error occur");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try ( Connection conn = Util.connect();
        PreparedStatement preStmt = conn.prepareStatement("INSERT INTO users VALUES (default, ?, ?, ?)")) {
            preStmt.setString(1, name);
            preStmt.setString(2, lastName);
            preStmt.setByte(3, age);
            preStmt.execute();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("SQL error occure");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Connection conn = Util.connect();
             Statement statement = conn.createStatement()){
        statement.executeUpdate("DELETE FROM users WHERE id =" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try(Connection conn = Util.connect();
        Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while(resultSet.next()) {
                userList.add(new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4)));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.connect();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
