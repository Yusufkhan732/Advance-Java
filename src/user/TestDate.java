package user;

import java.util.Date;

public class TestDate {
	
	public static void main(String[] args) {

		Date today = new Date();// Creates a new instance of java.util.Date, representing the current date and time 

		System.out.println(today);// Prints the string representation of the today object, which includes the current date and time in the default format.

		System.out.println(today.getTime());// Prints the number of milliseconds that have passed since January 1, 1970, 00:00:00 UTC (the Unix epoch).

		System.out.println(new java.sql.Date(today.getTime()));// Converts the java.util.Date object into a java.sql.Date object. java.sql.Date is typically used for database operations in JDBC and represents only the date (year, month, day) without time information.
	}
}