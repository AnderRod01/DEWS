package beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Fecha {
	private int day;
	private int month;
	private int year;

	public Fecha(final int day, final int month, final int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public boolean correct() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		formatter.setLenient(false);

		try {
			final java.util.Date d = formatter.parse(this.year + "/" + this.month + "/" + this.day);
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	public String toString() {
		return this.year + "/" + this.month + "/" + this.day;
	}
}
