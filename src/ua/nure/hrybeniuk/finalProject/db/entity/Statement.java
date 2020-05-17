package ua.nure.hrybeniuk.finalProject.db.entity;

import java.sql.Timestamp;

/**
 * Statement entity.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class Statement extends Entity {

	private static final long serialVersionUID = -2207581237254034411L;

	private User user;

	private int placesNum;

	private RoomTypes roomType;

	private Timestamp residenceStart;
	
	private Timestamp residenceEnd;

	private byte status;
	
	public byte getStatus() {
		return status;
	}
	
	public void setStatus(byte status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPlacesNum() {
		return placesNum;
	}

	public void setPlacesNum(int placesNum) {
		this.placesNum = placesNum;
	}

	public RoomTypes getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = RoomTypes.getType(roomType);
	}

	public void setResidenceStart(Timestamp start) {
		this.residenceStart = start;
	}
	
	public void setResidenceEnd(Timestamp end) {
		this.residenceEnd = end;
	}
	
	public Timestamp getResidenceStart() {
		return residenceStart;
	}
	
	public Timestamp getResidenceEnd() {
		return residenceEnd;
	}
	
	@Override
	public String toString() {
		return "Statement [user=" + user.getFullName() + ", placesNum=" + placesNum + ", roomType=" + roomType.value()
				+ ", residenceStart=" + residenceStart + ", residenceEnd=" + residenceEnd+"]";
	}

}
