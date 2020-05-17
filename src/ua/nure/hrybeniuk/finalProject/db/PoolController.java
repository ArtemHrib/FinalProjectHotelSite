package ua.nure.hrybeniuk.finalProject.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

/**
 * Pool controller. Pool of DB connections. Uses ConfigParser to take
 * configurations
 * 
 * @author A.Hrybeniuk
 * 
 */

public class PoolController {
	private static final Logger LOG = Logger.getLogger(DBManager.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////
	private static PoolController instance;
	private List<Connection> conPool;
	private String url;
	private int maxCon = 10;
	private int startCon = 3;
	private int currentCon;
	private final Object locker = new Object();

	private PoolController() throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ConfigParser cp = ConfigParser.getInstance();
		this.maxCon = cp.getMaxCon();
		this.url = cp.getUrl();
		this.startCon = cp.getStartCon();
		conPool = new ArrayList<>(maxCon);
		int k = startCon > maxCon ? maxCon : startCon;
		for (int i = 0; i < k; i++) {
			add();
		}
		LOG.debug("PoolController was successfully initialized");
		LOG.trace("Currect connection's number = " + currentCon);
	}

	// Add new DB connection to List
	public Connection add() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url);
			conPool.add(connection);
			currentCon++;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LOG.trace("PoolController added connection, current number = " + currentCon);
		return connection;
	}

	// return Connection with removing from the List
	public Connection getConnection() {
		synchronized (locker) {
			Connection connection = null;
			if (!conPool.isEmpty()) {
				connection = conPool.remove(conPool.size() - 1);
			} else {
				if (currentCon == maxCon) {
					try {
						locker.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return getConnection();
				} else {
					connection = add();
				}
			}
			return connection;
		}
	}

	// Add given connection to the List
	public void close(Connection connection) {
		synchronized (locker) {
			conPool.add(connection);
			locker.notifyAll();
		}
	}

	public static PoolController getInstance()
			throws SAXException, IOException, ParserConfigurationException, ClassNotFoundException {
		if (instance == null) {
			instance = new PoolController();
		}
		return instance;
	}

	@Override
	public String toString() {
		return "PoolController [maxCon=" + maxCon + ", startCon=" + startCon + ", currentCon=" + currentCon + "]";
	}

}
