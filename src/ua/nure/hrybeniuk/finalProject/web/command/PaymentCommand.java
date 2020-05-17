package ua.nure.hrybeniuk.finalProject.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.hrybeniuk.finalProject.db.DBManager;
import ua.nure.hrybeniuk.finalProject.exception.AppException;

/**
 * Payment execution.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class PaymentCommand extends Command {
	private static final long serialVersionUID = 1862329220540109363L;
	private static final Logger LOG = Logger.getLogger(PaymentCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		String action = request.getParameter("action");
		int bookingId = Integer.valueOf(request.getParameter("bookingId"));
		LOG.trace("User choose action: \"" + action + "\" for booking with id: " + bookingId);
		DBManager manager = null;

		manager = DBManager.getInstance();

		switch (action) {
		case "pay": {
			manager.updateBookingStatus(bookingId, 1);
			LOG.trace("Updated booking(status) in DB with value: " + 1);
		}
			break;
		case "cansel": {
			manager.deleteBookingById(bookingId);
			LOG.trace("Deleted row in the table \"booking\" in DB with id: " + bookingId);
		}
			break;
		}

		LOG.debug("Command finished with redirection to AccountCommand");
		return CommandContainer.get("account").execute(request, response);
	}

}
