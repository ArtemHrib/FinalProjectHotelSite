package ua.nure.hrybeniuk.finalProject.web.customTags;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * Tag handler for booking dates
 * 
 * @author A.Hrybeniuk
 * 
 */
public class DateTag extends SimpleTagSupport {
	private String value;
	private Long parameter;

	public void setValue(String value) {
		this.value = value;
		
	}

	public void setParameter(Long parameter) {
		this.parameter = parameter;
	}
	
	public void doTag() throws JspException, IOException {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
		Date nowDate = new Date();
		
		String resultValue = null;
		switch (value) {
		case "nowDate": {
			Timestamp nowTimest = new Timestamp(nowDate.getTime());
			resultValue = dateFormater.format(nowTimest);
		}
			break;
		case "maxBookingDaysMil": {
			resultValue = dateFormater.format(nowDate.getTime() + parameter);
		}
			break;
		case "minBookingDaysMil": {
			resultValue = dateFormater.format(nowDate.getTime() + parameter);
		}
			break;
		}

		JspWriter out = getJspContext().getOut();
		out.print(resultValue);

	}

}
