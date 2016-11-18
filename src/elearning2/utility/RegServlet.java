package elearning2.utility;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
		try{
			RBean user=new RBean();
			user.setRegid(request.getParameter("regid"));
			user.setFirstname(request.getParameter("fname"));
			user.setLastname(request.getParameter("lname"));
			user.setPassword(request.getParameter("password"));
			user.setAddress_Line_1(request.getParameter("line1"));
			user.setAddress_Line_2(request.getParameter("line2"));
			user.setCity(request.getParameter("city"));
			user.setState(request.getParameter("state"));
			user.setPincode(request.getParameter("pincode"));
			user.setEmailid(request.getParameter("emailid"));
			user.setMobile(request.getParameter("mobile"));
			
			
			int success=RDAO.registration(user);
			if(success==1){
				response.sendRedirect("regsuccess.html");;
			}
			else
				response.sendRedirect("regunsuccess.jsp");
		}catch(Throwable theException){
			System.out.println(theException);
		}
	}
				
			
		
	

}
