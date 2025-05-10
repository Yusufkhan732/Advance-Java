package rb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCDataSource {
	
	private static JDBCDataSource jds = null;

	private ComboPooledDataSource cpds = null; //An instance of ComboPooledDataSource, which manages the connection pool.

	private static ResourceBundle rb = ResourceBundle.getBundle("bundle.system"); //Loads a properties file named system.properties from the package in.co.rays.bundle.

	private JDBCDataSource() {
		try {
			cpds = new ComboPooledDataSource();//A new connection pool.
			cpds.setDriverClass(rb.getString("driver")); //Fetches database credentials from the ResourceBundle and sets them for cpds
			cpds.setJdbcUrl(rb.getString("url"));
			cpds.setUser(rb.getString("username"));
			cpds.setPassword(rb.getString("password"));
			cpds.setInitialPoolSize(Integer.parseInt(rb.getString("initialpoolsize")));
			cpds.setAcquireIncrement(Integer.parseInt(rb.getString("acquireincrement")));
			cpds.setMaxPoolSize(Integer.parseInt(rb.getString("maxpoolsize")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JDBCDataSource getInstance() { //Ensures only one instance of JDBCDataSource exists.
		if (jds == null) { //If jds is null, it creates a new instance.
			jds = new JDBCDataSource();
		}
		return jds;
	}

	public static Connection getConnection() {
		try {
			return getInstance().cpds.getConnection();
		} catch (SQLException e) {
			return null;
		}
	}

	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) { //Ensures proper resource cleanup: Closes ResultSet, Statement, and Connection.
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection(Connection conn, Statement stmt) {
		closeConnection(conn, stmt, null);
	}

	public static void closeConnection(Connection conn) {
		closeConnection(conn, null);
	}
}