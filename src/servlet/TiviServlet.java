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
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		req.setAttribute("params", req.getParameterMap());
		String id = req.getParameter("id");
		if (id == null) {
			String page = req.getParameter("page");
			// lấy tham số
			String name = req.getParameter("name");
			String priceMin = req.getParameter("price-min");
			String priceMax = req.getParameter("price-max");
			String quantity = req.getParameter("quantity");
			String deleted = req.getParameter("deleted");
			int currPage = 1;
			BigDecimal numPriceMin = null;
			BigDecimal numPriceMax = null;
			Boolean isDeleted = null;
			if (name == null) {
				name = "";
			}
			if (priceMin != null && priceMax != null) {
				try {
					numPriceMin = BigDecimal.valueOf(Double.parseDouble(priceMin));
					numPriceMax = BigDecimal.valueOf(Double.parseDouble(priceMax));
				} catch (Exception e) {
				}
			}
			if (quantity != null) {
				if (!quantity.equalsIgnoreCase("DESC") && !quantity.equalsIgnoreCase("ASC")) {
					quantity = null;
				}
			}
			if (deleted != null) {
				if (!deleted.equals("true") && !deleted.equals("false")) {
					isDeleted = null;
				} else if (deleted.equals("true")) {
					isDeleted = true;
				} else if (deleted.equals("false")) {
					isDeleted = false;
				}
			}
			if (page != null) {
				try {
					currPage = Integer.parseInt(page);
				} catch (Exception e) {
				}
			}

			int count = 0;
			List<Tivi> listTivi = null;
			if (session.getAttribute("role") == AccountRole.ADMIN) {
				count = tiviService.getCountByFilter(name, numPriceMin, numPriceMax, isDeleted);
				listTivi = tiviService.getByFilter(name, quantity, numPriceMin, numPriceMax, isDeleted,
					5 * (currPage - 1), 5);
			} else {
				count = tiviService.getCountByFilter(name, numPriceMin, numPriceMax, false);
				listTivi = tiviService.getByFilter(name, quantity, numPriceMin, numPriceMax, false,
						5 * (currPage - 1), 5);
			}
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
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Thêm mới sản phẩm thành công');");
				resp.getWriter().println("location.replace('/PH18485_ASM/tivi');");
				resp.getWriter().println("</script>");
				return;
			}
			req.setAttribute("tivi", tivi);
			req.getRequestDispatcher("/WEB-INF/view/detail.jsp").forward(req, resp);
		}
	}

}
