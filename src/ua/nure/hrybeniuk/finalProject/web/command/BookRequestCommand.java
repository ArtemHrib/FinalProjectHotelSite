package ua.nure.hrybeniuk.finalProject.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.hrybeniuk.finalProject.Path;
import ua.nure.hrybeniuk.finalProject.db.DBManager;
import ua.nure.hrybeniuk.finalProject.db.entity.Statement;
import ua.nure.hrybeniuk.finalProject.exception.AppException;
/**
 * BookingQuery creator.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class BookRequestCommand extends Command {
	private static final long serialVersionUID = 4479004615864568706L;
	private static final Logger LOG = Logger.getLogger(BookRequestCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		int statId = Integer.valueOf(request.getParameter("statement-id"));
		LOG.trace("Statement id = " + statId);
		int roomId = Integer.valueOf(request.getParameter("roomChooseStat"));
		LOG.trace("Room id = " + roomId);
		List<Statement> statementsList = null;
		DBManager manager = null;
		manager = DBManager.getInstance();
		if (roomId == 0) {
			manager.updateStatementStatus(statId, (byte) 2);
			LOG.trace("Statement status was updated, status = " + 2);
		} else {

			manager.insertBookingQuery(0, roomId, statId);
			LOG.trace("Booking query was inserted");
			manager.updateStatementStatus(statId, (byte) 1);
			LOG.trace("Statement status was updated, status = " + 1);
		}
		statementsList = manager.selectAllStatement();
		LOG.trace("Found in DB: statementsList: " + statementsList);

		HttpSession session = request.getSession();
		session.setAttribute("statementsList", statementsList);
		LOG.trace("Set the session attribute: statementsList: " + statementsList);

		LOG.debug("Command finished");
		return Path.PAGE_ACC_MANAGER_PAGE;
	}

}
