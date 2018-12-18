package dao.interfacesdao;

import entities.Tracking;
import exceptions.DAOException;

import java.sql.Connection;

/**
 * Description: This interface describes methods for working with <i>activity</i> database table,
 * extending the capabilities of basic DAO interface.
 *
 * Created by Yaroslav Bodyak on 11.12.2018.
 */
public interface TrackingDAO extends AbstractDAO<Tracking> {

    /**
     * This method deletes an existing record (row) in a database table.
     *
     * @param id            - id number of the current entity which will be deleted.
     * @param connection    - the current connection to a database. Transmitted from the service module to provide transactions.
     */
    void deleteTrackingByUserId(int id, Connection connection) throws DAOException;
    /**
     * This method deletes an existing record (row) in a database table.
     *
     * @param id            - id number of the current entity which will be deleted.
     * @param connection    - the current connection to a database. Transmitted from the service module to provide transactions.
     */
    void deleteTrackingById(int id, Connection connection) throws DAOException;

    /**
     * This method reads and returns information from a record (row) of a database table.
     *
     * @param id            - id number of the record (row) in the database table..
     * @param connection    - the current connection to a database. Transmitted from the service module to provide transactions.
     * @return              - an entity from a database table according to the incoming id number.
     */
    Tracking getTrackingById(int id, Connection connection) throws DAOException;

    /**
     * This method updates an existing record (row) in a database table.
     *
     * @param tracking      - the current entity of activity which will be updated.
     * @param connection    - the current connection to a database. Transmitted from the service module to provide transactions.
     */
    void updateTrackingStatusAndTimeByID (Tracking tracking, Connection connection) throws DAOException;

    /**
     * This method updates an existing record (row) in a database table.
     *
     * @param tracking      - the current entity of activity which will be updated.
     * @param connection    - the current connection to a database. Transmitted from the service module to provide transactions.
     */
    void updateTrackingStatusByID (Tracking tracking, Connection connection) throws DAOException;

}
