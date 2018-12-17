package services;

import connection.ConnectionPool;
import dao.daofactory.DaoFactory;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {
    private volatile static ServiceFactory instance;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    return instance = new ServiceFactory();
                }
            }
        }
        return instance;
    }

    private Map<String, Object> serviceMap = new HashMap<>(20);

    public Object getService(String serviceName) {
        Object existingService = serviceMap.get(serviceName);
        if (existingService == null) {
            Object service = createService(serviceName);
            serviceMap.put(serviceName, service);
            return service;
        } else {
            return existingService;
        }
    }

    private Object createService(String serviceName) {
        synchronized (ServiceFactory.class) {
            if (serviceMap.containsKey(serviceName)) {
                return serviceMap.get(serviceName);
            }
            switch (serviceName) {
                case "userService": {
                    UserService userService = UserService.getInstance();
                    DaoFactory daoFactory = DaoFactory.getDaoFactory(DaoFactory.MYSQL);
                    userService.setUserDao(daoFactory.getUserDao());
                    userService.setConnectionPool(ConnectionPool.getInstance());
                    return userService;
                }
               // case "...":{}
                default:
                    throw new RuntimeException("Can't find service " + serviceName);
            }
        }
    }
}
