package ua.nure.hrybeniuk.finalProject.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.hrybeniuk.finalProject.Path;
import ua.nure.hrybeniuk.finalProject.db.DBManager;
import ua.nure.hrybeniuk.finalProject.db.entity.User;
import ua.nure.hrybeniuk.finalProject.exception.AppException;
import ua.nure.hrybeniuk.finalProject.util.HeshFactory;

/**
 * Users settings executor.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class SettingsCommand extends Command {

	private static final long serialVersionUID = 5075885010935923906L;
	private static final Logger LOG = Logger.getLogger(SettingsCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		String changeType = request.getParameter("submit");
		LOG.trace("Get request parameter \"submit\" = " + changeType);
		DBManager manager = DBManager.getInstance();
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		LOG.trace("User id = " + userId);
		String role = (String) session.getAttribute("role");
		User user = manager.selectUserById(userId);
		LOG.trace("Found in DB: user: " + user);
		switch (changeType) {
		case "Change Full name": {
			String name = request.getParameter("fullName");
			manager.updateUserName(userId, name);
			session.setAttribute("userName", name);
			LOG.trace("User changed name, new name = " + name);
			LOG.debug("Command finished");
			if ("manager".equals(role)) {
				return Path.PAGE_ACC_MANAGER_PAGE;
			} else {
				return Path.PAGE_ACC_USER_PAGE;
			}
		}
		case "Change password": {
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			String currentPassword = user.getPassword();
			String heshedOldPassword = HeshFactory.makeHesh(oldPassword);
			if (currentPassword.equals(heshedOldPassword)) {
				String heshedNewPassword = HeshFactory.makeHesh(newPassword);
				manager.updateUserPassword(userId, heshedNewPassword);
				LOG.trace("User successfully changed password");
			} else {
				LOG.trace("User's changing password attempt was unsuccessfull");
			}
			LOG.debug("Command finished");
			return Path.PAGE_ACC_MANAGER_PAGE;
		}
		case "Change phone": {
			String phone = request.getParameter("phone");
			manager.updateUserPhone(userId, phone);
			LOG.trace("User updated phone number");
			LOG.debug("Command finished");
			return Path.PAGE_ACC_MANAGER_PAGE;
		}
		case "Log out": {
			session.setAttribute("userId", null);
			session.setAttribute("role", null);
			session.invalidate();
			LOG.trace("Session invalideted");
			LOG.debug("Command finished");
			return Path.PAGE_HOME_PAGE;
		}
		}

		LOG.debug("Command finished");
		return Path.PAGE_ACC_MANAGER_PAGE;

	}

}
