package services;

import connection.ConnectionPool;
import connection.TransactionHandler;
import dao.daofactory.DaoFactory;
import dao.interfacesdao.UserDAO;
import entities.User;
import entities.UserType;
import exceptions.DAOException;
import org.apache.tomcat.dbcp.dbcp2.ConnectionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {
    private UserService userService;
    private UserDAO userDAOMock;
    private DaoFactory mySqlFactoryMock;
    private Context contextMock;
    private Connection connectionMock;
    private User expectedUser;

    @Before
    public void setUp() throws SQLException {
        contextMock = mock(Context.class);
        userService = UserService.getInstance();
        mySqlFactoryMock = mock(DaoFactory.class);
        userDAOMock = mock(UserDAO.class);
        connectionMock = mock(Connection.class);
        expectedUser = new User("Ray", "Parker", "admin", "admin",
                new UserType(), null);
        MockitoAnnotations.initMocks(this);
        System.setProperty("java.naming.factory.initial",
                this.getClass().getCanonicalName() + "$MyContextFactory");

    }

    @Mock
    InitialContextFactory ctx;
    public static class MyContextFactory implements InitialContextFactory
    {
        @Override
        public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
            ConnectionFactory mockConnFact = mock(ConnectionFactory.class);
            InitialContext mockCtx = mock(InitialContext.class);
            when(mockCtx.lookup("jms1")).thenReturn(mockConnFact);
            return mockCtx;
        }
    }

    @Test
    public void checkUserAuthorizationSuccess() throws SQLException, DAOException, NamingException {
//        userService.setConnection(connectionMock);
//        userService.setUserDAO(userDAOMock);
//        when(any(Context.class).lookup("java:comp/env")).thenReturn(contextMock);
//        when(DaoFactory.getDaoFactory(DaoFactory.MYSQL)).thenReturn(mySqlFactoryMock);
//        when(any(DaoFactory.class).getUserDao()).thenReturn(userDAOMock);
        when(ConnectionPool.getInstance().getConnection()).thenReturn(connectionMock);
//        when(any(DataSource.class).getConnection()).thenReturn(connectionMock);
        when(userDAOMock.isAuthorized(eq("admin"), eq("admin"), any(Connection.class))).thenReturn(true);
        boolean result = userService.checkUserAuthorization("admin", "admin");
        assertTrue(result);
    }

//    @Before
//    public void setupClass() throws IOException
//    {
//        MockitoAnnotations.initMocks(this);
//        System.setProperty("java.naming.factory.initial",
//                this.getClass().getCanonicalName() + "$SetMyContextFactory");
//    }


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

//    @Rule
//    public MockInitialContextRule mockInitialContextRule = new MockInitialContextRule(contextMock);


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
