package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Tivi;
import service.TiviService;

@WebServlet("/tivi/filter")
public class FilterTiviServlet extends HttpServlet {

	private TiviService tiviService = new TiviService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String page = req.getParameter("page");
		String name = req.getParameter("name");
		String priceMin = req.getParameter("priceMin");
		String priceMax = req.getParameter("priceMax");
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
				numPriceMin = BigDecimal.valueOf(Double.parseDouble(priceMax));
			} catch (Exception e) {
//				resp.sendRedirect("/PH18485_ASM/tivi");
				return;
			}
		}
		if (quantity != null) {
			if (!quantity.equals("DESC") && !quantity.equals("ASC")) {
				quantity = null;
			}
		}
		if (deleted != null) {
			if (!deleted.equals("true") && !deleted.equals("false")) {
				isDeleted = null;
			}
			else if (deleted.equals("true")) {
				isDeleted = true;
			}
			else if (deleted.equals("false")) {
				isDeleted = false;
			}
		}
		if (page != null) {
			try {
				currPage = Integer.parseInt(page);
			} catch (Exception e) {
				System.out.println("page");
//				resp.sendRedirect("/PH18485_ASM/tivi");
				return;
			}
		}

		int count = tiviService.getCountByFilter(name, numPriceMin, numPriceMax, isDeleted);
		int maxPage = count % 5 == 0 ? count / 5 : (count / 5) + 1;
		if (currPage <= 0 || currPage > maxPage) {
//			resp.sendRedirect("/PH18485_ASM/tivi");
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
		List<Tivi> listTivi = tiviService.getByFilter(name, quantity, numPriceMin, numPriceMax, isDeleted,
				5 * (currPage - 1), 5);
		req.setAttribute("name", name);
		req.setAttribute("priceMin", priceMin);
		
		req.setAttribute("listTivi", listTivi);
		req.setAttribute("currPage", currPage);
		req.setAttribute("beginPage", beginPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("maxPage", maxPage);
		req.getRequestDispatcher("/WEB-INF/view/includes/table.jsp").forward(req, resp);
	}

}
