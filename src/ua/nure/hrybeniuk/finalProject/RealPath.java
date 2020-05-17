package ua.nure.hrybeniuk.finalProject;

import org.apache.log4j.Logger;

/**
 * Real path holder (Path of the WEB project is deployed).
 * 
 * @author A.Hrybeniuk
 * 
 */

public class RealPath {
	private static final Logger LOG = Logger.getLogger(RealPath.class);

	private static String path;

	// Setter for "path". Initialized, when the project starts
	public static void setPath(String path) {
		RealPath.path = RealPath.path == null ? path : RealPath.path;
		LOG.debug("RealPath was successfully defined");
		LOG.trace("path: " + path);
	}
	
	// Getter for "path"
	public static String getPath() {
		return path;
	}
}
