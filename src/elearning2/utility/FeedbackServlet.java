package elearning2.utility;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FeedbackServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("name");
		System.out.println("name:"+name);
		String email=request.getParameter("email");
		System.out.println("email:"+email);
		String subject=request.getParameter("subject");
		System.out.println("subject:"+subject);
		String content=request.getParameter("content");
		System.out.println("content:"+content);
		FeedbackBean Bean=new FeedbackBean();
		Bean.setName(name);
		Bean.setEmail(email);
		Bean.setSubject(subject);
		Bean.setContent(content);
		boolean flag=FeedbackDAO.persist(Bean);
		System.out.println("value of flag is" +flag);
		if(flag==true){
			response.sendRedirect("confirm.jsp");
			
		}
		else
			response.sendRedirect("invalid.jsp");
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
		
	}

}
