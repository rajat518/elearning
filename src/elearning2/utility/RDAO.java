package elearning2.utility;

  import java.sql.Connection;
  import java.sql.ResultSet;
  import java.sql.Statement;
  
  import elearning2.utility.ConnectionManager;
  import elearning2.utility.RBean;
  import java.sql.ResultSet;
  import java.sql.Statement;

public class RDAO {
      static Connection currentCon=null;
      static ResultSet rs=null;
      
      public static int registration(RBean bean){
 
    	  int count=0;
    	  Statement stmt1=null, stmt2=null;
    	 
    	  
    	  
    	  String regid=bean.getRegid();
    	  String password=bean.getPassword();
    	  String Firstname=bean.getFirstname();
    	  String Lastname=bean.getLastname();
    	  String Address_Line_1=bean.getAddress_Line_1();
    	  String Address_Line_2=bean.getAddress_Line_2();
    	  String City=bean.getCity();
    	  String State=bean.getState();
    	  String Pincode=bean.getPincode();
    	  String Emailid=bean.getEmailid();
    	  String Mobile=bean.getMobile();
    	
    	  String searchQuery1=
    			  "insert into registration values('"
    			           +regid+"','"
    			           +password+"','"
    			           +Firstname+"','"
    			           +Lastname+"','"
    			           +Address_Line_1+"','"
    			           +Address_Line_2+"','"
    			           +City+"','"
    			           +State+"',"
    			           +Pincode+",'"
    			           +Emailid+"','"
    			           +Mobile+"')";
    	  
    	  String searchQuery2=
    			  "insert into login values('"
    			           +regid+"','"
    			           +password+ "')";
    	 
     System.out.println("Query:"+searchQuery1);
     System.out.println("Query:"+searchQuery2);
    
    	  try{
    		  currentCon=ConnectionManager.getConnection();
    		  stmt1=currentCon.createStatement();
    		  stmt2=currentCon.createStatement();
    		  count=stmt1.executeUpdate(searchQuery1);
    		  count=stmt2.executeUpdate(searchQuery2);
    		  
    	  }catch(Exception ex){ System.out.println("registration failed:an exception has occured!"+ex);}
    	  finally{
    		  if(rs!=null){
    			  try{
    				  rs.close();
    			  }catch(Exception e){}
    			  rs=null;
    		  }

    		  if(stmt1!=null){
    			  try{
    				  stmt1.close();
    			  }catch(Exception e){}
    			  stmt1=null;
    		  }

    		  if(stmt2!=null){
    			  try{
    				  stmt2.close();
    			  }catch(Exception e){}
    			  stmt2=null;
    		  }
    		  
    		  if(currentCon!=null){
    			  try{
    				  currentCon.close();
    			  }catch(Exception e){ }
    			  currentCon=null;
    		  }
    	  }
    	  return count;
      }
}
    			  
