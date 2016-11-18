package elearning2.utility;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContactUsServlet
 */
// @WebServlet("/ContactUsServlet")
public class ContactUsServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String name = request.getParameter("name");
		String subject = request.getParameter("subject");
		String comment = request.getParameter("ques");
		String email = request.getParameter("email");
		System.out.println("dopost");
		ContactUsDAO bean = new ContactUsDAO();
		System.out.println("createddao");
		bean.setComment(comment);
		bean.setEmail(email);
		bean.setName(name);
		bean.setSubject(subject);
		bean = ContactUsDAO.contactUs(bean);
		if (bean.isValid()) {
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc
					.getRequestDispatcher("/contactus_home.jsp?msg=Request Sent Successfully");
			rd.forward(request, response);

			/*
			 * response.sendRedirect(
			 * "contactus_home.jsp?msg=Request Sent successfully ");
			 */
		} else {
			ServletContext sc = getServletContext();
			RequestDispatcher rd = sc
					.getRequestDispatcher("/contactus_home.jsp?msg=Request Sending Failed");
			rd.forward(request, response);

			/*
			 * response.sendRedirect("contactus_home.jsp?msg=Request Sending Failed"
			 * );
			 */
		}

	}

}
