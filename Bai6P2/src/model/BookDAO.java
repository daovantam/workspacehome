package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.Book;

public class BookDAO {

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public BookDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public boolean insertBook(Book book) throws SQLException {
		String sql = "INSERT INTO book (title, author, price) VALUES(?,?,?)";

		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, book.getTitle());
		statement.setString(2, book.getAuthor());
		statement.setFloat(3, book.getPrice());

		boolean rowInserted = statement.executeUpdate() > 0;

		statement.close();
		disconnect();

		return rowInserted;
	}

	public List<Book> listAllBooks() throws SQLException {
		List<Book> listBook = new ArrayList<Book>();
		String sql = "SELECT * FROM book";
		connect();
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			int id = resultSet.getInt("book_id");
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			float price = resultSet.getFloat("price");
			Book book = new Book(id, title, author, price);
			listBook.add(book);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listBook;
	}

	public List<Book> searchBook(String keyword) throws SQLException {
		List<Book> searchBookList = new ArrayList<Book>();
		String sql = "SELECT * FROM book WHERE title LIKE ?";
		connect();
		PreparedStatement pst = jdbcConnection.prepareStatement(sql);
		pst.setString(1, "%" + keyword + "%"); // Them % de tim gan dung
		ResultSet resultSet = pst.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("book_id");
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			float price = resultSet.getFloat("price");
			Book book = new Book(id, title, author, price);
			searchBookList.add(book);
		}
		resultSet.close();
		pst.close();
		disconnect();
		return searchBookList;

	}

}
