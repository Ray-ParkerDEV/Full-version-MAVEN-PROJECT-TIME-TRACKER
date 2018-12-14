package utils;

import connection.ConnectionPool;
import constants.MessageConstants;
import manager.ConfigManagerDB;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Description: This class contains static method which executes reading parameters for connection to database from
 * resource file <i>database.properties</i> and receive a connection.
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public class ConnectorDB {
    private final static Logger logger = Logger.getLogger(ConnectorDB.class);

    /**
     * This method provides making a connection to database using a property file.
     *
     * @return - connection to database.
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName(ConfigManagerDB.getInstance().getProperty("db.classforname"));
//            connection = DriverManager.getConnection(
//                    ConfigManagerDB.getInstance().getProperty("db.url"),
//                    ConfigManagerDB.getInstance().getProperty("db.user"),
//                    ConfigManagerDB.getInstance().getProperty("db.password")
//             );
            connection = ConnectionPool.getInstance().getConnection();


        } catch (ClassNotFoundException e) {
            logger.error(MessageConstants.CLASS_FOR_NAME_ERROR, e);
        }
        return connection;
    }

    /**
     * This method closes Statement after the queries have been executed.
     *
     * @param statement - an using statement.
     */
    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error(MessageConstants.STATEMENT_ERROR);
            }
        }
    }

    /**
     * This method closes ResultSet after queries have been executed and information have been processed.
     *
     * @param resultSet - the result from the query.
     */
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(MessageConstants.RESULTSET_ERROR);
            }
        }
    }

    /**
     * This method closes Connection after queries have been executed and information have been processed.
     *
     * @param connection - an using connection.
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(MessageConstants.CONNECTION_ERROR);
            }
        }
    }
}
