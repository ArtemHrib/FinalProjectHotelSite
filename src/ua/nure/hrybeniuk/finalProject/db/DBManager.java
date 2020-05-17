package ua.nure.hrybeniuk.finalProject.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import ua.nure.hrybeniuk.finalProject.db.constants.DBQueries;
import ua.nure.hrybeniuk.finalProject.db.entity.Booking;
import ua.nure.hrybeniuk.finalProject.db.entity.BookingQuerry;
import ua.nure.hrybeniuk.finalProject.db.entity.Room;
import ua.nure.hrybeniuk.finalProject.db.entity.RoomTypes;
import ua.nure.hrybeniuk.finalProject.db.entity.User;
import ua.nure.hrybeniuk.finalProject.exception.DBException;
import ua.nure.hrybeniuk.finalProject.exception.ErrorMessages;

/**
 * DB manager. Works with PollController. Only the required DAO methods are
 * defined!
 * 
 * @author A.Hrybeniuk
 * 
 */

public class DBManager {
	private static final Logger LOG = Logger.getLogger(DBManager.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private static DBManager instance;
	private PoolController poolController;

	private DBManager() throws DBException {
		try {
			poolController = PoolController.getInstance();
			LOG.trace("DB Pool: " + poolController);
		} catch (ClassNotFoundException | SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_INITIALIZE_DB_MANAGER, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_INITIALIZE_DB_MANAGER, e);
		}

	}

	public static DBManager getInstance() throws DBException {
		try {
			if (instance == null) {
				return instance = new DBManager();
			}
		} catch (DBException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_DB_MANAGER, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_DB_MANAGER, e);

		}
		return instance;
	}

	//
	// =============================================================================================================
	//

	
	/**
	 * Update booking status.
	 * 
	 * @param id, status
	 *            id of booking to update status.
	 * @throws DBException
	 */
	public void updateBookingSpecialStatus(int id, int status) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.UPDATE_BOOKING_SPECIAL_STATUS);
			int k = 1;
			ps.setInt(k++, status);
			ps.setInt(k++, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_UPDATE_BOOKING_STATUS, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_UPDATE_BOOKING_STATUS, e);
		} finally {
			DBTools.close(ps);
			poolController.close(con);
		}
	}
	
	/**
	 * Update room status.
	 * 
	 * @param id, status
	 *            id of room to update status.
	 * @throws DBException
	 */
	public void updateRoomStatusById(int id, int status) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.UPDATE_ROOM_STATUS_BY_ID);
			int k = 1;
			ps.setInt(k++, status);
			ps.setInt(k++, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_UPDATE_ROOM_STATUS, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_UPDATE_ROOM_STATUS, e);
		} finally {
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Delete booking.
	 * 
	 * @param id
	 *            id of deleted booking.
	 * @throws DBException
	 */
	public void deleteBookingById(int id) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.DELETE_BOOKING_BY_ID);
			int k = 1;
			ps.setInt(k++, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_DELETE_BOOKING_BY_ID, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_DELETE_BOOKING_BY_ID, e);
		} finally {
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Update booking query status.
	 * 
	 * @param id, status
	 *            id of booking query to update status.
	 * @throws DBException
	 */
	public void updateBookingQuerryStatus(int id, int status) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.UPDATE_BOOKING_QUERRY_STATUS);
			int k = 1;
			ps.setInt(k++, status);
			ps.setInt(k++, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_UPDATE_BOOKING_QUERY_STATUS, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_UPDATE_BOOKING_QUERY_STATUS, e);
		} finally {
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Update booking status.
	 * 
	 * @param id, status
	 *            id of booking to update status.
	 * @throws DBException
	 */
	public void updateBookingStatus(int id, int status) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.UPDATE_BOOKING_STATUS);
			int k = 1;
			ps.setInt(k++, status);
			ps.setInt(k++, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_UPDATE_BOOKING_STATUS, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_UPDATE_BOOKING_STATUS, e);
		} finally {
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Update user's phone number.
	 * 
	 * @param id, phone
	 *            id of user to update phone number.
	 * @throws DBException
	 */
	public void updateUserPhone(int id, String phone) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.UPDATE_USER_PHONE);
			int k = 1;
			ps.setString(k++, phone);
			ps.setInt(k++, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_UPDATE_USER_PHONE, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_UPDATE_USER_PHONE, e);
		} finally {
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Update user's password.
	 * 
	 * @param id, password
	 *            id of user to update password.
	 * @throws DBException
	 */
	public void updateUserPassword(int id, String password) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.UPDATE_USER_PASSWORD);
			int k = 1;
			ps.setString(k++, password);
			ps.setInt(k++, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_UPDATE_USER_PASSWORD, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_UPDATE_USER_PASSWORD, e);
		} finally {
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Update user's name.
	 * 
	 * @param id, name
	 *            id of user to update name.
	 * @throws DBException
	 */
	public void updateUserName(int id, String name) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.UPDATE_USER_NAME);
			int k = 1;
			ps.setString(k++, name);
			ps.setInt(k++, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_UPDATE_USER_NAME, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_UPDATE_USER_NAME, e);
		} finally {
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Update statement status.
	 * 
	 * @param statementId, status
	 *            id of statement to update status.
	 * @throws DBException
	 */
	public void updateStatementStatus(int statementId, byte status) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.UPDATE_STATEMENT_STATUS);
			int k = 1;
			ps.setInt(k++, status);
			ps.setInt(k++, statementId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_UPDATE_STATEMENT_STATUS, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_UPDATE_STATEMENT_STATUS, e);
		} finally {
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Returns booking list of the given room id.
	 * 
	 * @param id
	 *            room id.
	 * @return List of Booking item entities.
	 * @throws DBException
	 */
	public List<Booking> selectBookingListByRoomId(int id) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		List<Booking> bookingList = new ArrayList<>();
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_ALL_BOOKING_BY_ROOM_ID);
			int k = 1;
			ps.setInt(k++, id);
			set = ps.executeQuery();
			while (set.next()) {
				Booking booking = new Booking();
				int d = 1;
				booking.setId(set.getInt(d++));
				Room room = selectRoomById(set.getInt(d++));
				booking.setRoom(room);
				User user = selectUserById(set.getInt(d++));
				booking.setUser(user);
				booking.setBookingDate(Timestamp.valueOf(set.getString(d++)));
				booking.setAccomodationFee(set.getString(d++));
				booking.setBookingStart(Timestamp.valueOf(set.getString(d++)));
				booking.setBookingEnd(Timestamp.valueOf(set.getString(d++)));
				booking.setStatus(set.getByte(d++));
				booking.setSpecial_status(set.getByte(d++));
				bookingList.add(booking);
			}
			return bookingList;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_BOOKING_LIST_BY_ROOM_ID, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_BOOKING_LIST_BY_ROOM_ID, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}
	
	/**
	 * Returns all bookings.
	 * 
	 * @return List of Booking item entities.
	 * @throws DBException
	 */
	public List<Booking> selectBookingList() throws DBException {
		Connection con = null;
		Statement ps = null;
		ResultSet set = null;
		List<Booking> bookingList = new ArrayList<>();
		try {
			con = poolController.getConnection();
			ps = con.createStatement();
			set = ps.executeQuery(DBQueries.SELECT_ALL_BOOKING);
			while (set.next()) {
				Booking booking = new Booking();
				int d = 1;
				booking.setId(set.getInt(d++));
				Room room = selectRoomById(set.getInt(d++));
				booking.setRoom(room);
				User user = selectUserById(set.getInt(d++));
				booking.setUser(user);
				booking.setBookingDate(Timestamp.valueOf(set.getString(d++)));
				booking.setAccomodationFee(set.getString(d++));
				booking.setBookingStart(Timestamp.valueOf(set.getString(d++)));
				booking.setBookingEnd(Timestamp.valueOf(set.getString(d++)));
				booking.setStatus(set.getByte(d++));
				booking.setSpecial_status(set.getByte(d++));
				bookingList.add(booking);
			}
			return bookingList;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_BOOKING_LIST, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_BOOKING_LIST, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Returns booking query of the given user id.
	 * 
	 * @param userId
	 *            user id.
	 * @return List of BookingQuery item entities.
	 * @throws DBException
	 */
	public List<BookingQuerry> selectBookingQuerryListByUserId(int userId) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		List<BookingQuerry> bookingList = new ArrayList<>();
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_ALL_BOOKING_QUERRY_BY_USER_ID);
			int k = 1;
			ps.setInt(k++, userId);
			set = ps.executeQuery();
			while (set.next()) {
				BookingQuerry bookingQuerry = new BookingQuerry();
				int d = 1;
				bookingQuerry.setId(set.getInt(d++));
				bookingQuerry.setStatus(set.getInt(d++));
				Room room = selectRoomById(set.getInt(d++));
				bookingQuerry.setRoom(room);
				ua.nure.hrybeniuk.finalProject.db.entity.Statement statement = selectStatementById(set.getInt(d++));
				bookingQuerry.setStatement(statement);
				bookingList.add(bookingQuerry);
			}
			return bookingList;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_BOOKING_QUERY_LIST_BY_USER_ID, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_BOOKING_QUERY_LIST_BY_USER_ID, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Returns booking of the given user id.
	 * 
	 * @param id
	 *            user id.
	 * @return List of Booking item entities.
	 * @throws DBException
	 */
	public List<Booking> selectBookingListByUserId(int id) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		List<Booking> bookingList = new ArrayList<>();
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_ALL_BOOKING_BY_USER_ID);
			int k = 1;
			ps.setInt(k++, id);
			set = ps.executeQuery();
			while (set.next()) {
				Booking booking = new Booking();
				int d = 1;
				booking.setId(set.getInt(d++));
				Room room = selectRoomById(set.getInt(d++));
				booking.setRoom(room);
				User user = selectUserById(set.getInt(d++));
				booking.setUser(user);
				booking.setBookingDate(Timestamp.valueOf(set.getString(d++)));
				booking.setAccomodationFee(set.getString(d++));
				booking.setBookingStart(Timestamp.valueOf(set.getString(d++)));
				booking.setBookingEnd(Timestamp.valueOf(set.getString(d++)));
				booking.setStatus(set.getByte(d++));
				booking.setSpecial_status(set.getByte(d++));
				bookingList.add(booking);
			}
			return bookingList;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_BOOKING_LIST_BY_USER_ID, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_BOOKING_LIST_BY_USER_ID, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

	/**
	 * Returns a booking query with the given user id.
	 * 
	 * @param id
	 *            BookingQuerry identifier.
	 * @return BookingQuerry entity.
	 * @throws DBException
	 */
	public BookingQuerry selectBookingQuerryById(int id) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		BookingQuerry bookingQuerry = null;
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_BOOKING_QUERRY_BY_ID);
			int k = 1;
			ps.setInt(k++, id);
			set = ps.executeQuery();
			if (set.next()) {
				bookingQuerry = new BookingQuerry();
				int d = 1;
				bookingQuerry.setId(set.getInt(d++));
				bookingQuerry.setStatus(set.getInt(d++));
				Room room = selectRoomById(set.getInt(d++));
				bookingQuerry.setRoom(room);
				ua.nure.hrybeniuk.finalProject.db.entity.Statement statement = selectStatementById(set.getInt(d++));
				bookingQuerry.setStatement(statement);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_BOOKING_QUERY_BY_ID, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_BOOKING_QUERY_BY_ID, e);
		} finally {
			DBTools.close(ps);
			poolController.close(con);
		}
		return bookingQuerry;
	}

	/**
	 * Insert a BookingQuery entity with given parameters.
	 * 
	 * @param status, roomId, statementId
	 *            insert BookingQuery with given parameters
	 * @throws DBException
	 */
	public void insertBookingQuery(int status, int roomId, int statementId) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.INSERT_BOOKING_QUERY);
			int k = 1;
			ps.setInt(k++, status);
			ps.setInt(k++, roomId);
			ps.setInt(k++, statementId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_INSERT_BOOKING_QUERY, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_INSERT_BOOKING_QUERY, e);
		} finally {
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Insert a given Booking entity .
	 * 
	 * @param booking
	 *            insert Booking entity
	 * @throws DBException
	 */
	public void insertBooking(Booking booking) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;

		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.INSERT_BOOKING, Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			ps.setInt(k++, booking.getRoom().getId());
			ps.setInt(k++, booking.getUser().getId());
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = dateFormater.format(booking.getBookingDate());
			ps.setString(k++, date);
			ps.setString(k++, booking.getAccomodationFee());
			String dateStart = dateFormater.format(booking.getBookingStart());
			ps.setString(k++, dateStart);
			String dateEnd = dateFormater.format(booking.getBookingEnd());
			ps.setString(k++, dateEnd);
			if (ps.executeUpdate() > 0) {
				set = ps.getGeneratedKeys();
				if (set.next()) {
					int id = set.getInt(1);
					booking.setId(id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_INSERT_BOOKING, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_INSERT_BOOKING, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Insert a given Statement entity .
	 * 
	 * @param stat
	 *            insert Statement entity
	 * @throws DBException
	 */
	public void insertStatement(ua.nure.hrybeniuk.finalProject.db.entity.Statement stat) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;

		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.INSERT_STATEMENT, Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			ps.setInt(k++, stat.getUser().getId());
			ps.setInt(k++, stat.getPlacesNum());
			ps.setInt(k++, getRoomTypeId(stat.getRoomType().value()));
			SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStart = dateFormater.format(stat.getResidenceStart());
			ps.setString(k++, dateStart);
			String dateEnd = dateFormater.format(stat.getResidenceEnd());
			ps.setString(k++, dateEnd);

			if (ps.executeUpdate() > 0) {
				set = ps.getGeneratedKeys();
				if (set.next()) {
					int id = set.getInt(1);
					stat.setId(id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_INSERT_STATEMENT, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_INSERT_STATEMENT, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Returns a statement with the given statement id.
	 * 
	 * @param id
	 *            statement identifier.
	 * @return Statement entity.
	 * @throws DBException
	 */
	public ua.nure.hrybeniuk.finalProject.db.entity.Statement selectStatementById(int id) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;

		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_STATEMENT_BY_ID);
			int k = 1;
			ps.setInt(k++, id);
			set = ps.executeQuery();
			ua.nure.hrybeniuk.finalProject.db.entity.Statement stat = null;
			if (set.next()) {
				stat = new ua.nure.hrybeniuk.finalProject.db.entity.Statement();
				int d = 1;
				stat.setId(set.getInt(d++));
				User user = selectUserById(set.getInt(d++));
				stat.setUser(user);
				stat.setPlacesNum(set.getInt(d++));
				stat.setRoomType(getRoomTypeName(set.getInt(d++)));
				stat.setResidenceStart(Timestamp.valueOf(set.getString(d++)));
				stat.setResidenceEnd(Timestamp.valueOf(set.getString(d++)));
				stat.setStatus(set.getByte(d++));
			}

			return stat;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_STATEMENT_BY_ID, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_STATEMENT_BY_ID, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

	/**
	 * Returns a statement list with the given user id.
	 * 
	 * @param id
	 *            user identifier.
	 * @return List of Statement entity.
	 * @throws DBException
	 */
	public List<ua.nure.hrybeniuk.finalProject.db.entity.Statement> selectAllStatementByUserId(int id)
			throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		List<ua.nure.hrybeniuk.finalProject.db.entity.Statement> statList = new ArrayList<>();
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_ALL_STATEMENT_BY_USER_ID);
			int k = 1;
			ps.setInt(k++, id);
			set = ps.executeQuery();
			while (set.next()) {
				ua.nure.hrybeniuk.finalProject.db.entity.Statement stat = new ua.nure.hrybeniuk.finalProject.db.entity.Statement();
				int d = 1;
				stat.setId(set.getInt(d++));
				User user = selectUserById(set.getInt(d++));
				stat.setUser(user);
				stat.setPlacesNum(set.getInt(d++));
				stat.setRoomType(getRoomTypeName(set.getInt(d++)));
				stat.setResidenceStart(Timestamp.valueOf(set.getString(d++)));
				stat.setResidenceEnd(Timestamp.valueOf(set.getString(d++)));
				stat.setStatus(set.getByte(d++));
				statList.add(stat);
			}

			return statList;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_STATEMENT_LIST_BY_USER_ID, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_STATEMENT_LIST_BY_USER_ID, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

	/**
	 * Returns a statement list.
	 * 
	 * @return list of statement entity.
	 * @throws DBException
	 */
	public List<ua.nure.hrybeniuk.finalProject.db.entity.Statement> selectAllStatement() throws DBException {
		Connection con = null;
		Statement ps = null;
		ResultSet set = null;
		List<ua.nure.hrybeniuk.finalProject.db.entity.Statement> statList = new ArrayList<>();
		try {
			con = poolController.getConnection();
			ps = con.createStatement();
			set = ps.executeQuery(DBQueries.SELECT_ALL_STATEMENT);
			while (set.next()) {
				ua.nure.hrybeniuk.finalProject.db.entity.Statement stat = new ua.nure.hrybeniuk.finalProject.db.entity.Statement();
				int d = 1;
				stat.setId(set.getInt(d++));
				User user = selectUserById(set.getInt(d++));
				stat.setUser(user);
				stat.setPlacesNum(set.getInt(d++));
				stat.setRoomType(getRoomTypeName(set.getInt(d++)));
				stat.setResidenceStart(Timestamp.valueOf(set.getString(d++)));
				stat.setResidenceEnd(Timestamp.valueOf(set.getString(d++)));
				stat.setStatus(set.getByte(d++));
				statList.add(stat);
			}

			return statList;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_STATEMENT_LIST, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_STATEMENT_LIST, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

	/**
	 * Returns a rooms list with the given type and places number.
	 * 
	 * @param type, places
	 *            BookingQuerry identifier.
	 * @return BookingQuerry entity.
	 * @throws DBException
	 */
	public List<Room> selectRoomsByOption(RoomTypes type, int places) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_ALL_ROOMS_BY_OPTION);
			int k = 1;
			ps.setInt(k++, places);
			int typeId = getRoomTypeId(type.value());
			ps.setInt(k++, typeId);
			set = ps.executeQuery();
			while (set.next()) {
				Room room = new Room();
				int d = 1;
				room.setId(set.getInt(d++));
				String roomTypeName = getRoomTypeName(set.getInt(d++));
				room.setType(roomTypeName);
				String room_status = getRoomStatusName(set.getInt(d++));
				room.setStatus(room_status);
				room.setPrice(set.getInt(d++));
				room.setPlacesNum(set.getInt(d++));
				room.setPicturePath(set.getString(d++));
				roomList.add(room);
			}

			return roomList;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_ROOMS_LIST_BY_OPTION, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_ROOMS_LIST_BY_OPTION, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

	/**
	 * Returns all rooms .
	 * 
	 * @return List of Room item entities.
	 * @throws DBException
	 */
	public List<Room> selectAllRooms() throws DBException {
		Connection con = null;
		Statement ps = null;
		ResultSet set = null;
		List<Room> roomList = new ArrayList<Room>();
		try {
			con = poolController.getConnection();
			ps = con.createStatement();
			set = ps.executeQuery(DBQueries.SELECT_ALL_ROOM);
			while (set.next()) {
				Room room = new Room();
				int d = 1;
				room.setId(set.getInt(d++));
				String roomTypeName = getRoomTypeName(set.getInt(d++));
				room.setType(roomTypeName);
				String room_status = getRoomStatusName(set.getInt(d++));
				room.setStatus(room_status);
				room.setPrice(set.getInt(d++));
				room.setPlacesNum(set.getInt(d++));
				room.setPicturePath(set.getString(d++));
				roomList.add(room);
			}

			return roomList;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_ROOMS_LIST, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_ROOMS_LIST, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

	/**
	 * Returns a room with the given room id.
	 * 
	 * @param id
	 *            room identifier.
	 * @return Room entity.
	 * @throws DBException
	 */
	public Room selectRoomById(int id) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		Room room = null;
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_ROOM_BY_ID);
			int k = 1;
			ps.setInt(k++, id);

			set = ps.executeQuery();
			if (set.next()) {
				room = new Room();
				int d = 1;
				room.setId(set.getInt(d++));
				String roomTypeName = getRoomTypeName(set.getInt(d++));
				room.setType(roomTypeName);
				String room_status = getRoomStatusName(set.getInt(d++));
				room.setStatus(room_status);
				room.setPrice(set.getInt(d++));
				room.setPlacesNum(set.getInt(d++));
				room.setPicturePath(set.getString(d++));
			}

			return room;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_ROOM_BY_ID, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_ROOM_BY_ID, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

	/**
	 * Returns all users .
	 * 
	 * @return List of User item entities.
	 * @throws DBException
	 */
	public List<User> selectAllUser() throws DBException {
		Connection con = null;
		Statement ps = null;
		ResultSet set = null;
		List<User> userList = new ArrayList<User>();
		try {
			con = poolController.getConnection();
			ps = con.createStatement();
			set = ps.executeQuery(DBQueries.SELECT_ALL_USER);
			while (set.next()) {
				User user = new User();
				int d = 1;
				user.setId(set.getInt(d++));
				user.setFullName(set.getString(d++));
				user.setPhoneNumber(set.getString(d++));
				user.setEmail(set.getString(d++));
				String roleName = getRoleName(set.getInt(d++));
				user.setRole(roleName);
				user.setPassword(set.getString(d++));
				userList.add(user);
			}

			return userList;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_USER_LIST, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_USER_LIST, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Returns a user with the given user id.
	 * 
	 * @param id
	 *            user identifier.
	 * @return User entity.
	 * @throws DBException
	 */
	public User selectUserById(int id) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		User user = null;
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_USER_BY_ID);
			int k = 1;
			ps.setInt(k++, id);

			set = ps.executeQuery();
			if (set.next()) {
				user = new User();
				int d = 1;
				user.setId(set.getInt(d++));
				user.setFullName(set.getString(d++));
				user.setPhoneNumber(set.getString(d++));
				user.setEmail(set.getString(d++));
				String roleName = getRoleName(set.getInt(d++));
				user.setRole(roleName);
				user.setPassword(set.getString(d++));
			}

			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_USER, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_USER, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

	/**
	 * Returns a user with the given user email.
	 * 
	 * @param email
	 *            user email address.
	 * @return User entity.
	 * @throws DBException
	 */
	public User selectUserByEmail(String email) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		User user = null;
		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_USER_BY_EMAIL);
			int k = 1;
			ps.setString(k++, email);

			set = ps.executeQuery();
			if (set.next()) {
				user = new User();
				int d = 1;
				user.setId(set.getInt(d++));
				user.setFullName(set.getString(d++));
				user.setPhoneNumber(set.getString(d++));
				user.setEmail(set.getString(d++));
				String roleName = getRoleName(set.getInt(d++));
				user.setRole(roleName);
				user.setPassword(set.getString(d++));
			}

			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_USER_BY_EMAIL, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

	/**
	 * Insert a given User entity .
	 * 
	 * @param user
	 *            insert User entity
	 * @throws DBException
	 */
	public void insertUser(User user) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;

		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			ps.setString(k++, user.getFullName());
			ps.setString(k++, user.getPhoneNumber());
			ps.setString(k++, user.getEmail());
			int userRoleId = getRoleId(user.getRole().value());
			ps.setInt(k++, userRoleId);
			ps.setString(k++, user.getPassword());

			if (ps.executeUpdate() > 0) {
				set = ps.getGeneratedKeys();
				if (set.next()) {
					int id = set.getInt(1);
					user.setId(id);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_INSERT_USER, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_INSERT_USER, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}
	}

	/**
	 * Returns a room status id with the given rooms status text name.
	 * 
	 * @param roomStatus
	 *            rooms status text name.
	 * @return room status id.
	 * @throws DBException
	 */
	@SuppressWarnings("unused")
	private int getRoomStatusId(String roomStatus) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;

		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_ROOM_STATUS_ID);
			int k = 1;
			ps.setString(k++, roomStatus);
			set = ps.executeQuery();
			set.next();
			int result = set.getInt(1);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_ROOM_STATUS_ID, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_ROOM_STATUS_ID, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

	/**
	 * Returns a room status name with the given room status id.
	 * 
	 * @param id
	 *            room status id.
	 * @return room status text name.
	 * @throws DBException
	 */
	private String getRoomStatusName(int id) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;

		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_ROOM_STATUS_NAME);
			int k = 1;
			ps.setInt(k++, id);
			set = ps.executeQuery();
			set.next();
			String result = set.getString(1);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_ROOM_STATUS_NAME, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_ROOM_STATUS_NAME, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

	/**
	 * Returns a room type id with the given room type text name.
	 * 
	 * @param typeName
	 *            rooms type text name.
	 * @return room type id.
	 * @throws DBException
	 */
	private int getRoomTypeId(String typeName) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;

		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_ROOM_TYPE_ID);
			int k = 1;
			ps.setString(k++, typeName);
			set = ps.executeQuery();
			set.next();
			int result = set.getInt(1);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_ROOM_TYPE_ID, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_ROOM_TYPE_ID, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

	/**
	 * Returns a room type name with the given room type id.
	 * 
	 * @param id
	 *            room type id.
	 * @return room type text name.
	 * @throws DBException
	 */
	private String getRoomTypeName(int id) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;

		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_ROOM_TYPE_NAME);
			int k = 1;
			ps.setInt(k++, id);
			set = ps.executeQuery();
			set.next();
			String result = set.getString(1);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_ROOM_TYPE_NAME, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_ROOM_TYPE_NAME, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

	/**
	 * Returns a user role name with the given user role id.
	 * 
	 * @param id
	 *            user's role id.
	 * @return role text name.
	 * @throws DBException
	 */
	private String getRoleName(int id) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;

		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_USER_ROLE_NAME);
			int k = 1;
			ps.setInt(k++, id);
			set = ps.executeQuery();
			set.next();
			String result = set.getString(1);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_USER_ROLE_NAME, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_USER_ROLE_NAME, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

	/**
	 * Returns a user role id with the given user role text name.
	 * 
	 * @param roleName
	 *            user's role text name.
	 * @return role id.
	 * @throws DBException
	 */
	private int getRoleId(String roleName) throws DBException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet set = null;

		try {
			con = poolController.getConnection();
			ps = con.prepareStatement(DBQueries.SELECT_USER_ROLE_ID);
			int k = 1;
			ps.setString(k++, roleName);
			set = ps.executeQuery();
			set.next();
			int result = set.getInt(1);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			LOG.error(ErrorMessages.ERR_CANNOT_OBTAIN_USER_ROLE_ID, e);
			throw new DBException(ErrorMessages.ERR_CANNOT_OBTAIN_USER_ROLE_ID, e);
		} finally {
			DBTools.close(set);
			DBTools.close(ps);
			poolController.close(con);
		}

	}

}
