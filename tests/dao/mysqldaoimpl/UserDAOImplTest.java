package dao.mysqldaoimpl;

import connection.ConnectionPool;
import connection.Transaction;
import connection.TransactionHandler;
import dao.daofactory.DaoFactory;
import dao.daofactory.MySqlDaoFactory;
import dao.interfacesdao.UserDAO;
import entities.User;
import entities.UserType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DaoFactory.class})
public class UserDAOImplTest {

    @InjectMocks
    private UserDAOImpl userDAOImpl;
    @Mock
    private MySqlDaoFactory mySqlDaoFactory;
    @Mock
    private UserDAO userDAO;
    @Mock
    private Transaction transaction;
    @Mock
    private TransactionHandler transactionHandler;
    @Mock
    private ConnectionPool connectionPool;

    private ArrayList<User> users = new ArrayList<>();
    private User user;

    @Before
    public void setUp() {
        user = new User(null,"Tom","Crooz","user", "user",
                new UserType(null, "client"),null);
        PowerMockito.mockStatic(DaoFactory.class);
        when(DaoFactory.getDaoFactory(1)).thenReturn(mySqlDaoFactory);
    }

    @Test
    public void add() {
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