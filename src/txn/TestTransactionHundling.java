package txn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestTransactionHundling {
	
	public static void main(String[] args) throws Exception {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");// loading the driver

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advance_java", "root", "root");// establishing the connection

			conn.setAutoCommit(false);//When you use conn.setAutoCommit(false); 
                                      //In this mode, all SQL statements executed through the connection are grouped into a single transaction 
									 //until you manually commit or roll back the transaction.
			
			Statement stmt = conn.createStatement(); //A Statement object is used to send SQL commands (such as SELECT, INSERT, UPDATE, or DELETE) to the database.

			int i = stmt.executeUpdate("insert into marksheet values(31, 131, 'aryant', 89, 22, 34)");

			i = stmt.executeUpdate("insert into marksheet values(31, 132, 'aryant', 89, 22, 34)");

			i = stmt.executeUpdate("insert into marksheet values(31, 133, 'aryant', 89, 22, 34)");

			conn.commit();

			System.out.println("data inserted => " + i);

		} catch (Exception e) {

			conn.rollback();
			System.out.println("exception: " + e.getMessage());

		} finally {
			conn.close();
		}
	}

}