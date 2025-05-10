package ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import model.UserModel;

@WebServlet("/LoginCtl")
public class LoginCtl extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String op = req.getParameter("operation");

		if (op != null && op.equals("logout")) { // if op is not null and = to logout

			HttpSession session = req.getSession(); // getsession method will get the session

			session.invalidate(); // invalidate method will terminate the method

			req.setAttribute("msg", "Logout Successfully..!!"); // msg print
		}
		RequestDispatcher rd = req.getRequestDispatcher("LoginView.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String op = req.getParameter("operation");

		if (op.equals("signIn")) {

			String loginId = req.getParameter("loginId");
			String password = req.getParameter("password"); // these line (29-30) fetch the credentials submitted via
															// the login form
			HttpSession session = req.getSession(); // Created session

			UserModel model = new UserModel(); // This initializes an instance of , which likely contains methods for
												// interacting with the database.

			try {
				UserBean bean = model.authenticate(loginId, password);
				if (bean != null) { // This means that authentication was successful, and the object contains valid
									// user details.

					/*
					 * req.setAttribute("user", bean); // Stores the authenticated user's
					 * information () in the request // scope so that can access it.
					 * 
					 * RequestDispatcher rd = req.getRequestDispatcher("WelcomeCtl");
					 * rd.forward(req, resp); // This forwards the request to , preserving the
					 * request attributes. So can // access the attribute and display user-specific
					 * information.
					 */
					session.setAttribute("user", bean); // user contains the userbean object data

					resp.sendRedirect("WelcomeCtl");

				} else {
					req.setAttribute("msg", "Invalid loginId or password...!!"); // msg print
					RequestDispatcher rd = req.getRequestDispatcher("LoginView.jsp");
					rd.forward(req, resp);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (op.equals("signUp")) {
			resp.sendRedirect("UserRegistrationView.jsp");
		}
	}
}