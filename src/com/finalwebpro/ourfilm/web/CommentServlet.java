package com.finalwebpro.ourfilm.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finalwebpro.ourfilm.bean.Comment;
import com.finalwebpro.ourfilm.bean.Film;
import com.finalwebpro.ourfilm.dao.UserDao;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @return 
     * @see HttpServlet#HttpServlet()
     */
    //public void commentServlet() {
      //  super();
        // TODO Auto-generated constructor stub
    //}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertComment(request, response);
				break;
			case "/delete":
				deleteComment(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateComment(request, response);
				break;
			default:
				listComment(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	private void listComment(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		UserDao UserDao = new UserDao();
		try {
			List<Comment> listComment = UserDao.selectAllComments();
			request.setAttribute("listComment", listComment);
			RequestDispatcher dispatcher = request.getRequestDispatcher("comment-list.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("comment-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id_comment = Integer.parseInt(request.getParameter("id_comment"));
		
		//Comment existingComment;
		UserDao UserDao = new UserDao();
		try {
			Comment existingComment = UserDao.selectComment(id_comment);
			RequestDispatcher dispatcher = request.getRequestDispatcher("comment-form.jsp");
			request.setAttribute("comment", existingComment);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void insertComment(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name_film = request.getParameter("name_film");
		String distributor_film = request.getParameter("distributor_film");
		String comment_film = request.getParameter("comment_film");
		String date_comment = request.getParameter("date_comment");
		Comment comment = new Comment(name_film, distributor_film, comment_film, date_comment);
		UserDao UserDao = new UserDao();
		if (UserDao.insertComment(comment) == 1) {
			response.sendRedirect("list");
		} else {
			response.sendRedirect("mainpage.jsp");
		}
	}

	private void updateComment(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id_comment = Integer.parseInt(request.getParameter("id_comment"));
		String name_film = request.getParameter("name_film");
		String distributor_film = request.getParameter("distributor_film");
		String comment_film = request.getParameter("comment_film");
		String date_comment = request.getParameter("date_comment");
		UserDao UserDao = new UserDao();
		Comment book = new Comment(id_comment, name_film, distributor_film, comment_film, date_comment);
		UserDao.updateComment(book);
		response.sendRedirect("list");
	}

	private void deleteComment(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		UserDao UserDao = new UserDao();
		int id_comment = Integer.parseInt(request.getParameter("id_comment"));
		try {
			UserDao.deleteComment(id_comment);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("list");

	}

}