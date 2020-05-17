package ua.nure.hrybeniuk.finalProject.db;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ua.nure.hrybeniuk.finalProject.RealPath;
import ua.nure.hrybeniuk.finalProject.db.constants.XML;

/**
 * Parser for PoolController. It parses myConfig.xml(must be in "\META-INF\")
 * 
 * @author A.Hrybeniuk
 * 
 */

public class ConfigParser {
	private static final Logger LOG = Logger.getLogger(ConfigParser.class);

	// //////////////////////////////////////////////////////////
	// singleton
	// //////////////////////////////////////////////////////////

	private final String contextPath;

	private static ConfigParser instance;

	private String url;

	private int maxCon = 10;

	private int startCon = 3;

	// Initialization of ConfigParser. RealPath must be initialized
	private ConfigParser() throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbfactory.newDocumentBuilder();
		contextPath = RealPath.getPath() + "META-INF\\myConfig.xml";
		LOG.trace("Path of config file: " + contextPath);

		Document document = db.parse(contextPath);

		Element root = document.getDocumentElement();

		attributeFinder(root);
		LOG.debug("Config Parser was successfully initialized");
	}

	public static ConfigParser getInstance() throws SAXException, IOException, ParserConfigurationException {
		if (instance == null) {
			instance = new ConfigParser();
		}
		return instance;

	}

	public String getUrl() {
		return url;
	}

	public int getMaxCon() {
		return maxCon;
	}

	public int getStartCon() {
		return startCon;
	}

	// setter for attributes
	private void attributeFinder(Element root) {
		NodeList resources = root.getChildNodes();
		Element resource = (Element) resources.item(1);
		String resUrl = resource.getAttribute(XML.CON_URL);
		String userName = resource.getAttribute(XML.USER_NAME);
		String password = resource.getAttribute(XML.PASSWORD);
		Integer maxCon = Integer.valueOf(resource.getAttribute(XML.MAX_CON));
		Integer startCon = Integer.valueOf(resource.getAttribute(XML.START_CON));
		this.startCon = startCon;
		this.maxCon = maxCon;
		this.url = resUrl + "?user=" + userName + "&password=" + password + "&serverTimezone=UTC";
		LOG.trace("DB Pool configuration: " + this.toString());
	}

	@Override
	public String toString() {
		return "ConfigParser [contextPath=" + contextPath + ", url=" + url + ", maxCon=" + maxCon + ", startCon="
				+ startCon + "]";
	}

}
