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
import ua.nure.hrybeniuk.finalProject.db.entity.User;
import ua.nure.hrybeniuk.finalProject.exception.AppException;
import ua.nure.hrybeniuk.finalProject.util.HeshFactory;

/**
 * Login and Registration for users.
 * 
 * @author A.Hrybeniuk
 * 
 */
public class LogRegCommand extends Command {

	private static final long serialVersionUID = -1000961574187558447L;
	private static final Logger LOG = Logger.getLogger(LogRegCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		String command = request.getParameter("command");
		String userName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String password = request.getParameter("psw");
		String repPassword = request.getParameter("psw-repeat");
		String heshedUserPassword = HeshFactory.makeHesh(password);
		DBManager manager = null;
		HttpSession session = request.getSession();
		manager = DBManager.getInstance();
		if ("login".equals(command)) {
			LOG.debug("User's login attempt");

			User user = manager.selectUserByEmail(email);
			LOG.trace("Found in DB: user: " + user);
			if (user != null && user.getPassword().equals(heshedUserPassword)) {
				session.setAttribute("userId", user.getId());
				session.setAttribute("role", user.getRole().value());
				LOG.trace("User successfuly logined: id = " + user.getId() + ", role = " + user.getRole().value());
			} else {
				LOG.trace("User's login attempt was unsuccessful");
				throw new AppException("Email or password that you've entered is incorrect");
			}

		} else if ("sign-up".equals(command)) {
			LOG.debug("User's sign-up attempt");
			List<User> userList = manager.selectAllUser();
			LOG.trace("Found in DB: userList: (" + userList.size() + " users)");
			String errorMessage = null;
			for (User user : userList) {
				if (user.getEmail().equals(email)) {
					errorMessage = "Email that you've entered belongs to another user";
					break;
				} else if (user.getPhoneNumber().equals(phone)) {
					errorMessage = "Phone number that you've entered belongs to another user";
					break;
				}
			}
			if(!password.equals(repPassword)&&errorMessage == null) {
				errorMessage = "Password and Repeat Password fields dodn't match";
			}
			if (errorMessage == null) {
				LOG.trace("User successfuly signed-up");
				User user = new User();
				user.setFullName(userName);
				user.setEmail(email);
				user.setPassword(heshedUserPassword);
				user.setPhoneNumber(phone);
				user.setRole("Client");
				manager.insertUser(user);
				session.setAttribute("userId", user.getId());
				session.setAttribute("role", user.getRole().value());
			} else {
				LOG.trace("User's sign-up attempt was unsuccessful");
				throw new AppException(errorMessage);
			}

		}
		LOG.debug("Command finished");
		return Path.PAGE_HOME_PAGE;
	}

}
