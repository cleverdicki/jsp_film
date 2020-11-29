package com.finalwebpro.ourfilm.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.finalwebpro.ourfilm.bean.Comment;
import com.finalwebpro.ourfilm.bean.Film;
import com.finalwebpro.ourfilm.bean.LoginBean;
import com.finalwebpro.ourfilm.bean.RegisterBean;

public class UserDao {
	private String dbUrl = "jdbc:mysql://localhost:3306/userdb?useSSL=false";
	private String dbUname = "root";
	private String dbPassword = "";
	private String dbDriver = "com.mysql.cj.jdbc.Driver";
	
	private static final String INSERT_COMMENTS_SQL = "INSERT INTO comments(name_film, distributor_film, comment_film, date_comment) VALUES (?, ?, ?, ?)";
	private static final String SELECT_COMMENT_BY_ID = "select id_comment, name_film, distributor_film, comment_film, date_comment from comments where id =?";
	private static final String SELECT_ALL_COMMENTS = "select * from comments";
	private static final String DELETE_COMMENTS_SQL = "delete from comments where id_comment = ?";
	private static final String UPDATE_COMMENTS_SQL = "update comments set name_film = ?,distributor_film= ?, comment_film =?, date_comment= ?, where id_comment = ?";
	private static final String INSERT_FILMS_SQL = "INSERT INTO films(name, distributor, genre, year, country, duration,trailer) VALUES (?, ?, ?, ?, ?, ?, ?);";
	private static final String SELECT_FILM_BY_ID = "select id, name, distributor, genre, year, country, duration,trailer from films where id =?";
	private static final String SELECT_ALL_FILMS = "select * from films";
	private static final String DELETE_FILMS_SQL = "delete from films where id = ?;";
	private static final String UPDATE_FILMS_SQL = "update films set name=?, distributor=?, genre=?, year=?, country=?, duration=?,trailer=? where id = ?;";
	
	
	public void loadDriver(String dbDriver) {
		try {
			Class.forName(dbDriver);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(dbUrl, dbUname, dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public boolean validate(LoginBean loginBean) {
		loadDriver(dbDriver);
		Connection connection = getConnection();
		boolean status = false;
		
		String sql = "select * from users where email_user = ? and password_user = ?";
		
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginBean.getEmail_user());
			preparedStatement.setString(2, loginBean.getPassword_user());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			status = resultSet.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
	public int insert(RegisterBean registerBean) {
		loadDriver(dbDriver);
		Connection connection = getConnection();
		int status = 0;
		String sql = "insert into users (name_user,email_user,password_user) values (?,?,?)";
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, registerBean.getName_user());
			preparedStatement.setString(2, registerBean.getEmail_user());
			preparedStatement.setString(3, registerBean.getPassword_user());
			preparedStatement.executeUpdate();
			status = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
	public int insertFilm(Film film)
	{
		loadDriver(dbDriver);
		Connection connection = getConnection();
		String sql = INSERT_FILMS_SQL;
		int status = 0;
		// try-with-resource statement will auto close the connection.
		try (
			PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, film.getName());
			preparedStatement.setString(2, film.getDistributor());
			preparedStatement.setString(3, film.getGenre());
			preparedStatement.setString(4, film.getYear());
			preparedStatement.setString(5, film.getCountry());
			preparedStatement.setString(6, film.getDuration());
			preparedStatement.setString(7, film.getTrailer());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			status = 1;
		} catch (SQLException e) {
			printSQLException(e);
		}
		
		return status;
	}
	
	public Film selectFilm(int id)
	{
		Film film = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FILM_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("name");
				String distributor = rs.getString("distributor");
				String genre = rs.getString("genre");
				String year = rs.getString("year");
				String country = rs.getString("country");
				String duration = rs.getString("duration");
				String trailer = rs.getString("trailer");
				film = new Film(id, name, distributor, genre, year, country, duration,trailer);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return film;
	}
	
	public List<Film> selectAllFilms()
	{
		// using try-with-resources to avoid closing resources (boiler plate code)
				List<Film> films = new ArrayList<>();
				// Step 1: Establishing a Connection
				try (Connection connection = getConnection();

						// Step 2:Create a statement using connection object
					PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FILMS);) {
					System.out.println(preparedStatement);
					// Step 3: Execute the query or update query
					ResultSet rs = preparedStatement.executeQuery();

					// Step 4: Process the ResultSet object.
					while (rs.next()) {
						int id = rs.getInt("id");
						String name = rs.getString("name");
						String distributor = rs.getString("distributor");
						String genre = rs.getString("genre");
						String year = rs.getString("year");
						String country = rs.getString("country");
						String duration = rs.getString("duration");
						String trailer = rs.getString("trailer");
						films.add(new Film(id, name, distributor, genre, year, country, duration,trailer));
					}
				} catch (SQLException e) {
					printSQLException(e);
				}
				return films;
	}
	
	public boolean updateFilm(Film film) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_FILMS_SQL);) {
			System.out.println("Your Film has been updated:"+statement);
			statement.setString(1, film.getName());
			statement.setString(2, film.getDistributor());
			statement.setString(3, film.getGenre());
			statement.setString(4, film.getYear());
			statement.setString(5, film.getCountry());
			statement.setString(6, film.getDuration());
			statement.setString(7, film.getTrailer());
			statement.setInt(8, film.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public boolean deleteFilm(int id_comment) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_FILMS_SQL);) {
			statement.setInt(1, id_comment);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public int insertComment(Comment comment) {
		loadDriver(dbDriver);
		Connection connection = getConnection();
		String sql = INSERT_COMMENTS_SQL;
		int status = 0;
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, comment.getname_film());
			preparedStatement.setString(2, comment.getdistributor_film());
			preparedStatement.setString(3, comment.getcomment_film());
			preparedStatement.setString(4, comment.getdate_comment());
			preparedStatement.executeUpdate();
			status = 1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	public Comment selectComment(int id_comment) {
		Comment comment = null;
		
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENT_BY_ID);) {
			preparedStatement.setInt(1, id_comment);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name_film = rs.getString("name_film");
				String distributor_film = rs.getString("distributor_film");
				String comment_film = rs.getString("comment_film");
				String date_comment = rs.getString("date_comment");
				comment = new Comment(id_comment, name_film, distributor_film, comment_film, date_comment);
				//comment = new Comment(id, name_film, distributor_film, comment_film, date_comment);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return comment;
	}

	public List<Comment> selectAllComments() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Comment> comments = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_COMMENTS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id_comment = rs.getInt("id_comment");
				String name_film = rs.getString("name_film");
				String distributor_film = rs.getString("distributor_film");
				String comment_film = rs.getString("comment_film");
				String date_comment = rs.getString("date_comment");
				comments.add(new Comment(id_comment, name_film, distributor_film, comment_film, date_comment));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return comments;
	}

	public boolean deleteComment(int id_comment) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_COMMENTS_SQL);) {
			statement.setInt(1, id_comment);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateComment(Comment comment) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_COMMENTS_SQL);) {
			System.out.println("Your comment has been updated:"+statement);
			statement.setString(1, comment.getname_film());
			statement.setString(2, comment.getdistributor_film());
			statement.setString(3, comment.getcomment_film());
			statement.setString(4, comment.getdate_comment());
			statement.setInt(5, comment.getId_comment());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
