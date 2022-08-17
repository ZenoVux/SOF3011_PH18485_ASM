package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.CartStatus;
import service.CartService;

@WebServlet("/cart/cancel")
public class CartCancelServlet extends HttpServlet {
	
	private final CartService cartService = new CartService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("/PH18485_ASM/index");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
			if (cart.getStatus() != CartStatus.COMFIRMED) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Đã có lỗi xảy ra vui lòng thử lại');");
				resp.getWriter().println("location.replace('/PH18485_ASM/index');");
				resp.getWriter().println("</script>");
				return;
			}
			cart.setStatus(CartStatus.CANCEL);
			if (!cartService.update(cart)) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Hủy đơn hàng thất bại');");
				resp.getWriter().println("location.replace('/PH18485_ASM/cart/history');");
				resp.getWriter().println("</script>");
			}
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Hủy đơn hàng thành công');");
			resp.getWriter().println("location.replace('/PH18485_ASM/cart/history');");
			resp.getWriter().println("</script>");
		}
	}
}
