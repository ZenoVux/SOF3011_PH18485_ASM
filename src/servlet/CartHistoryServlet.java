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

import model.Cart;
import model.CartDetail;
import model.CartStatus;
import service.CartDetailService;
import service.CartService;

@WebServlet("/cart/history")
public class CartHistoryServlet extends HttpServlet {
	
	private final CartService cartService = new CartService();
	private final CartDetailService cartDetailService = new CartDetailService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int accountId = (Integer) session.getAttribute("id");
		String id = req.getParameter("id");
		if (id != null) {
			int cartId = 0;
			try {
				cartId = Integer.parseInt(id);
			} catch (Exception e) {
				return;
			}
			Cart cart = cartService.getById(cartId);
			if (cart == null) {
				return;
			}
			if (cart.getAccount().getId() != accountId) {
				return;
			}
			List<CartDetail> cartDetails = cartDetailService.getByCartId(cart.getId());
			req.setAttribute("cart", cart);
			req.setAttribute("cartDetails", cartDetails);
			req.getRequestDispatcher("/WEB-INF/view/cart-detail.jsp").forward(req, resp);
		} else {
			List<Cart> carts = cartService.getAllByAccountId(accountId);
			req.setAttribute("carts", carts);
			req.setAttribute("size", carts.size());
			req.getRequestDispatcher("/WEB-INF/view/cart-history.jsp").forward(req, resp);
		}
	}
	
}
