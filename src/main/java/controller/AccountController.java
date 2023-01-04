package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import accountManagement.AccountsManager;

/**
 * Servlet implementation class AccountController
 */
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action").trim();
			AccountsManager manager = new AccountsManager();

			if (action.equals("login")) {

				String webusername = request.getParameter("username").trim();
				String webpassword = request.getParameter("password");

				if (manager.authenticateUser(webusername, webpassword)) {
					HttpSession session = request.getSession();
					
					if (request.getParameter("cookies").equals("yes") ) {
						session.setAttribute("cookies", true);

					} else if (request.getParameter("cookies").equals("no")){
						session.setAttribute("cookies", false);
					}
					session.setAttribute("account", manager.getAccount(webusername));
					RequestDispatcher view = request.getRequestDispatcher("views/search.jsp");
					view.forward(request, response);
				} else {
					request.setAttribute("error", "Wrong username or password");
					RequestDispatcher view = request.getRequestDispatcher("index.jsp");
					view.forward(request, response);
				}
			}
			if (action.equals("newuser")) {

				manager.addAccount(request.getParameter("username"), request.getParameter("name"),
						request.getParameter("password"), request.getParameter("secretquestion"),
						request.getParameter("secretanswer"), request.getParameter("city"),
						request.getParameter("country"));
				response.sendRedirect("/JAVA22-webbutvecklingmedjava-slutprojekt-franzjosef-birch/index.jsp");
			}

		}
	}

}
