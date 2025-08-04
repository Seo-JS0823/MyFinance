package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDao;
import model.dto.UserDto;
import model.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = UserService.getInstance(new UserDao());
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		UserDto target = userService.login(userid, password);
		
		request.setAttribute("userid", target.getUserid());
		request.setAttribute("username", target.getUsername());
		request.setAttribute("email", target.getEmail());
		request.setAttribute("gender", target.getGender());
		request.setAttribute("birthday", target.getBirthday());
		request.setAttribute("regdate", target.getRegdate());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request,response);
	}

	
	
	
}
