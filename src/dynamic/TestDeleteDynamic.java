package dynamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TestDeleteDynamic {
	
	public static void main(String[] args) throws Exception {
		
		//testDelete1();
		//testDelete2();
		testDelete3(32);
		
	}
	
	public static void testDelete1() throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");// loading the driver

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advance_java", "root", "root"); // establishing the connection

		PreparedStatement pstmt = conn.prepareStatement("delete from marksheet where id = 34");// creates a PreparedStatement object to execute an SQL DELETE query

		int i = pstmt.executeUpdate();// executes the DELETE query and returns the number of rows affected (typically 1 for a successful delete).

		System.out.println("data deleted => " + i);
	}
	
	public static void testDelete2() throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");// loading the driver

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advance_java", "root", "root"); // establishing the connection

		PreparedStatement pstmt = conn.prepareStatement("delete from marksheet where id = ?");// creates a PreparedStatement object to execute an SQL DELETE query

		pstmt.setInt(1, 33);// setting the values in the queries 1 = the place of question mark and 32 = value to be deleted in this case id

		int i = pstmt.executeUpdate();// executes the DELETE query and returns the number of rows affected (typically 1 for a successful delete).

		System.out.println("data deleted => " + i);
	}
	
	public static void testDelete3(int id) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");// loading the driver

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advance_java", "root", "root"); // establishing the connection

		PreparedStatement pstmt = conn.prepareStatement("delete from marksheet where id = ?");// creates a PreparedStatement object to execute an SQL DELETE query

		pstmt.setInt(1, id);// setting the values in the queries 1 = the place of question mark and the paraeters are to be set using the constructor 

		int i = pstmt.executeUpdate();// executes the DELETE query and returns the number of rows affected (typically 1 for a successful delete).

		System.out.println("data deleted => " + i);
	}
}