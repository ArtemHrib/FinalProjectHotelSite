package ua.nure.hrybeniuk.finalProject.db.entity;

/**
 * BookingQuerry entity.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class BookingQuerry extends Entity {

	private static final long serialVersionUID = -3534051746400955869L;

	private int status;

	private Room room;

	private Statement statement;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	@Override
	public String toString() {
		return "BookingQuerry [status=" + status 
				+ ", room=" + room 
				+ ", statement=" + statement + "]";
	}
	
	
	
}
