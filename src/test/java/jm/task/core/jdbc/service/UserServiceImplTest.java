package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserDaoJDBCImpl userDao;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetAllUsersWhenDatabaseNotEmptyThenReturnUsers() {
        // Arrange
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User("John", "Doe", (byte) 30));
        expectedUsers.add(new User("Jane", "Doe", (byte) 25));
        when(userDao.getAllUsers()).thenReturn(expectedUsers);

        // Act
        List<User> actualUsers = userService.getAllUsers();

        // Assert
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testGetAllUsersWhenDatabaseEmptyThenReturnEmptyList() {
        // Arrange
        List<User> expectedUsers = new ArrayList<>();
        when(userDao.getAllUsers()).thenReturn(expectedUsers);

        // Act
        List<User> actualUsers = userService.getAllUsers();

        // Assert
        assertEquals(expectedUsers, actualUsers);
    }
}