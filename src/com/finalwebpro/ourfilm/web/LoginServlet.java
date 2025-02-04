package com.finalwebpro.ourfilm.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.finalwebpro.ourfilm.bean.LoginBean;
import com.finalwebpro.ourfilm.dao.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("email_user");
		String password = request.getParameter("password_user");

		LoginBean loginBean = new LoginBean();
		loginBean.setEmail_user(username);
		loginBean.setPassword_user(password);

		UserDao loginDao = new UserDao();
		if (loginDao.validate(loginBean)) {
			HttpSession session = request.getSession();
			session.setAttribute("email_user", username);
			response.sendRedirect("comment-list.jsp?email_user="+username);
		} else {
			response.sendRedirect("login.jsp");
		}
	}

}
