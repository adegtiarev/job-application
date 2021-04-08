package kg.aios.application.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

	public String dateToString(Date date) {
		if (date == null)
			return "";

		return dateFormat.format(date);
	}
}
