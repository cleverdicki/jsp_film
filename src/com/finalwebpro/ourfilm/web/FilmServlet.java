package com.finalwebpro.ourfilm.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finalwebpro.ourfilm.bean.Film;
import com.finalwebpro.ourfilm.dao.UserDao;

/**
 * Servlet implementation class FilmServlet
 */
@WebServlet("/film")
public class FilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch(action)
		{
		case "/new":
			showNewForm(request, response);
			break;
		
		case "/insert":
			try {
				insertFilm(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		
		case "/delete":
			try {
				deleteFilm(request,response);
			} catch (SQLException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
			
		case "/edit":
			try {
				showEditForm(request,response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "/update":
			try {
				updateFilm(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		
			default:
			try {
				listFilm(request, response);
			} catch (SQLException | IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				break;
		
		}
	
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher("film-form.jsp");
		dispatcher.forward(request, response);
	}
	
	//insert film
	private void insertFilm(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		String distributor = request.getParameter("distributor");
		String genre = request.getParameter("genre");
		String year = request.getParameter("year");
		String country = request.getParameter("country");
		String duration = request.getParameter("duration");
		String trailer = request.getParameter("trailer");
		Film newFilm = new Film(name, distributor, genre, year, country, duration,trailer);
		UserDao UserDao = new UserDao();
		if (UserDao.insertFilm(newFilm) == 1) {
			response.sendRedirect("film-list.jsp");
		} else {
			response.sendRedirect("mainpage.jsp");
		}
	}
	
	//delete film
	private void deleteFilm(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		UserDao UserDao = new UserDao();
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			UserDao.deleteFilm(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("list");

	}
	
	//edit film 
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		UserDao UserDao = new UserDao();
		int id = Integer.parseInt(request.getParameter("id"));
		
		Film existingFilm;
		
		try {
			existingFilm = UserDao.selectFilm(id);
			RequestDispatcher dispatcher = request.getRequestDispatcher("film-form.jsp");
			request.setAttribute("film", existingFilm);
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//update
	private void updateFilm(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String distributor = request.getParameter("distributor");
		String genre = request.getParameter("genre");
		String year = request.getParameter("year");
		String country = request.getParameter("country");
		String duration = request.getParameter("duration");
		String trailer = request.getParameter("trailer");
		Film film = new Film(id, name, distributor, genre, year, country, duration,trailer);
		UserDao UserDao = new UserDao();
		UserDao.updateFilm(film);
		response.sendRedirect("list");
	}
	
	//default
	private void listFilm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		UserDao UserDao = new UserDao();
		try {
			List<Film> listFilm = UserDao.selectAllFilms();
			request.setAttribute("listFilm", listFilm);
			RequestDispatcher dispatcher = request.getRequestDispatcher("film-list.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}