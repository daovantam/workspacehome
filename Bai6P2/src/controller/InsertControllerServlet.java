package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import model.BookDAO;

public class InsertControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BookDAO bookDAO;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
		bookDAO = new BookDAO(jdbcURL, jdbcUsername, jdbcPassword);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		// Xu ly Tieng Viet
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		try {
//			switch (action) {
//			case "/insert":
//				showNewForm(request, response);
//				insertBook(request, response);
//				break;
//			default:
//				listBook(request, response);
//				break;
//			}
			showNewForm(request, response);
			insertBook(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
		dispatcher.forward(request, response);
	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		String title = request.getParameter("title");
		String author = request.getParameter("author");
		float price = Float.parseFloat(request.getParameter("price"));
		Book newBook = new Book(title, author, price);
		bookDAO.insertBook(newBook);
		// Forward den trang danh sach cac cuon sach, kiem tra cuon sach vua them moi

		RequestDispatcher dispatcher = request.getRequestDispatcher("BookList.jsp");
		dispatcher.forward(request, response);
	}

}
