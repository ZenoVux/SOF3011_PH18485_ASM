package servlet;

import java.io.IOException;
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
import model.Tivi;
import service.AccountService;
import service.CartDetailService;
import service.CartService;
import service.TiviService;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	private final AccountService accountService = new AccountService();
	private final TiviService tiviService = new TiviService();
	private final CartService cartService = new CartService();
	private final CartDetailService cartDetailService = new CartDetailService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int accountId = (Integer) session.getAttribute("id");
		Cart cart = cartService.getLastByAccountId(accountId);
		List<CartDetail> cartDetails = cartDetailService.getByCartId(cart.getId());
		req.setAttribute("cartDetails", cartDetails);
		req.getRequestDispatcher("/WEB-INF/view/cart.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		int accountId = (Integer) session.getAttribute("id");
		Account account = accountService.getById(accountId);
		Cart cart = cartService.getLastByAccountId(accountId);
		String action = req.getParameter("action");
		String strId = req.getParameter("id");
		if (action == null) {
			return;
		}
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
		switch (action) {
		case "add":
			if (cart == null) {
				cart = new Cart();
				cart.setAccount(account);
				cart.setCompleted(false);
				if (!cartService.create(cart)) {
					return;
				}
			}
			CartDetail cartDetail = cartDetailService.getByCartIdAndTiviId(cart.getId(), tivi.getId());
			if (cartDetail != null) {
				cartDetail.setQuantity(2);
				if (cartDetailService.update(cartDetail)) {
					resp.getWriter().println("<script type=\"text/javascript\">");
					resp.getWriter().println(
							"alert('Thêm sản phẩm vào giỏ hàng thành công " + cartDetail.getQuantity() + "');");
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
			break;
		case "change":
			int quantity = Integer.parseInt(req.getParameter("quantity"));
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println(
					"$('#price').html(new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format("
							+ (quantity * tivi.getPrice().intValue()) + "));");
			resp.getWriter().println("</script>");
			break;
		case "remove":
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Xóa sản phẩm thành công');");
			resp.getWriter().println("</script>");
			break;
		default:
			break;
		}
	}
}
