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

@WebServlet("/cart/check-out")
public class CartCheckOutServlet extends HttpServlet {

	private final CartService cartService = new CartService();
	private final CartDetailService cartDetailService = new CartDetailService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String id = req.getParameter("id");
		if (id != null) {
			int cartId = 0;
			try {
				cartId = Integer.parseInt(id);
			} catch (Exception e) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Đã có lỗi xảy ra');");
				resp.getWriter().println("location.replace('/PH18485_ASM/index');");
				resp.getWriter().println("</script>");
				return;
			}
			Cart cart = cartService.getById(cartId);
			if (cart == null) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Đã có lỗi xảy ra');");
				resp.getWriter().println("location.replace('/PH18485_ASM/index');");
				resp.getWriter().println("</script>");
				return;
			}
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String id = req.getParameter("id");
		req.setAttribute("url", "http://192.168.0.101:8080/PH18485_ASM/cart/check-out?id=" + id);
		req.getRequestDispatcher("/WEB-INF/view/cart-check-out.jsp").forward(req, resp);
		return;
	}

}
