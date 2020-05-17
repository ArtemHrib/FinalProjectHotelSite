package ua.nure.hrybeniuk.finalProject.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import ua.nure.hrybeniuk.finalProject.exception.DBException;
import ua.nure.hrybeniuk.finalProject.exception.ErrorMessages;

/**
 * DB tools. Special static methods for closing DB entities and make rollback 
 * 
 * @author A.Hrybeniuk
 * 
 */
public class DBTools {
	private static final Logger LOG = Logger.getLogger(DBTools.class);
	public static void close(ResultSet set) throws DBException {
		try {
			if (set != null) {
				set.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_CLOSE_RESULT_SET, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_CLOSE_RESULT_SET, e);
		}
	}

	public static void close(Statement stmt) throws DBException {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_CLOSE_STATEMENT, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_CLOSE_STATEMENT, e);
		}
	}

	public static void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
