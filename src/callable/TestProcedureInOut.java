package callable;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;

public class TestProcedureInOut {
	
	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/advance_java", "root", "root");

		CallableStatement callStmt = conn.prepareCall("{CALL userInOut(?)}");

		callStmt.setInt(1, 10);

		callStmt.registerOutParameter(1, Types.VARCHAR);
		
		callStmt.execute();
		
		String resultValue = callStmt.getString(1);

		System.out.println(resultValue);
	}
}