package ua.nure.hrybeniuk.finalProject.web.command;

import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;


/**
 * Holder for all commands.<br/>
 * 
 * @author A.Hrybeniuk
 * 
 */
public class CommandContainer {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {
		
		//client commands
		commands.put("booking-user", new PaymentCommand());
		commands.put("create-statement", new StatementCreateCommand());
		commands.put("select-room", new BookingsCreateCommand());
		commands.put("booking-query-user-acception", new BookingQuerryAcception());
		
		//manager commands
		commands.put("get-rooms", new GetNiceRooms());
		commands.put("send-book-request", new BookRequestCommand());
		
		//common commands
		commands.put("rooms_path", new RoomsCommand());
		commands.put("login", new LogRegCommand());
		commands.put("sign-up", new LogRegCommand());
		commands.put("account", new AccountCommand());
		commands.put("settings", new SettingsCommand());
		
		
		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name: " + commandName);
			return commands.get("noCommand");
		}

		return commands.get(commandName);
	}

}