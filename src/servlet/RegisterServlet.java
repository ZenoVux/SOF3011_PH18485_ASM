package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.AccountRole;
import service.AccountService;
import service.AuthenticationService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private AuthenticationService authenticationService = new AuthenticationService();
	private AccountService accountService = new AccountService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String username = req.getParameter("username");
		String fullname = req.getParameter("fullname");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		if (username == null || fullname == null || password == null || confirmPassword == null) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Vui lòng nhập đầy đủ các trường');");
			resp.getWriter().println("</script>");
			return;
		}
		if (username.trim().isEmpty() || fullname.trim().isEmpty() || password.trim().isEmpty()
				|| confirmPassword.trim().isEmpty()) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Vui lòng nhập đầy đủ các trường');");
			resp.getWriter().println("</script>");
			return;
		}
		if (authenticationService.checkUsername(username)) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Tên đăng nhập đã tồn tại');");
			resp.getWriter().println("</script>");
			return;
		}
//		if (!role.equals("admin") || !role.equals("user")) {
//			req.setAttribute("message", "Vai trò không tồn tại");
//			req.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(req, resp);
//			return;
//		}
		if (password.length() < 8) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Mật khẩu tối thiểu có 8 ký tự');");
			resp.getWriter().println("</script>");
			return;
		}
//		if (password.length() > 16) {
//			req.setAttribute("message", "Mật khẩu tối đa có 16 ký tự");
//			req.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(req, resp);
//			return;
//		}
		if (!password.equals(confirmPassword)) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Xác nhận mật khẩu không trùng với mật khẩu');");
			resp.getWriter().println("</script>");
			return;
		}
		Account account = new Account();
		account.setUsername(username);
		account.setFullname(fullname);
		account.setPassword(password);
		account.setRole(AccountRole.USER);
		if (!accountService.createAccount(account)) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Đăng ký thất bại');");
			resp.getWriter().println("</script>");
			return;
		}
		resp.getWriter().println("<script type=\"text/javascript\">");
		resp.getWriter().println("alert('Đăng ký thành công');");
		resp.getWriter().println("location.replace('/PH18485_ASM/login');");
		resp.getWriter().println("</script>");
	}
}
