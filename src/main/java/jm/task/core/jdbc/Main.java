package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public final static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        userService.createUsersTable();
        userService.saveUser("Lawrence", "Kasdan", (byte) 74);
        userService.saveUser("Willard", "Huyck", (byte) 78);
        userService.saveUser("Gloria", "Katz", (byte) 76);
        userService.saveUser("Jeffrey", "Boam", (byte) 50);
        userService.removeUserById(2);
        userService.getAllUsers();
        /*userService.cleanUsersTable();*/
        /*userService.dropUsersTable();*/

        }
    }