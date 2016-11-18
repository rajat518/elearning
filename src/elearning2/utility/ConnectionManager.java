package elearning2.utility;

import java.sql.*;
public class ConnectionManager
{
static Connection con;
static String url;
public static Connection getConnection()
{
try
{
Class.forName("oracle.jdbc.driver.OracleDriver");
con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","elearning","elearning");
}
catch(SQLException ex)
{
ex.printStackTrace();
}
catch(ClassNotFoundException e)
{
System.out.println(e);
}
return con;
}
}