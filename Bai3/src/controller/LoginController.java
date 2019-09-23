package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.LoginBean;
import model.LoginModel;

public class LoginController extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");

		LoginBean loginBean = new LoginBean();
		loginBean.setUserName(userName);
		loginBean.setPassword(password);
		req.setAttribute("loginBean", loginBean);

		LoginModel loginModel = new LoginModel();
		boolean status = loginModel.checkAccount(loginBean);

		if (status) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("login-success.jsp");
			dispatcher.forward(req, resp);
		} else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("login-error.jsp");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
