package elearning2.utility;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class UserDAO {

	static ResultSet rs = null;
	static ResultSet rs1 = null, rs4 = null, rs5 = null, rs6 = null;

	public static UserBean login(UserBean bean) {
		// Statement stmt = null;
		// Statement stmt1 = null;

		String regid = bean.getRegid();
		String password = bean.getPassword();

		String searchQuery = "select * from Login where regid='" + regid
				+ "' AND password='" + password + "'";
		// String searchQuery1 = "select * from profile where id='"+ regid +
		// "'";
		System.out.println("Your UserName is" + regid);
		System.out.println("Your Password is" + password);
		System.out.println("Query:" + searchQuery);

		try {
			rs = DBConnection.getResultSet(searchQuery);

			boolean more = rs.next();

			System.out.println(more);
			if (!more) {
				System.out
						.println("Sorry, you are not a registered user! Please sign up first");
				bean.setValid(false);
			} else if (more) {
				// String role=rs.getString("ROLE");

				// System.out.println(role);
				// bean.setRole(role);
				// bean.setValid(true);\
				bean.setValid(true);
			}

			/*
			 * rs1 = DBConnection.getResultSet(searchQuery1); boolean more1 =
			 * rs1.next();
			 * 
			 * if (!more1) { System.out.println(
			 * "Sorry, you are not a registered user! Please sign up first");
			 * bean.setValid(false); } else if (more1) { String firstName =
			 * rs1.getString("FIRSTNAME"); String lastName =
			 * rs1.getString("LASTNAME"); bean.setFirstName(firstName);
			 * bean.setLastName(lastName); bean.setValid(true); }
			 */
		} catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occured!" + ex);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {

				}
				rs = null;
			}
			if (rs1 != null) {
				try {
					rs1.close();
				} catch (Exception e) {

				}
			}
		}
		return bean;
	}

	/*
	public static UserBean profile(UserBean user) {
		// preparing some objects for connection
		Statement stmt = null;
		String id = user.getRegid();

		System.out.println(id);
		Connection currentCon = null;
		try {
			// connect to DB
			currentCon = DBConnection.getDBConnection();
			stmt = currentCon.createStatement();
			System.out.println("after conn");

			String insertQuery = "update profile set id='" + user.getRegid()
					+ "'name='" + fname + "',lname='" + lname + "',gender='"
					+ gen + "',dob=to_date('" + dob
					+ "','yyyy-mm-dd'),contact_no=" + contcno + ",address='"
					+ addr + "',city='" + city + "',state='" + state
					+ "',pincode='" + pin + "',mail_id='" + email
					+ "' where id='" + id + "'";
			System.out.println("insertquery:" + insertQuery);
			rs = stmt.executeQuery(insertQuery);
*/
			/*
			 * boolean more=rs2.next(); if(more=true) bean.setValid(true); else
			 * bean.setValid(false);// if user does not exist set the isValid
			 * variable to false
			 */
/*		} catch (Exception ex) {
			System.out.println("exeption: " + ex);
		}
		// some exception handling
		finally {
			try {
				rs4.close();
				rs5.close();
			} catch (Exception e) {
			}
			rs4 = null;
			rs5 = null;
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
			}
			stmt = null;
		}
		if (currentCon != null) {
			try {
				currentCon.close();
			} catch (Exception e) {
			}
			currentCon = null;
		}

		return user;
	}
*/
}
