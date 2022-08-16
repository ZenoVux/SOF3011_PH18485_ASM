package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.Cart;
import model.CartDetail;
import model.CartStatus;
import model.Tivi;
import service.AccountService;
import service.CartDetailService;
import service.CartService;
import service.TiviService;

@WebServlet("/cart/add")
public class AddTiviToCartServlet extends HttpServlet {

	private final AccountService accountService = new AccountService();
	private final TiviService tiviService = new TiviService();
	private final CartService cartService = new CartService();
	private final CartDetailService cartDetailService = new CartDetailService();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		int accountId = (Integer) session.getAttribute("id");
		Account account = accountService.getById(accountId);
		Cart cart = cartService.getLastByAccountId(accountId);
		String strId = req.getParameter("id");
		int id = 0;
		try {
			id = Integer.parseInt(strId);
		} catch (Exception e) {
			return;
		}
		Tivi tivi = tiviService.getById(id);
		if (tivi == null) {
			return;
		}
		if (cart == null) {
			cart = new Cart();
			cart.setAccount(account);
			cart.setStatus(CartStatus.WAITING);
			if (!cartService.create(cart)) {
				return;
			}
		}
		CartDetail cartDetail = cartDetailService.getByCartIdAndTiviId(cart.getId(), tivi.getId());
		if (cartDetail != null) {
			if (cartDetail.getDeleted()) {
				cartDetail.setQuantity(1);
				cartDetail.setDeleted(false);
			} else {
				cartDetail.setQuantity(cartDetail.getQuantity() + 1);
			}
			if (cartDetailService.update(cartDetail)) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println(
						"alert('Thêm sản phẩm vào giỏ hàng thành công');");
				resp.getWriter().println("</script>");
			}
			return;
		}
		cartDetail = new CartDetail();
		cartDetail.setTivi(tivi);
		cartDetail.setCart(cart);
		cartDetail.setDeleted(false);
		cartDetail.setQuantity(1);
		if (cartDetailService.create(cartDetail)) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Thêm sản phẩm vào giỏ hàng thành công');");
			resp.getWriter().println("</script>");
		}
	}
}
