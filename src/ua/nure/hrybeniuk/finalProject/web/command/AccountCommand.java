package ua.nure.hrybeniuk.finalProject.web.command;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.hrybeniuk.finalProject.Path;
import ua.nure.hrybeniuk.finalProject.db.DBManager;
import ua.nure.hrybeniuk.finalProject.db.entity.Booking;
import ua.nure.hrybeniuk.finalProject.db.entity.BookingQuerry;
import ua.nure.hrybeniuk.finalProject.db.entity.Room;
import ua.nure.hrybeniuk.finalProject.db.entity.Statement;
import ua.nure.hrybeniuk.finalProject.db.entity.User;
import ua.nure.hrybeniuk.finalProject.exception.AppException;
import ua.nure.hrybeniuk.finalProject.exception.DBException;

/**
 * Account cabinet.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class AccountCommand extends Command {

	private static final long serialVersionUID = -44165909771560397L;
	private static final Logger LOG = Logger.getLogger(AccountCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		int userId = (int) session.getAttribute("userId");
		LOG.trace("user id = " + userId);
		LOG.trace("defines user role = " + role);
		DBManager manager = null;

		manager = DBManager.getInstance();

		String result = Path.PAGE_ERROR_PAGE;

		switch (role) {
		case "Client": {
			// set client's items to session attributes
			List<Statement> statementList = null;
			List<Booking> bookingList = null;
			List<BookingQuerry> bookingQuerryList = null;
			statementList = manager.selectAllStatementByUserId(userId);
			LOG.trace("Found in DB: statementList:" + statementList);
			bookingList = manager.selectBookingListByUserId(userId);
			LOG.trace("Found in DB: bookingList:" + bookingList);
			bookingQuerryList = manager.selectBookingQuerryListByUserId(userId);
			LOG.trace("Found in DB: bookingQueryList:" + bookingQuerryList);

			session.setAttribute("bookingQuerryList", bookingQuerryList);
			LOG.trace("Set the session attribute: bookingQuerryList --> " + bookingQuerryList);
			session.setAttribute("bookingList", bookingList);
			LOG.trace("Set the session attribute: bookingList --> " + bookingList);
			session.setAttribute("statementList", statementList);
			LOG.trace("Set the session attribute: statementList --> " + statementList);
			int bookingQueryNum = bookingQueryCounter(bookingQuerryList);
			session.setAttribute("BookingQuerryNumber", bookingQueryNum);
			LOG.trace("Set the session attribute: BookingQuerryNumber: " + bookingQueryNum);
			result = Path.PAGE_ACC_USER_PAGE;
		}
			break;
		case "Manager": {
			// set manager's items to session attributes
			List<Statement> statementList = null;
			List<Booking> bookingList = null;
			Map<Room, Integer> roomStatistics = null;
			Map<Room, Integer> roomStatistics2 = null;
			
			roomStatistics2 = getRoomStatistics2();
			session.setAttribute("roomStatistics2", roomStatistics2);
			LOG.trace("Set the session attribute: roomStatistics --> " + roomStatistics2);
			
			roomStatistics = getRoomStatistics();
			LOG.trace("Found in DB: roomStatistics:" + roomStatistics);
			statementList = manager.selectAllStatement();
			LOG.trace("Found in DB: statementList:" + statementList);
			bookingList = manager.selectBookingList();
			LOG.trace("Found in DB: bookingList:" + bookingList);

			session.setAttribute("roomStatistics", roomStatistics);
			LOG.trace("Set the session attribute: roomStatistics --> " + roomStatistics);
			session.setAttribute("bookingList", bookingList);
			LOG.trace("Set the session attribute: bookingList --> " + bookingList);
			session.setAttribute("statementsList", statementList);
			LOG.trace("Set the session attribute: statementList --> " + statementList);
			result = Path.PAGE_ACC_MANAGER_PAGE;
		}
			break;

		}
		if (session.getAttribute("userName") == null) {
			User user = manager.selectUserById(userId);
			session.setAttribute("userName", user.getFullName());
		}

		LOG.debug("Command finished");
		return result;
	}

	// number of query for user
	private static int bookingQueryCounter(List<BookingQuerry> list) {
		int result = 0;
		for (BookingQuerry bq : list) {
			if (bq.getStatus() == 0) {
				result++;
			}
		}
		return result;
	}

	//Map with room and money earned 
		private static Map<Room, Integer> getRoomStatistics2() throws DBException {
			DBManager manager = DBManager.getInstance();
			Map<Room, Integer> roomStatistics = new TreeMap<>();
			List<Room> rooms = manager.selectAllRooms();
			List<Booking> bookings = manager.selectBookingList();

			for (Room room : rooms) {
				roomStatistics.put(room, 0);
				for (Booking booking : bookings) {
					if (booking.getRoom().getId() == room.getId() && booking.getStatus() == 1) {
						int current = roomStatistics.get(room);
						roomStatistics.put(room, current + Integer.valueOf(booking.getAccomodationFee()));
					}
				}
			}
			return roomStatistics;

		} 
	
	//Map with room and booking number of this room
	private static Map<Room, Integer> getRoomStatistics() throws DBException {
		DBManager manager = DBManager.getInstance();
		Map<Room, Integer> roomStatistics = new TreeMap<>();
		List<Room> rooms = manager.selectAllRooms();
		List<Booking> bookings = manager.selectBookingList();

		for (Room room : rooms) {
			roomStatistics.put(room, 0);
			for (Booking booking : bookings) {
				if (booking.getRoom().getId() == room.getId()) {
					int current = roomStatistics.get(room);
					roomStatistics.put(room, current + 1);
				}
			}
		}
		return roomStatistics;

	}
}
