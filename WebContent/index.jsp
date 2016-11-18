
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="elearning2.utility.UserBean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<%
	HttpSession ses = request.getSession(false);

	UserBean bean = null;
	bean = (UserBean) ses.getAttribute("admin");

	if (bean != null) {
		RequestDispatcher rd = getServletContext()
				.getRequestDispatcher("/1.html");
		rd.forward(request, response);

	} else {
		bean = (UserBean) ses.getAttribute("user");
		if (bean != null) {

			RequestDispatcher rd = getServletContext()
					.getRequestDispatcher("/1.html");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = getServletContext()
					.getRequestDispatcher("/1.html");
			rd.forward(request, response);
		}

	}
%>
</html>