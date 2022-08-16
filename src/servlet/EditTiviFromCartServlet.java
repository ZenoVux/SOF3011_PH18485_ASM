package servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.CartDetail;
import model.Tivi;
import service.CartDetailService;
import service.CartService;
import service.TiviService;

@WebServlet("/cart/edit")
public class EditTiviFromCartServlet extends HttpServlet {

	private final TiviService tiviService = new TiviService();
	private final CartService cartService = new CartService();
	private final CartDetailService cartDetailService = new CartDetailService();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		int accountId = (Integer) session.getAttribute("id");
		Cart cart = cartService.getLastByAccountId(accountId);
		String strId = req.getParameter("id");
		String strQuantity = req.getParameter("quantity");
		int id = 0;
		int quantity = 1;
		try {
			id = Integer.parseInt(strId);
			quantity = Integer.parseInt(strQuantity);
		} catch (Exception e) {
			return;
		}
		Tivi tivi = tiviService.getById(id);
		if (tivi == null) {
			return;
		}
		if (cart == null) {
			return;
		}
		CartDetail cartDetail = cartDetailService.getByCartIdAndTiviId(cart.getId(), tivi.getId());
		if (cartDetail != null) {
			if (quantity > tivi.getQuantity()) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Sản phẩm không đủ số lượng');");
				resp.getWriter().println("$('#quantity" + tivi.getId() + "').val(" + cartDetail.getQuantity() + ");");
				resp.getWriter().println("</script>");
				return;
			}
			cartDetail.setQuantity(quantity);
			if (cartDetailService.update(cartDetail)) {
				BigDecimal totalMoney = cartService.getTotalMoney(cart.getId());
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("$('#price" + tivi.getId()
						+ "').html(new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format("
						+ (quantity * tivi.getPrice().intValue()) + "));");
				resp.getWriter().println(
						"$('#totalMoney').html(new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format("
								+ totalMoney + "));");
				resp.getWriter().println("</script>");
			}
		} else {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Lỗi');");
			resp.getWriter().println("</script>");
		}
	}
}
