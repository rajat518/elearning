package elearning2.utility;
import java.sql.*;
public class FeedbackDAO {
static Connection currentCon=null;
static ResultSet rs=null;
public static boolean persist(FeedbackBean Bean){
	int i=0;
	Statement stmt=null;
	String name=Bean.getName();
	System.out.println("name:" +name);
	String email=Bean.getEmail();
	System.out.println("name:" +email);
	String subject=Bean.getSubject();
	System.out.println("name:" +subject);
	String content=Bean.getContent();
	System.out.println("name:" +content);
	String searchQuery="insert into feedback values('"+name+"','"+email+"'"
			+ subject+"','"+content+"',sysdate)";
	System.out.println("query:" +searchQuery);
	try{
		currentCon=ConnectionManager.getConnection();
		stmt=currentCon.createStatement();
		i=stmt.executeUpdate(searchQuery);

	}catch(Exception e){
		System.out.println("feedback insertion unsucessfull"+e);
		
	}finally{
		if(rs!=null){
			try{
				rs.close();
				}catch(Exception e)
				{
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
			          if(i==1)
			        	  return true;
			          else
			        	  return false;
}
}