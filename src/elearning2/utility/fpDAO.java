package elearning2.utility;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;

public class fpDAO {
static Connection currentCon=null;
static ResultSet rs=null;
static Statement stmt=null;
static boolean more;

public static boolean getPassword(String regid){
	String searchQuery="select emailid,password from registration where regid='"+regid+"'";
	System.out.println("Query:" +searchQuery);
	try{
currentCon=ConnectionManager.getConnection();
stmt=currentCon.createStatement();
rs=stmt.executeQuery(searchQuery);
more=rs.next();
if(!more){
	return false;
}

else if(more){
	String password=rs.getString("password");
	String email=rs.getString("email");
	MailSend.send("to_emailId","from_emailId","from_password","welcome to my program,","your id:"+regid+" and password:"+password);
}
	}catch(Exception ex){
		System.out.println("log in failed:an Exception has occured!" +ex);
		
	}
	finally{
		if(rs!=null){
			try{
				rs.close();
				
			}catch(Exception ex){
		}
		rs=null;
	}
if(stmt!=null){
	try{
		stmt.close();
	}catch(Exception e){
		
	}
	stmt=null;
}
if(currentCon!=null){
	try{
		currentCon.close();
	}catch(Exception e){
}
	currentCon=null;
}
}
return more;
}
}