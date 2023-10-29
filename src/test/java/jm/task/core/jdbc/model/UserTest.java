package jm.task.core.jdbc.model;

public class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
    }

    @Test
    public void testGetIdWhenIdIsSetThenReturnId() {
        // Arrange
        Long expectedId = 1L;
        user.setId(expectedId);

        // Act
        Long actualId = user.getId();

        // Assert
        assertThat(actualId).isEqualTo(expectedId);
    }

    @Test
    public void testGetIdWhenIdIsNotSetThenReturnNull() {
        // Act
        Long actualId = user.getId();

        // Assert
        assertThat(actualId).isNull();
    }

    @Test
    public void testGetNameWhenNameIsSetThenReturnName() {
        // Arrange
        String expectedName = "John";
        user.setName(expectedName);

        // Act
        String actualName = user.getName();

        // Assert
        assertThat(actualName).isEqualTo(expectedName);
    }

    @Test
    public void testGetNameWhenNameIsNotSetThenReturnNull() {
        // Act
        String actualName = user.getName();

        // Assert
        assertThat(actualName).isNull();
    }

    @Test
    public void testGetLastNameWhenLastNameIsSetThenReturnLastName() {
        // Arrange
        String expectedLastName = "Doe";
        user.setLastName(expectedLastName);

        // Act
        String actualLastName = user.getLastName();

        // Assert
        assertThat(actualLastName).isEqualTo(expectedLastName);
    }

    @Test
    public void testGetLastNameWhenLastNameIsNotSetThenReturnNull() {
        // Act
        String actualLastName = user.getLastName();

        // Assert
        assertThat(actualLastName).isNull();
    }

    @Test
    public void testGetAgeWhenAgeIsSetThenReturnAge() {
        // Arrange
        Byte expectedAge = 30;
        user.setAge(expectedAge);

        // Act
        Byte actualAge = user.getAge();

        // Assert
        assertThat(actualAge).isEqualTo(expectedAge);
    }

    @Test
    public void testGetAgeWhenAgeIsNotSetThenReturnNull() {
        // Act
        Byte actualAge = user.getAge();

        // Assert
        assertThat(actualAge).isNull();
    }
}