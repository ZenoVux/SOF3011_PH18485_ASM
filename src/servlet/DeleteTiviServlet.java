package servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Tivi;
import service.AccountService;
import service.TiviService;

@WebServlet("/tivi/delete")
public class DeleteTiviServlet extends HttpServlet {

	private final TiviService tiviService = new TiviService();
	private final AccountService accountService = new AccountService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String id = req.getParameter("id");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		if (id != null) {
			Tivi tivi = tiviService.getById(Integer.parseInt(id));
			tivi.setDeleted(true);
			tivi.setLastModifiedUser(accountService.getByUsername(username));
			tivi.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
			if (tiviService.updateTivi(tivi)) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Xóa thành công');");
				resp.getWriter().println("location.replace('/PH18485_ASM/tivi');");
				resp.getWriter().println("</script>");
			}

		}
	}
}
