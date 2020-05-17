package ua.nure.hrybeniuk.finalProject.web.command;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.hrybeniuk.finalProject.db.DBManager;
import ua.nure.hrybeniuk.finalProject.db.entity.Booking;
import ua.nure.hrybeniuk.finalProject.db.entity.Room;
import ua.nure.hrybeniuk.finalProject.db.entity.User;
import ua.nure.hrybeniuk.finalProject.exception.AppException;
import ua.nure.hrybeniuk.finalProject.exception.DBException;

/**
 * Booking creator.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class BookingsCreateCommand extends Command {

	private static final long serialVersionUID = 1488320788011431366L;
	private static final Logger LOG = Logger.getLogger(BookingsCreateCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		HttpSession session = request.getSession();
		ServletContext context = request.getSession().getServletContext();
		DBManager manager = null;
		int roomId = Integer.valueOf(request.getParameter("roomId"));
		LOG.trace("Room id = " + roomId);
		int userId = (int) session.getAttribute("userId");
		LOG.trace("User id = " + userId);
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStartFormated = dateFormater.format(Date.valueOf((request.getParameter("checkInRoom"))));
		Timestamp start = Timestamp.valueOf(dateStartFormated);
		String dateEndFormated = dateFormater.format(Date.valueOf((request.getParameter("checkOutRoom"))));
		Timestamp end = Timestamp.valueOf(dateEndFormated);

		manager = DBManager.getInstance();

		// make sure if room is free for given dates
		boolean isFree = isFreeRoom(roomId, start, end);

		if (isFree) {
			// create Booking entity
			Booking booking = new Booking();
			Room room = manager.selectRoomById(roomId);
			booking.setRoom(room);
			User user = manager.selectUserById(userId);
			booking.setUser(user);
			booking.setBookingDate(new Timestamp(System.currentTimeMillis()));
			Long startTime = start.getTime();
			Long endTime = end.getTime();
			String accommodationFee = String.valueOf(
					((endTime - startTime) / Long.valueOf(context.getInitParameter("dayMil"))) * room.getPrice());
			booking.setAccomodationFee(accommodationFee);
			booking.setBookingStart(start);
			booking.setBookingEnd(end);

			// insert Booking to DB
			manager.insertBooking(booking);
			LOG.trace("Booking created and iserted to the DB");
			manager.updateRoomStatusById(roomId, 2);
			LOG.debug("Command finished");
			throw new AppException("You booked room №" + roomId + ". You have 2 days for payment");
		} else {
			LOG.trace("Booking didn't create");
			LOG.debug("Command finished");
			throw new AppException(
					"The room №" + roomId + " will be occupied at this type.<br>Please, choose another room or date");
		}

	}

	private static boolean isFreeRoom(int roomId, Timestamp start, Timestamp end) throws DBException {
		Long startTime = start.getTime();
		Long endTime = end.getTime();
		DBManager manager = DBManager.getInstance();
		boolean result = true;
		List<Booking> bookings = manager.selectBookingListByRoomId(roomId);
		for (int i = 0; i < bookings.size(); i++) {
			Long bookedStart = bookings.get(i).getBookingStart().getTime();
			Long bookedEnd = bookings.get(i).getBookingEnd().getTime();
			if (startTime < bookedStart && endTime < bookedStart || startTime > bookedEnd && endTime > bookedEnd) {
				continue;
			} else {
				result = false;
			}
		}

		return result;
	}

}
