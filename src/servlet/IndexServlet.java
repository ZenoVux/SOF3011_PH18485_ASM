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

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

	private final TiviService tiviService = new TiviService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		req.setAttribute("params", req.getParameterMap());
		String page = req.getParameter("page");
		// lấy tham số
		String name = req.getParameter("name");
		String priceMin = req.getParameter("price-min");
		String priceMax = req.getParameter("price-max");
		String quantity = req.getParameter("quantity");
		int currPage = 1;
		int size = 6;
		BigDecimal numPriceMin = null;
		BigDecimal numPriceMax = null;
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
		if (page != null) {
			try {
				currPage = Integer.parseInt(page);
			} catch (Exception e) {
			}
		}

		int count = tiviService.getCountByFilter(name, numPriceMin, numPriceMax, false);
		List<Tivi> listTivi = tiviService.getByFilter(name, quantity, numPriceMin, numPriceMax, false, size * (currPage - 1),
					size);
		int maxPage = count % size == 0 ? count / size : (count / size) + 1;
		if (currPage <= 0 || currPage > maxPage) {
			resp.sendRedirect("/PH18485_ASM/index");
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
		req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
	}
}
