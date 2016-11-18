package elearning2.utility;
/* Program: DBConnection for Operations
 * Date: 15/Aug/2014
 * Version:1.0
 * Developers: * * *
 */

import java.sql.*;

public class DBConnection {

	static Connection con;
	static String url;
	static ResultSet rs;
	static Statement statementObj;
	static PreparedStatement ps;
	static int result = 0;

	static Connection getDBConnection() {
		String serverName = "localhost";
		int port = 1521;
		String password = "elearning";
		String username = "elearning";
		String SID = "XE";

		String url = "jdbc:oracle:thin:@" + serverName + ":" + port + ":" + SID;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// DriverManager.registerDriver(new
			// oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection(url, username, password);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return con;
	}

	public static ResultSet getResultSet(String searchQuery) {
		Connection current = null;
		try {
			rs = null;
			current = DBConnection.getDBConnection();
			statementObj = current.createStatement();
		//			ResultSet.TYPE_SCROLL_SENSITIVE,
		//			ResultSet.CONCUR_UPDATABLE, ResultSet.CONCUR_READ_ONLY);
			rs = statementObj.executeQuery(searchQuery);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}

	public static PreparedStatement getPrepareStatement(String sql) {
		try {
			ps = null;
			Connection current = DBConnection.getDBConnection();
			ps = current.prepareStatement(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return ps;
	}

	public static int getUpdateResult(PreparedStatement prepareStatObj) {
		try {
			result = 0;
			result = prepareStatObj.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;

	}

	public static int getExecuteResult(String query) {

		try {
			System.out.println(query);
			result = 0;
			Connection currentCon = DBConnection.getDBConnection();
			statementObj = currentCon.createStatement();
			result = statementObj.executeUpdate(query);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;

	}

	public static void resultSetClose(ResultSet resultSetObj) {

		if (resultSetObj != null) {
			try {
				resultSetObj.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void statementClose(Statement statementObj) {
		if (statementObj != null) {
			try {
				statementObj.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void connectionClose(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void prepareStatementClose(
			PreparedStatement prepareStatementObj) {
		if (prepareStatementObj != null) {
			try {
				prepareStatementObj.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}