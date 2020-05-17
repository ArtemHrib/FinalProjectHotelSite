package ua.nure.hrybeniuk.finalProject.web.command;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.hrybeniuk.finalProject.db.DBManager;
import ua.nure.hrybeniuk.finalProject.db.entity.Statement;
import ua.nure.hrybeniuk.finalProject.db.entity.User;
import ua.nure.hrybeniuk.finalProject.exception.AppException;
/**
 * Statement creator.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class StatementCreateCommand extends Command {

	private static final long serialVersionUID = 8558235475266335050L;
	private static final Logger LOG = Logger.getLogger(StatementCreateCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		DBManager manager = null;
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		LOG.trace("User id = " + userId);
		Statement statement = new Statement();
		User user = new User();
		user.setId(userId);
		statement.setUser(user);
		int placesNum = Integer.valueOf(request.getParameter("bookingPlaceNum"));
		statement.setPlacesNum(placesNum);
		statement.setRoomType(request.getParameter("roomType"));
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStart = dateFormater.format(Date.valueOf(request.getParameter("checkInDate")));
		statement.setResidenceStart(Timestamp.valueOf(dateStart));
		String dateEnd = dateFormater.format(Date.valueOf(request.getParameter("checkOutDate")));
		statement.setResidenceEnd(Timestamp.valueOf(dateEnd));
		LOG.trace("Created statement: " + statement);
		manager = DBManager.getInstance();
		manager.insertStatement(statement);
		LOG.trace("Inserted in DB statement: " + statement);

		throw new AppException("Your booking request is accepted. Please, wait for the manager");
	}

}
