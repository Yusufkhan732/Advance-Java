package ctl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import model.UserModel;

@WebServlet("/UserCtl.do")
public class UserCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");// Retrieves the id parameter from the request.

		UserModel model = new UserModel();

		if (id != null) {
			try {
				UserBean bean = model.findByPk(Integer.parseInt(id));// Converts id from String to Integer using
																		// Integer.parseInt(id). Calls findByPk(
				req.setAttribute("bean", bean);// Stores the retrieved UserBean (user data) in the request scope.
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		RequestDispatcher rd = req.getRequestDispatcher("UserView.jsp");// Forwards the request to UserView.jsp,
																		// ensuring that user data (bean) is available
																		// in the JSP.
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String firstName = req.getParameter("firstName");// These lines(42-47) retrieve form data sent from an HTML form
															// via a POST request.
		String lastName = req.getParameter("lastName");
		String loginId = req.getParameter("loginId");
		String password = req.getParameter("password");
		String dob = req.getParameter("dob");
		String address = req.getParameter("address");

		String op = req.getParameter("operation");// Extracts the operation type (whether the user wants to save or
													// update the record).

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		UserBean bean = new UserBean();
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setLoginId(loginId);
		bean.setPassword(password);
		try {
			bean.setDob(sdf.parse(dob));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		bean.setAddress(address);

		UserModel model = new UserModel();

		if (op.equals("save")) {
			try {
				model.add(bean);
				req.setAttribute("msg", "User Added Successfully..!!");
			} catch (Exception e) {
				req.setAttribute("msg", e.getMessage());
			}
		}

		if (op.equals("update")) {
			try {
				bean.setId(Integer.parseInt(req.getParameter("id")));
				model.update(bean);
				bean = model.findByPk(bean.getId());
				req.setAttribute("bean", bean);
				req.setAttribute("msg", "User Updated Successfully..!!");
			} catch (Exception e) {
				req.setAttribute("msg", e.getMessage());
			}
		}

		RequestDispatcher rd = req.getRequestDispatcher("UserView.jsp");
		rd.forward(req, resp);
	}
}