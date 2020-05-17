package ua.nure.hrybeniuk.finalProject.db.entity;

import java.util.Locale;

/**
 * Room Type entity.
 * 
 * @author A.Hrybeniuk
 * 
 */
public enum RoomTypes {
	STANDART("standart"), APARTMENT("apartment"), STUDIO("studio"), FAMILY_ROOM("family_room"),
	FAMILY_STUDIO("family_studio"), DELUX("delux"), SUITE("suite"), ROYAL_SUITE("royal_suite");

	private String value;

	RoomTypes(String value) {
		this.value = value;
	}

	static RoomTypes getType(String type) {
		String roomTypeUpper = type.toUpperCase(Locale.getDefault());
		return RoomTypes.valueOf(roomTypeUpper);
	}

	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String value() {
		return value;
	}

}
