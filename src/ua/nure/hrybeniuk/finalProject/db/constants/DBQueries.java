package ua.nure.hrybeniuk.finalProject.db.constants;

/**
 * DB queries dictionary
 * 
 * @author A.Hrybeniuk
 * 
 */
public class DBQueries {

	// //////////////////////////////////////////////////////////
	// SQL queries
	// //////////////////////////////////////////////////////////
	private DBQueries() {
	}

	// delete items
	public static final String DELETE_BOOKING_BY_ID = "delete from booking where id = ?;";

	// select items
	public static final String SELECT_BOOKING_QUERRY_BY_ID = "Select * from booking_query where id = ?;";
	public static final String SELECT_ALL_BOOKING_QUERRY_BY_USER_ID = "Select * from booking_query bq,statement s where bq.statement_id = s.id AND s.user_id = ?;";
	public static final String SELECT_ALL_STATEMENT_BY_USER_ID = "Select * from statement where user_id = ?;";
	public static final String SELECT_ALL_BOOKING = "Select * from booking;";
	public static final String SELECT_ALL_BOOKING_BY_ROOM_ID = "Select * from booking where room_id = ?;";
	public static final String SELECT_ALL_BOOKING_BY_USER_ID = "Select * from booking where user_id = ?;";
	public static final String SELECT_STATEMENT_BY_ID = "Select * from statement where id = ?;";
	public static final String SELECT_USER_BY_ID = "Select * from user where id = ?;";
	public static final String SELECT_USER_BY_EMAIL = "Select * from user where email = ?;";
	public static final String SELECT_ALL_USER = "Select * from user;";
	public static final String SELECT_ROOM_BY_ID = "Select * from room where id = ?;";
	public static final String SELECT_ALL_ROOM = "Select * from room;";
	public static final String SELECT_ALL_STATEMENT = "Select * from statement;";
	public static final String SELECT_ALL_ROOMS_BY_OPTION = "Select * from room where num_places = ? AND room_type_id = ? AND NOT status_id = 4;";

	// util items
	public static final String SELECT_ROOM_STATUS_ID = "Select id from status_room where status = ?;";
	public static final String SELECT_ROOM_STATUS_NAME = "Select status from status_room where id = ?;";
	public static final String SELECT_USER_ROLE_ID = "Select id from user_roles where role_name = ?;";
	public static final String SELECT_USER_ROLE_NAME = "Select role_name from user_roles where id = ?;";
	public static final String SELECT_ROOM_TYPE_ID = "Select id from type_room where type = ?;";
	public static final String SELECT_ROOM_TYPE_NAME = "Select type from type_room where id = ?;";

	// insert items
	public static final String INSERT_USER = "Insert into user (user_name,phone_number,email,user_role,password) values (?,?,?,?,?);";
	public static final String INSERT_STATEMENT = "Insert into statement (user_id,number_places,type_room_id,residence_start,residence_end) values (?,?,?,?,?)";
	public static final String INSERT_BOOKING = "Insert into booking (room_id,user_id,time_booking,accommodation_fee,booking_start,booking_end) values (?,?,?,?,?,?)";
	public static final String INSERT_BOOKING_QUERY = "Insert into booking_query(status,room_id,statement_id) values (?,?,?)";

	//update items
	public static final String UPDATE_STATEMENT_STATUS = "Update statement set status=? where id=?";
	public static final String UPDATE_USER_NAME = "Update user set user_name=? where id=?";
	public static final String UPDATE_USER_PASSWORD = "Update user set password=? where id=?";
	public static final String UPDATE_USER_PHONE = "Update user set phone_number=? where id=?";
	public static final String UPDATE_BOOKING_STATUS = "Update booking set status=? where id=?";
	public static final String UPDATE_BOOKING_QUERRY_STATUS = "Update booking_query set status=? where id=?";
	public static final String UPDATE_ROOM_STATUS_BY_ID = "Update room set status_id = ? where id=?;";
	public static final String UPDATE_BOOKING_SPECIAL_STATUS = "Update booking set special_status=? where id=?";

}
