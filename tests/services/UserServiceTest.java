package services;

import connection.ConnectionPool;
import dao.daofactory.DaoFactory;
import dao.interfacesdao.UserDAO;
import entities.User;
import entities.UserType;
import exceptions.DAOException;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private UserService userService;
    private UserDAO userDAOMock;
    private DaoFactory mySqlFactoryMock;
    private ConnectionPool connectionPoolMock;

    @Before
    public void setUp() {
        userService = UserService.getInstance();
        userDAOMock = mock(UserDAO.class);
        mySqlFactoryMock = mock(DaoFactory.class);
        connectionPoolMock = mock(ConnectionPool.class);
    }

    @Test
    public void checkUserAuthorizationSuccess() throws SQLException, DAOException {
        when(connectionPoolMock.getConnection()).thenReturn(mock(Connection.class));
        when(userDAOMock.isAuthorized(eq("admin"),eq( "admin"), any(Connection.class)))
                .thenReturn(true);
        boolean result = userService.checkUserAuthorization("admin", "admin");
        assertTrue(result);
    }

    @Test (expected = SQLException.class)
    public void checkUserAuthorizationException() throws SQLException, DAOException {
        when(connectionPoolMock.getConnection()).thenReturn(mock(Connection.class));
        when(userDAOMock.isAuthorized(eq("admin"),eq( "admin"), any(Connection.class)))
                .thenThrow(DAOException.class);
        boolean result = userService.checkUserAuthorization("admin", "admin");
        assertTrue(result);
    }

    @Test
    public void getUserByLoginSuccess() throws Exception {
        Connection connectionMock = mock(Connection.class);
        User user = new User(3, "Ievgen", "Kopachev", "admin", "admin",
                new UserType(5, "admin"),false);
        when(connectionPoolMock.getConnection()).thenReturn(connectionMock);
        when(userDAOMock.getByLogin(eq("admin"), eq(connectionMock)))
                .thenReturn(user);
        User result = userService.getUserByLogin("admin");
        assertEquals(user, result);
    }

    @Test (expected = SQLException.class)
    public void getUserByLoginFailed() throws Exception {
        Connection connectionMock = mock(Connection.class);
        when(connectionPoolMock.getConnection()).thenReturn(connectionMock);
        when(userDAOMock.getByLogin(eq("admin"), eq(connectionMock)))
                .thenThrow(DAOException.class);
        User result = userService.getUserByLogin("admin");
    }
    @Test
    public void updateUser() {
    }

    @Test
    public void isUniqueUser() {
    }

    @Test
    public void registerUser() {
    }

    @Test
    public void setAttributeToSession() {
    }
}
