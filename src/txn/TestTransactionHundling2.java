package txn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class TestTransactionHundling2 {
	
	public static void main(String[] args) throws Exception {

		Connection conn = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");// loading the driver

			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advance_java", "root", "root");// establishing the connection

			Statement stmt = conn.createStatement();

			int i = stmt.executeUpdate("insert into marksheet values(41, 117, 'aryant', 89, 22, 34)");// will commit this quary

			i = stmt.executeUpdate("insert into marksheet values(42, 118, 'aryant', 89, 22, 34)");// will commit this quary

			i = stmt.executeUpdate("insert into marksheet values(42, 119, 'aryant', 89, 22, 34)");// will throw error

			System.out.println("data inserted => " + i);

		} catch (Exception e) {

			System.out.println("exception: " + e.getMessage());

		} finally {
			conn.close();
		}
	}
}