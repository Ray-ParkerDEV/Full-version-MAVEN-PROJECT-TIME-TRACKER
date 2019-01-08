package dao.mysqldaoimpl;

import connection.ConnectionPool;
import connection.Transaction;
import connection.TransactionHandler;
import dao.daofactory.DaoFactory;
import dao.daofactory.MySqlDaoFactory;
import entities.User;
import entities.UserType;
import exceptions.DAOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(PowerMockRunner.class)
public class UserDAOImplTest {

    @InjectMocks
    private UserDAOImpl userDAOImpl;
    @Mock
    private MySqlDaoFactory mySqlDaoFactory;
    @Mock
    private Transaction transaction;
    @Mock
    private TransactionHandler transactionHandler;
    @Mock
    private ConnectionPool connectionPool;
    @Mock
    private Connection connection;
    @Mock
    Context initContext = new InitialContext();

    private ArrayList<User> users = new ArrayList<>();
    private User user;

    public UserDAOImplTest() throws NamingException {
    }

    @Before
    public void setUp() {
        user = new User("Kat", "Murzic", "user", "user",
                new UserType(null, "client"), null);
        PowerMockito.mockStatic(DaoFactory.class);
//        when(DaoFactory.getDaoFactory(1)).thenReturn(mySqlDaoFactory);
    }

    @Test
    public void add() throws SQLException, DAOException {
        Connection connection = ConnectionPool.getInstance().getConnection();
        connection.setAutoCommit(false);
        assertThat(userDAOImpl.getByLogin(user.getLogin(), connection), is(equalTo(user)));
    }

    @Test
    public void isAuthorized() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void getByLogin() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void createUser() {
    }

    @Test
    public void checkUniqueUser() {
    }
}