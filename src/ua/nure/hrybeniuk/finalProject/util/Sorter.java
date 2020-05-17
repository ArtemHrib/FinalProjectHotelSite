package ua.nure.hrybeniuk.finalProject.util;

import java.util.Comparator;

import ua.nure.hrybeniuk.finalProject.db.entity.Room;

/**
 * Util class - Comparators for entities sorting
 * 
 * @author A.Hrybeniuk
 *
 */
public final class Sorter {

	private Sorter() {
	}

	public static final Comparator<Room> SORT_ROOMS_BY_ID = new Comparator<Room>() {
		@Override
		public int compare(Room o1, Room o2) {
			return o1.getId() - o2.getId();
		}
	};
	
	public static final Comparator<Room> SORT_ROOMS_BY_PRICE = new Comparator<Room>() {
		@Override
		public int compare(Room o1, Room o2) {
			return o1.getPrice() - o2.getPrice();
		}
	};
	
	public static final Comparator<Room> SORT_ROOMS_BY_PLACES = new Comparator<Room>() {
		@Override
		public int compare(Room o1, Room o2) {
			return o1.getPlacesNum() - o2.getPlacesNum();
		}
	};
	
	public static final Comparator<Room> SORT_ROOMS_BY_TYPE = new Comparator<Room>() {
		@Override
		public int compare(Room o1, Room o2) {
			return o1.getType().compareTo(o2.getType());
		}
	};
	
	public static final Comparator<Room> SORT_ROOMS_BY_STATUS = new Comparator<Room>() {
		@Override
		public int compare(Room o1, Room o2) {
			return o1.getStatus().compareTo(o2.getStatus());
		}
	};
}
