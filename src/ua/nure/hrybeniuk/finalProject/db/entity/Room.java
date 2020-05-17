package ua.nure.hrybeniuk.finalProject.db.entity;

/**
 * Room entity.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class Room extends Entity implements Comparable<Room> {

	private static final long serialVersionUID = 8765572237814355563L;

	private RoomTypes type;

	private RoomStatus status;

	private int price;

	private int placesNum;

	private String picturePath;

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public String getType() {
		return type.value();
	}

	public void setType(String type) {
		this.type = RoomTypes.getType(type);
	}

	public String getStatus() {
		return status.value();
	}

	public void setStatus(String status) {
		this.status = RoomStatus.getStatus(status);
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPlacesNum() {
		return placesNum;
	}

	public void setPlacesNum(int placesNum) {
		this.placesNum = placesNum;
	}

	@Override
	public boolean equals(Object obj) {
		Room room = (Room) obj;
		return this.getId() == room.getId();
	}

	@Override
	public String toString() {
		return "Room [id =" + getId() + ", type=" + type.value() + ", status=" + status.value() + ", price=" + price
				+ ", placesNum=" + placesNum + "]";
	}

	@Override
	public int compareTo(Room o) {
		return this.getId() - o.getId();
	}

}
