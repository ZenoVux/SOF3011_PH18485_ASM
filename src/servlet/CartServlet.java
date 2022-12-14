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

import model.Account;
import model.Cart;
import model.CartDetail;
import model.CartStatus;
import model.Tivi;
import service.AccountService;
import service.CartDetailService;
import service.CartService;
import service.TiviService;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	private final AccountService accountService = new AccountService();
	private final CartService cartService = new CartService();
	private final CartDetailService cartDetailService = new CartDetailService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int accountId = (Integer) session.getAttribute("id");
		Account account = accountService.getById(accountId);
		Cart cart = cartService.getLastByAccountId(accountId);
		if (cart == null) {
			cart = new Cart();
			cart.setAccount(account);
			cart.setStatus(CartStatus.WAITING);
			if (!cartService.create(cart)) {
				return;
			}
		}
		List<CartDetail> cartDetails = cartDetailService.getByCartId(cart.getId());
		BigDecimal totalMoney = cartService.getTotalMoney(cart.getId());
		req.setAttribute("cartDetails", cartDetails);
		req.setAttribute("totalMoney", totalMoney);
		req.setAttribute("size", cartDetails.size());
		req.getRequestDispatcher("/WEB-INF/view/cart.jsp").forward(req, resp);
	}
}
