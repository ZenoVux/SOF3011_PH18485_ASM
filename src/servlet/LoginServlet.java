package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import service.AuthenticationService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private AuthenticationService authenticationService = new AuthenticationService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (username == null || password == null) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Vui lòng nhập đầy đủ các trường');");
			resp.getWriter().println("</script>");
			return;
		}
		if (username.trim().isEmpty() || password.trim().isEmpty()) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Vui lòng nhập đầy đủ các trường');");
			resp.getWriter().println("</script>");
			return;
		}
		Account account = authenticationService.authenticate(username, password);
		HttpSession session = req.getSession();
		if (account == null) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Tài khoản hoặc mật khẩu không chính xác!');");
			resp.getWriter().println("</script>");
		} else {
			session.setAttribute("id", account.getId());
			session.setAttribute("username", account.getUsername());
			session.setAttribute("fullname", account.getFullname());
			session.setAttribute("role", account.getRole());

			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("location.replace('/PH18485_ASM/index');");
			resp.getWriter().println("</script>");
		}
	}
}
