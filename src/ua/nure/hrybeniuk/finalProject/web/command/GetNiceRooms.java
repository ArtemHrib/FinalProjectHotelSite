package ua.nure.hrybeniuk.finalProject.web.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.hrybeniuk.finalProject.Path;
import ua.nure.hrybeniuk.finalProject.db.DBManager;
import ua.nure.hrybeniuk.finalProject.db.entity.Booking;
import ua.nure.hrybeniuk.finalProject.db.entity.Room;
import ua.nure.hrybeniuk.finalProject.db.entity.Statement;
import ua.nure.hrybeniuk.finalProject.exception.AppException;
import ua.nure.hrybeniuk.finalProject.exception.DBException;

/**
 * Nice room from client's request for manager.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class GetNiceRooms extends Command {

	private static final long serialVersionUID = -1728679495386404493L;
	private static final Logger LOG = Logger.getLogger(GetNiceRooms.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		HttpSession session = request.getSession();
		int statId = Integer.valueOf(request.getParameter("statement-id-get"));
		LOG.trace("Statement id = " + statId);
		session.setAttribute("statementId", statId);
		LOG.trace("Set the session attribute: statementId: " + statId);
		DBManager manager = null;

		List<Room> roomList = null;

		manager = DBManager.getInstance();
		Statement userStat = manager.selectStatementById(statId);
		LOG.trace("Found in DB: Statement: " + userStat);
		roomList = manager.selectRoomsByOption(userStat.getRoomType(), userStat.getPlacesNum());
		LOG.trace("Found in DB: Room list: " + roomList);
		roomList = niceRoomSort(userStat, roomList);
		LOG.trace("Founded selected room: " + roomList);

		session.setAttribute("niceRoomList", roomList);
		return Path.PAGE_ACC_MANAGER_PAGE;
	}

	private static List<Room> niceRoomSort(Statement stat, List<Room> rooms) throws DBException {
		DBManager manager = DBManager.getInstance();
		List<Room> niceRooms = new ArrayList<Room>();
		Long startTime = stat.getResidenceStart().getTime();
		Long endTime = stat.getResidenceEnd().getTime();
		for (Room room : rooms) {
			List<Booking> bookings = manager.selectBookingListByRoomId(room.getId());
			for (int i = 0; i < bookings.size(); i++) {
				Long bookedStart = bookings.get(i).getBookingStart().getTime();
				Long bookedEnd = bookings.get(i).getBookingEnd().getTime();
				if (startTime < bookedStart && endTime < bookedStart || startTime > bookedEnd && endTime > bookedEnd) {
					if (i == bookings.size() - 1) {
						niceRooms.add(room);
					}
				} else {
					break;
				}
			}
			if (bookings.size() == 0) {
				niceRooms.add(room);
			}
		}
		return niceRooms;
	}
}
