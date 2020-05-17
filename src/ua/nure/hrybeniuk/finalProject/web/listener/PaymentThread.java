package ua.nure.hrybeniuk.finalProject.web.listener;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.hrybeniuk.finalProject.db.DBManager;
import ua.nure.hrybeniuk.finalProject.db.entity.Booking;
import ua.nure.hrybeniuk.finalProject.exception.DBException;

/**
 * Payment and status checker.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class PaymentThread implements Runnable {
	private DBManager manager = null;
	private static final Logger LOG = Logger.getLogger(PaymentThread.class);

	public PaymentThread() {
	}

	@Override
	public void run() {
		LOG.debug("PaymentThread starts");
		Long timeNow = null;
		try {
			timeNow = new Date().getTime();
			manager = DBManager.getInstance();
			List<Booking> bookings = manager.selectBookingList();
			LOG.trace("DB found booking list: " + bookings);
			for (Booking booking : bookings) {
				int roomId = booking.getRoom().getId();
				if (booking.getBookingEnd().getTime() <= timeNow && booking.getStatus() == 0) {
					manager.updateBookingSpecialStatus(booking.getId(), 1);
					LOG.trace("Deleted booking from DB by id = " + booking.getId());
					if (isFreeFromBookings(roomId)) {
						manager.updateRoomStatusById(roomId, 1);
						LOG.trace("Updated room status by id = " + roomId);
					}
				} else if (booking.getStatus() == 1 && booking.getBookingEnd().getTime() < timeNow) {

					if (isFreeFromBookings(roomId)) {
						manager.updateRoomStatusById(roomId, 1);
						LOG.trace("Updated room status by id = " + roomId);
					}
				} else if (booking.getStatus() == 1 && booking.getBookingStart().getTime() < timeNow
						&& timeNow < booking.getBookingEnd().getTime()
						&& !booking.getRoom().getStatus().equals("Occupied")) {
					manager.updateRoomStatusById(roomId, 3);
					LOG.trace("Updated room status by id = " + roomId);
				}
			}
			LOG.debug("PaymentThread finished");
		} catch (DBException e) {
			e.printStackTrace();
			LOG.error("PaymentThread finished with error: ", e);
		}

	}

	private static boolean isFreeFromBookings(int roomId) throws DBException {
		DBManager manager = DBManager.getInstance();
		return manager.selectBookingListByRoomId(roomId).isEmpty();
	}

}
