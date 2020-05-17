package ua.nure.hrybeniuk.finalProject.web.command;

import java.io.IOException;

import java.sql.Timestamp;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.hrybeniuk.finalProject.db.DBManager;
import ua.nure.hrybeniuk.finalProject.db.entity.Booking;
import ua.nure.hrybeniuk.finalProject.db.entity.BookingQuerry;
import ua.nure.hrybeniuk.finalProject.exception.AppException;
/**
 * Booking query accepting for user.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class BookingQuerryAcception extends Command {

	private static final long serialVersionUID = 7408576381747485369L;
	private static final Logger LOG = Logger.getLogger(BookingQuerryAcception.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		ServletContext context = request.getSession().getServletContext();
		int bookingQuerryId = Integer.valueOf(request.getParameter("bookingQuerryId"));
		LOG.trace("booking query id = " + bookingQuerryId);
		DBManager manager = null;
		BookingQuerry bookingQuerry = null;
		
		//make BookingQuery entity
		manager = DBManager.getInstance();
		bookingQuerry = manager.selectBookingQuerryById(bookingQuerryId);
		Booking booking = new Booking();
		booking.setRoom(bookingQuerry.getRoom());
		booking.setUser(bookingQuerry.getStatement().getUser());
		booking.setBookingDate(new Timestamp(System.currentTimeMillis()));
		Long startTime = bookingQuerry.getStatement().getResidenceStart().getTime();
		Long endTime = bookingQuerry.getStatement().getResidenceEnd().getTime();
		String accommodationFee = String.valueOf(((endTime - startTime)
				/ Long.valueOf(context.getInitParameter("dayMil")) * bookingQuerry.getRoom().getPrice()));
		booking.setAccomodationFee(accommodationFee);
		booking.setBookingStart(bookingQuerry.getStatement().getResidenceStart());
		booking.setBookingEnd(bookingQuerry.getStatement().getResidenceEnd());
		LOG.trace("Created booking" + booking);
		
		//insert BookingQuery entity to DB
		manager.insertBooking(booking);
		manager.updateBookingQuerryStatus(bookingQuerry.getId(), 1);
		manager.updateRoomStatusById(booking.getRoom().getId(), 2);
		LOG.debug("Command finished with redirection to AccountCommand");
		return CommandContainer.get("account").execute(request, response);
	}

}
