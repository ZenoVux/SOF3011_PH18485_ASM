package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountRole;
import model.Tivi;
import service.TiviService;

@WebServlet("/tivi")
public class TiviServlet extends HttpServlet {

	private TiviService tiviService = new TiviService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (id == null) {
			String page = req.getParameter("page");
			int currPage = 1;
			if (page != null) {
				try {
					currPage = Integer.parseInt(page);
				} catch (Exception e) {
					System.out.println("page");
					resp.sendRedirect("/PH18485_ASM/tivi");
					return;
				}
			}

			int count = tiviService.getCountByFilter("", null, null, null);
			int maxPage = count % 5 == 0 ? count / 5 : (count / 5) + 1;
			if (currPage <= 0 || currPage > maxPage) {
				resp.sendRedirect("/PH18485_ASM/tivi");
				return;
			}
			int beginPage = currPage - 2;
			int endPage = currPage + 2;
			if (beginPage <= 0) {
				beginPage = 1;
			}
			if (endPage > maxPage) {
				endPage = maxPage;
			}
			List<Tivi> listTivi = tiviService.getByFilter("", null, null, null, null,
					5 * (currPage - 1), 5);
			
			req.setAttribute("listTivi", listTivi);
			req.setAttribute("currPage", currPage);
			req.setAttribute("beginPage", beginPage);
			req.setAttribute("endPage", endPage);
			req.setAttribute("maxPage", maxPage);
			req.getRequestDispatcher("/WEB-INF/view/tivi.jsp").forward(req, resp);
		} else {
			Tivi tivi = null;
			try {
				tivi = tiviService.getById(Integer.parseInt(id));
			} catch (Exception e) {
				resp.sendRedirect("/PH18485_ASM/tivi");
				return;
			}
			if (tivi == null) {
				resp.setContentType("text/html;charset=UTF-8");
				resp.getWriter().append("<p>Id không tồn tại</p>");
				return;
			}
			req.setAttribute("tivi", tivi);
			req.getRequestDispatcher("/WEB-INF/view/detail.jsp").forward(req, resp);
		}
	}

}
