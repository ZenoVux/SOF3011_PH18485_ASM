package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
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

@WebServlet("/cart/check-out")
public class CartCheckOutServlet extends HttpServlet {

	private final CartService cartService = new CartService();
	private final CartDetailService cartDetailService = new CartDetailService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		int accountId = (Integer) session.getAttribute("id");
		String id = req.getParameter("id");
		if (id != null) {
			int cartId = 0;
			try {
				cartId = Integer.parseInt(id);
			} catch (Exception e) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Đã có lỗi xảy ra vui lòng thử lại');");
				resp.getWriter().println("location.replace('/PH18485_ASM/index');");
				resp.getWriter().println("</script>");
				return;
			}
			Cart cart = cartService.getById(cartId);
			if (cart == null) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Đã có lỗi xảy ra vui lòng thử lại');");
				resp.getWriter().println("location.replace('/PH18485_ASM/index');");
				resp.getWriter().println("</script>");
				return;
			}
			if (cart.getAccount().getId() != accountId) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Đã có lỗi xảy ra vui lòng thử lại');");
				resp.getWriter().println("location.replace('/PH18485_ASM/index');");
				resp.getWriter().println("</script>");
				return;
			}
			if (cart.getStatus() == CartStatus.WAITING) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Đã có lỗi xảy ra vui lòng thử lại');");
				resp.getWriter().println("location.replace('/PH18485_ASM/index');");
				resp.getWriter().println("</script>");
				return;
			}
			List<CartDetail> cartDetails = cartDetailService.getByCartId(cart.getId());
			req.setAttribute("cart", cart);
			req.setAttribute("cartDetails", cartDetails);
			req.getRequestDispatcher("/WEB-INF/view/cart-detail.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String id = req.getParameter("id");
		if (id != null) {
			int cartId = 0;
			try {
				cartId = Integer.parseInt(id);
			} catch (Exception e) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Đã có lỗi xảy ra vui lòng thử lại');");
				resp.getWriter().println("location.replace('/PH18485_ASM/index');");
				resp.getWriter().println("</script>");
				return;
			}
			Cart cart = cartService.getById(cartId);
			if (cart == null) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Đã có lỗi xảy ra vui lòng thử lại');");
				resp.getWriter().println("location.replace('/PH18485_ASM/index');");
				resp.getWriter().println("</script>");
				return;
			}
			if (cart.getStatus() != CartStatus.COMFIRMED) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Đã có lỗi xảy ra vui lòng thử lại');");
				resp.getWriter().println("location.replace('/PH18485_ASM/index');");
				resp.getWriter().println("</script>");
				return;
			}
			cart.setCompletedTime(new Timestamp(System.currentTimeMillis()));
			cart.setStatus(CartStatus.COMPLETED);
			if (!cartService.update(cart)) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Thanh toán thất bại');");
				resp.getWriter().println("location.replace('/PH18485_ASM/cart/history');");
				resp.getWriter().println("</script>");
			}
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Thanh toán thành công');");
			resp.getWriter().println("location.replace('/PH18485_ASM/cart/history');");
			resp.getWriter().println("</script>");
		}
	}

}
