package com.finalwebpro.ourfilm.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finalwebpro.ourfilm.bean.RegisterBean;
import com.finalwebpro.ourfilm.dao.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		String name_user = request.getParameter("name_user");
		String email_user = request.getParameter("email_user");
		String password_user = request.getParameter("password_user");

		RegisterBean registerBean = new RegisterBean(name_user, email_user, password_user);
		UserDao registerDao = new UserDao();
		if (registerDao.insert(registerBean) == 1) {
			response.sendRedirect("login.jsp");
		} else {
			response.sendRedirect("register.jsp");
		}

	}

}
