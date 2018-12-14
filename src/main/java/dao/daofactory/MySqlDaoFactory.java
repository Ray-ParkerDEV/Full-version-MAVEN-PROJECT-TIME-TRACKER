package dao.daofactory;

import dao.interfacesdao.ActivityDAO;
import dao.interfacesdao.TrackingDAO;
import dao.interfacesdao.UserDAO;
import dao.mysqldaoimpl.UserDAOImpl;

public class MySqlDaoFactory extends DaoFactory {

    @Override
    public ActivityDAO getActivityDao() {
        return null;
    }

    @Override
    public TrackingDAO getTrackingDao() {
        return null;
    }

    @Override
    public UserDAO getUserDao() {
        return UserDAOImpl.getInstance();
    }

//    Connection connection;
//    @Override
//    public Connection createConnection() throws SQLException {
//        // create connection
//        try {
//            //connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }

}
