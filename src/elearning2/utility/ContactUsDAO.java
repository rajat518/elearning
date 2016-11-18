package elearning2.utility;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactUsDAO {
	private String name;
	private String subject;
	private String email;
	private String comment;
	private boolean valid;
	private static String empid;
	private int count;
	private int no;
	public String getEmpid() {
		return empid;
	}

	public void setEmpid(String empid) {
		ContactUsDAO.empid = empid;
	}

	private java.sql.Date reqDate;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public java.sql.Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(java.sql.Date reqDate) {
		this.reqDate = reqDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public static ContactUsDAO contactUs(ContactUsDAO bean) {
		String comment = bean.getComment();
		String name = bean.getName();
		String email = bean.getEmail();
		String subject = bean.getSubject();
		int no = UtilityClass.getId("select * from contactus");
		String query = "INSERT INTO CONTACTUS(NO,REQ_DATE,NAME,EMAIL,SUBJECT,REQUEST,FLAG) VALUES("
				+ no 
				+ "''"
				+ ",SYSDATE,'"
				+ name
				+ "','"
				+ email
				+ "','"
				+ subject
				+ "','" + comment + "',0)";
		int i = DBConnection.getExecuteResult(query);
		if (i > 0)
			bean.setValid(true);
		else
			bean.setValid(false);
		return bean;
	}

	public static ContactUsDAO getBean(ContactUsDAO bean, int position)
			throws SQLException {
		String query = "select * from contactus where FLAG=0";
		ResultSet rs = DBConnection.getResultSet(query);
		if (rs.absolute(position)) {
			bean.setNo(rs.getInt(1));
			bean.setReqDate(rs.getDate(3));
			bean.setName(rs.getString(4));
			bean.setEmail(rs.getString(5));
			bean.setSubject(rs.getString(6));
			bean.setComment(rs.getString(7));
			bean.setValid(true);
		} else
			bean.setValid(false);

		return bean;
	}
}
