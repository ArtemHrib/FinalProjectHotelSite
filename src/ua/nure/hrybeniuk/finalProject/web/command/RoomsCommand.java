package ua.nure.hrybeniuk.finalProject.web.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.hrybeniuk.finalProject.Path;
import ua.nure.hrybeniuk.finalProject.db.DBManager;
import ua.nure.hrybeniuk.finalProject.db.entity.Room;
import ua.nure.hrybeniuk.finalProject.exception.AppException;
import ua.nure.hrybeniuk.finalProject.util.Sorter;

/**
 * Rooms preparation.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class RoomsCommand extends Command {
	private static final long serialVersionUID = -4608820782897781427L;
	private static final Logger LOG = Logger.getLogger(RoomsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
		LOG.debug("Command starts");

		String sortType = request.getParameter("rooms_sort_type");
		if (sortType == null) {
			sortType = "id";
		}
		DBManager dbm;
		List<Room> rooms = null;

		dbm = DBManager.getInstance();
		rooms = dbm.selectAllRooms();
		LOG.trace("Found in DB: rooms list: " + rooms);

		if (rooms == null) {
			LOG.debug("Command finished");
			return Path.PAGE_ROOM_PAGE;
		}
		switch (sortType) {
		case "id": {
			rooms.sort(Sorter.SORT_ROOMS_BY_ID);
		}
			break;
		case "price": {
			rooms.sort(Sorter.SORT_ROOMS_BY_PRICE);
		}
			break;
		case "places_number": {
			rooms.sort(Sorter.SORT_ROOMS_BY_PLACES);
		}
			break;
		case "type": {
			rooms.sort(Sorter.SORT_ROOMS_BY_TYPE);
		}
			break;
		case "status": {
			rooms.sort(Sorter.SORT_ROOMS_BY_STATUS);
		}
			break;

		}
		LOG.trace("Rooms list was sorted by " + sortType);
		HttpSession session = request.getSession();
		session.setAttribute("roomsList", rooms);
		LOG.trace("Set the session attribute: roomsList: " + rooms);
		LOG.debug("Command finished");
		return Path.PAGE_ROOM_PAGE;
	}

}
