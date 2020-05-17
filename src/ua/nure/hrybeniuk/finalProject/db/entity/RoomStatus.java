package ua.nure.hrybeniuk.finalProject.db.entity;

import java.util.Locale;

/**
 * Room Status entity.
 * 
 * @author A.Hrybeniuk
 * 
 */
public enum RoomStatus {
	FREE("Free"), RESERVED("Reserved"), OCCUPIED("Occupied"), INACCESSIBLE("Inaccessible");

	private String value;

	RoomStatus(String status) {
		this.value = status;
	}

	static RoomStatus getStatus(String status) {
		String roomStatusUpper = status.toUpperCase(Locale.getDefault());
		return RoomStatus.valueOf(roomStatusUpper);
	}

	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}
}
