package dao.daofactory;

import dao.interfacesdao.ActivityDAO;
import dao.interfacesdao.TrackingDAO;
import dao.interfacesdao.UserDAO;
import dao.mysqldaoimpl.ActivityDAOImpl;
import dao.mysqldaoimpl.TrackingDAOImpl;
import dao.mysqldaoimpl.UserDAOImpl;

public class MySqlDaoFactory extends DaoFactory {

    @Override
    public ActivityDAO getActivityDao() {
        return ActivityDAOImpl.getInstance();
    }

    @Override
    public TrackingDAO getTrackingDao() {
        return TrackingDAOImpl.getInstance();
    }

    @Override
    public UserDAO getUserDao() {
        return UserDAOImpl.getInstance();
    }

}
