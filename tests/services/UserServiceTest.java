package services;

import connection.ConnectionPool;
import connection.TransactionHandler;
import dao.daofactory.DaoFactory;
import dao.interfacesdao.UserDAO;
import entities.User;
import entities.UserType;
import exceptions.DAOException;
import org.junit.Before;
import org.junit.Test;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserService userService;
    private UserDAO userDAOMock;
    private DaoFactory mySqlFactoryMock ;
//    private UserDAO userDAO = mySqlFactory.getUserDao();
    private Connection connectionMock;
    private User expectedUser;

    @Before
    public void setUp() throws SQLException {
        userService = UserService.getInstance();
        mySqlFactoryMock = mock(DaoFactory.class);
        userDAOMock = mock(UserDAO.class);
        connectionMock = mock(Connection.class);
        expectedUser = new User("Ray", "Parker", "admin", "admin",
                new UserType(), null);
    }

//    @Test
//    public void checkUserAuthorizationSuccess() throws SQLException, DAOException {
//        final boolean[] isAuthorized = new boolean[1];
//        when(userDAOMock.isAuthorized(eq("admin"), eq("admin"), any(Connection.class))).thenReturn(true);
//        TransactionHandler.runInTransaction(connection ->
//                isAuthorized[0] = userDAOMock.isAuthorized("admin", "admin", connection), connectionMock);
//        boolean result = isAuthorized[0];
//        assertTrue(result);
//    }

//    @Test
//    public void checkUserAuthorizationSuccess() throws SQLException, DAOException {
//        userService.setConnection(connectionMock);
//        userService.setUserDAO(userDAOMock);
//        when(userDAOMock.isAuthorized(eq("admin"), eq("admin"), any(Connection.class))).thenReturn(true);
//        boolean result = userService.checkUserAuthorization("admin", "admin");
//        assertTrue(result);
//    }

    @Test
   // @RunWith(PowerMockito)
    public void checkUserAuthorizationSuccess() throws SQLException, DAOException, NamingException {

//        userService.setConnection(connectionMock);
//        userService.setUserDAO(userDAOMock);
        when(DaoFactory.getDaoFactory(DaoFactory.MYSQL)).thenReturn(mySqlFactoryMock);
        when(any(DaoFactory.class).getUserDao()).thenReturn(userDAOMock);
        when(ConnectionPool.getInstance().getConnection()).thenReturn(connectionMock);
        when(any(DataSource.class).getConnection()).thenReturn(connectionMock);
        when(userDAOMock.isAuthorized(eq("admin"), eq("admin"), any(Connection.class))).thenReturn(true);
        boolean result = userService.checkUserAuthorization("admin", "admin");
        assertTrue(result);
    }

    @Test(expected = SQLException.class)
    public void checkUserAuthorizationException() throws SQLException, DAOException {
        when(userDAOMock.isAuthorized(eq("admin"), eq("admin"), any(Connection.class)))
                .thenThrow(DAOException.class);
        TransactionHandler.runInTransaction(connection ->
                userDAOMock.isAuthorized("admin", "admin", connection), connectionMock);
    }

    @Test
    public void getUserByLoginSuccess() throws Exception {
        when(userDAOMock.getByLogin(eq("admin"), any(Connection.class))).thenReturn(expectedUser);
        final User[] user = new User[1];
        TransactionHandler.runInTransaction(connection ->
                user[0] = userDAOMock.getByLogin("admin", connection), connectionMock
        );
        User actualUser = user[0];
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getUserByIdSuccess() throws SQLException, DAOException {
        expectedUser.setUserId(1);
        final User[] user = new User[1];
        when(userDAOMock.getById(eq(expectedUser.getUserId().toString()), any(Connection.class))).thenReturn(expectedUser);
        TransactionHandler.runInTransaction(connection ->
                user[0] = userDAOMock.getById(expectedUser.getUserId().toString(), connection), connectionMock
        );
        User actualUser = user[0];
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void isUniqueUserSuccess() throws SQLException, DAOException {
        when(userDAOMock.checkUniqueUser(eq("admin"), any(Connection.class))).thenReturn(true);
        final boolean[] isUnique = new boolean[1];
        TransactionHandler.runInTransaction(connection ->
                isUnique[0] = userDAOMock.checkUniqueUser(expectedUser.getLogin(), connection), connectionMock
        );
        assertTrue(isUnique[0]);
    }

}
