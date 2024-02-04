package org.example.dao;
import org.example.config.ContainersEnvironment;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDAOTest extends ContainersEnvironment {


        private UserDAO userDAO;
    @BeforeEach
    void setUp() {
        userDAO = new UserDAO();
    }

    @Test
    void registerUser_ValidUserSuccess() {
        userDAO.createUser("testUser", "password", "USER");

        boolean isGood=userDAO.login("testUser", "password");
            Assertions.assertTrue(isGood);

    }





    }