package elearning2.utility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*
 login  servlet1
 */
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			UserBean user = new UserBean();
			user.setRegid(request.getParameter("login"));
			user.setPassword(request.getParameter("password"));
			
			
			user = UserDAO.login(user);
			
			if (user.isValid()) {
				HttpSession session = request.getSession(true);
							
				//session.setAttribute(user.getRole(), user);
				response.sendRedirect("1html.html");
			} else
				response.sendRedirect("invalidLogin.jsp");
		}

		catch (Throwable theException) {
			System.out.println(theException);
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}