package ua.nure.hrybeniuk.finalProject.web.listener;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.nure.hrybeniuk.finalProject.RealPath;

/**
 * Payment listener.
 * 
 * @author A.Hrybeniuk
 * 
 */
@WebListener
public class PaymentListener implements ServletContextListener {
	private static final Logger LOG = Logger.getLogger(PaymentListener.class);

	private ScheduledExecutorService scheduler;

	public PaymentListener() {
	}

	public void contextDestroyed(ServletContextEvent sce) {
		scheduler.shutdownNow();
	}

	public void contextInitialized(ServletContextEvent sce) {

		ServletContext sc = sce.getServletContext();
		initLog4J(sc);

		RealPath.setPath(sc.getRealPath(""));
		scheduler = Executors.newSingleThreadScheduledExecutor();
		Runnable command = new PaymentThread();
		long initialDelay = Long.valueOf(sc.getInitParameter("initialDelay"));
		TimeUnit unit = TimeUnit.SECONDS;
		long period = Long.valueOf(sc.getInitParameter("period"));

		scheduler.scheduleAtFixedRate(command, initialDelay, period, unit);
	}

	private void initLog4J(ServletContext servletContext) {
		log("Log4J initialization started");
		try {
			PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
			LOG.debug("Log4j has been initialized");
		} catch (Exception ex) {
			log("Cannot configure Log4j");
			ex.printStackTrace();
		}
		log("Log4J initialization finished");
	}

	private void log(String msg) {
		System.out.println("[ContextListener] " + msg);
	}

}
