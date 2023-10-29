package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection connect = Util.getconnect();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = connect.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users" +
                    "(id SERIAL primary key, name varchar(20), lastName varchar(20), age INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {
        try (Statement statement = connect.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement fullTable = connect.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            fullTable.setString(1, name);
            fullTable.setString(2, lastName);
            fullTable.setByte(3, age);
            fullTable.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeUserById(long id) {
        try (PreparedStatement fullTable = connect.prepareStatement("DELETE FROM users WHERE id = ?")) {
            fullTable.setLong(1, id);
            fullTable.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement ps = connect.prepareStatement("SELECT * FROM users");
             ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                User user = new User(rs.getString(2), rs.getString(3), rs.getByte(4));
                user.setId(rs.getLong("id"));
                list.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Statement statement = connect.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}