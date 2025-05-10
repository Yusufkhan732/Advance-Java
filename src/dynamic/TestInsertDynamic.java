package dynamic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TestInsertDynamic {
	
	public static void main(String[] args) throws Exception {
		
		//testInsert1(); 
		//testInsert2();
		//testInsert3(33, 133, "Harry", 56, 48, 55);
		
		MarksheetBean bean = new MarksheetBean();
		bean.setId(34);
		bean.setRollNo(135);
		bean.setName("pari");
		bean.setPhysics(10);
		bean.setChemistry(20);
		bean.setMaths(30);

		testInser4(bean);
	}
	
	public static void testInsert1() throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver"); // loading the driver

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advance_java", "root", "root"); // establishing the connection

		PreparedStatement pstmt = conn.prepareStatement("insert into marksheet values(31, 131, 'dev', 19, 22, 34)"); // creates a PreparedStatement object to execute an SQL INSERT query

		int i = pstmt.executeUpdate(); // executes the INSERT query and returns the number of rows affected (typically 1 for a successful insert).

		System.out.println("data inserted => " + i);
	}
	
	public static void testInsert2() throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");// loading the driver

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advance_java", "root", "root");// establishing the connection

		PreparedStatement pstmt = conn.prepareStatement("insert into marksheet values(?, ?, ?, ?, ?, ?)"); // creates a PreparedStatement object to execute an SQL INSERT query

		pstmt.setInt(1, 32); // setting the values in the queries 1 = the place of question mark and 32 = value to be inserted in this case id
		pstmt.setInt(2, 132);
		pstmt.setString(3, "kanak");
		pstmt.setInt(4, 18);
		pstmt.setInt(5, 25);
		pstmt.setInt(6, 01);

		int i = pstmt.executeUpdate();// executes the INSERT query and returns the number of rows affected (typically 1 for a successful insert).

		System.out.println("data inserted => " + i);
	}
	
	public static void testInsert3(int id, int rollNo, String name, int physics, int chemistry, int maths)
			throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");// loading the driver

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advance_java", "root", "root");// establishing the connection

		PreparedStatement pstmt = conn.prepareStatement("insert into marksheet values(?, ?, ?, ?, ?, ?)");// creates a PreparedStatement object to execute an SQL INSERT query

		pstmt.setInt(1, id);// setting the values in the queries 1 = the place of question mark and the paraeters are to be set using the constructor 
		pstmt.setInt(2, rollNo);
		pstmt.setString(3, name);
		pstmt.setInt(4, physics);
		pstmt.setInt(5, chemistry);
		pstmt.setInt(6, maths);

		int i = pstmt.executeUpdate();// executes the INSERT query and returns the number of rows affected (typically 1 for a successful insert).

		System.out.println("data inserted => " + i);
	}
	
	public static void testInser4(MarksheetBean bean) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advance_java", "root", "root");// establishing the connection

		PreparedStatement pstmt = conn.prepareStatement("insert into marksheet values(?, ?, ?, ?, ?, ?)");// creates a PreparedStatement object to execute an SQL INSERT query

		pstmt.setInt(1, bean.getId()); // setting the values in the queries 1 = the place of question mark and the parameter value are to be set using the setter getter method
		pstmt.setInt(2, bean.getRollNo());
		pstmt.setString(3, bean.getName());
		pstmt.setInt(4, bean.getPhysics());
		pstmt.setInt(5, bean.getChemistry());
		pstmt.setInt(6, bean.getMaths());

		int i = pstmt.executeUpdate();// executes the INSERT query and returns the number of rows affected (typically 1 for a successful insert).

		System.out.println("data inserted => " + i);
	}
}