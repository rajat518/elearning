package elearning2.utility;

import java.security.MessageDigest;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.ParseException;
import java.text.SimpleDateFormat;


public class UtilityClass {
	public static String getMessage(){
		return "Disclaimer:- This is a system generated email. Please do not reply to this email." +
				"\n*** This message is intended only for the person or entity to which  it is addressed and " +
				"may contain confidential and/or privileged information. If you have received this message in error," +
				"please notify the sender immediately and delete this message from your system ***";

	}
	public static int getId(String query) {
		int count = 0;
		ResultSet resultSetObj = null;
		try {
			resultSetObj = DBConnection.getResultSet(query);
			resultSetObj.last();
			count = resultSetObj.getRow();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.resultSetClose(resultSetObj);
		}
		return count + 1;
	}
	
	public static String getValue(String query) throws Exception{
		ResultSet resultSetObj = null;
		String value=null;
		try {
			resultSetObj = DBConnection.getResultSet(query);
			resultSetObj.next();
			value=resultSetObj.getString(1);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.resultSetClose(resultSetObj);
		}
		return value;
	}
	
	
	public static int getNamesCount(String query) throws SQLException{
		ResultSet resultSet=null;
		int count=0;
		try {
		resultSet=DBConnection.getResultSet(query);
		resultSet.last();
		count = resultSet.getRow();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConnection.resultSetClose(resultSet);
		}
		return count;
	}
	
	
	public static String insertAddress(String address, String city,
			String state, long pincode, String addrType) {

		String query = "select * from " + addrType + "_ADDR ";
		String addrInsert = "insert into " + addrType
				+ "_ADDR values(?,?,?,?,?)";
		PreparedStatement addrPst = DBConnection
				.getPrepareStatement(addrInsert);
		String addrId = "DTB" + addrType + "ADDR" + UtilityClass.getId(query);
		int returnvalue1 = 0;
		try {
			addrPst.setString(1, addrId);
			addrPst.setString(2, address);
			addrPst.setString(3, city);
			addrPst.setString(4, state);
			addrPst.setLong(5, pincode);
			returnvalue1 = addrPst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (returnvalue1 > 0)
			return addrId;
		else
			return null;
	}

	public static boolean isLoginIdExist(String loginId) {
		ResultSet loginRst = null;
		boolean result = false;
		String loginQuery = "select * from LOGINDETAILS where LOGIN_ID='"
				+ loginId + "'";
		loginRst = DBConnection.getResultSet(loginQuery);
		try {
			result = loginRst.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.resultSetClose(loginRst);
		}
		return result;
	}

	public static boolean updateAddress(String addrId, String address,
			String city, String state, long pincode, String addrType) {
		String updateAddrQuery = "update " + addrType + "_ADDR set ADDRESS='"
				+ address + "', CITY='" + city + "' , STATE='" + state
				+ "', PINCODE=" + pincode + " where ADDR_ID='" + addrId + "' ";
		
		int result = DBConnection.getExecuteResult(updateAddrQuery);
		if (result > 0)
			return true;
		else
			return false;
	}

	public static String getEncodePassword(String password) {
		byte[] unencodedPassword = password.getBytes();
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
		}
		md.reset();
		md.update(unencodedPassword);
		byte[] encodedPassword = md.digest();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < encodedPassword.length; i++) {
			if (((int) encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) encodedPassword[i] & 0xff, 16));
		}
		return buf.toString();
	}

	public static Date getSqlDate(String dateString) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date dt = sdf.parse(dateString);
		SimpleDateFormat sdfDestination = new SimpleDateFormat("yyyy-MM-dd");
		String newDateString = sdfDestination.format(dt);
		return Date.valueOf(newDateString);
	}
	
	public static double getInterest(int schemeType,String accountType){
		double interest=0;
		String query="select interest_rate from rate_of_interest where scheme_type="+schemeType+" and account_type='"+accountType+"' and (to_date(start_date,'dd-mm-yyyy')>=to_date(sysdate,'dd-mm-yyyy') and to_date(end_date,'dd-mm-yyyy')<=to_date(sysdate,'dd-mm-yyyy')) or (scheme_type='"+schemeType+"' and account_type='"+accountType+"' and to_date(start_date,'dd-mm-yyyy')<=to_date(sysdate,'dd-mm-yyyy') and end_date is null)";
		
		ResultSet resultSetObj=DBConnection.getResultSet(query);
		try {
			if(resultSetObj.next()){
			interest=resultSetObj.getDouble(1);
			   
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return interest;
	}
	
	/*
	 * Checking the account existence.
	 */
	public static int checkDetails(String accountId,String accountType,String account){
		int result=0;
		String query="select account_id from "+accountType+"_"+account+" where account_id='"+accountId+"' and flag=1";
		ResultSet resultSetObj=DBConnection.getResultSet(query);
		try {
			if(resultSetObj.next())
			result=1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public static boolean verifyDate(Date date){
		boolean flag=false;
		String query="select * from dual where to_date('"+date+"','yyyy-mm-dd')+1>=sysdate";
		ResultSet resultSetObj=DBConnection.getResultSet(query);
		try {
			if(resultSetObj.next())
				flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return flag;
	}
}