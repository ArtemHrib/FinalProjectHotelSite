package ua.nure.hrybeniuk.finalProject.db.entity;

import java.sql.Timestamp;

/**
 * Booking entity.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class Booking extends Entity {

	private static final long serialVersionUID = 2769114310462986285L;

	private Room room;

	private User user;

	private String accomodationFee;

	private Timestamp bookingDate;

	private Timestamp bookingStart;

	private Timestamp bookingEnd;

	private byte status;
	
	private byte special_status;

	public byte getSpecial_status() {
		return special_status;
	}

	public void setSpecial_status(byte special_status) {
		this.special_status = special_status;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Timestamp getBookingStart() {
		return bookingStart;
	}

	public void setBookingStart(Timestamp bookingStart) {
		this.bookingStart = bookingStart;
	}

	public Timestamp getBookingEnd() {
		return bookingEnd;
	}

	public void setBookingEnd(Timestamp bookingEnd) {
		this.bookingEnd = bookingEnd;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Timestamp date) {
		this.bookingDate = date;
	}

	public String getAccomodationFee() {
		return accomodationFee;
	}

	public void setAccomodationFee(String accomodationFee) {
		this.accomodationFee = accomodationFee;
	}

	@Override
	public String toString() {
		return "Booking [room=" + room.getId() + ", user=" + user.getFullName() + ", bookingDate=" + bookingDate
				+ ", accomodationFee=" + accomodationFee + "]";
	}
}
