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

@WebServlet("/cart/confirm")
public class CartConfirmServlet extends HttpServlet {
	
	private final CartService cartService = new CartService();
	private final CartDetailService cartDetailService = new CartDetailService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		int accountId = (Integer) session.getAttribute("id");
		Cart cart = cartService.getLastByAccountId(accountId);
		if (cart == null) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Không có sản phẩm trong giỏ hàng');");
			resp.getWriter().println("location.replace('/PH18485_ASM/index');");
			resp.getWriter().println("</script>");
			return;
		}
		List<CartDetail> cartDetails = cartDetailService.getByCartId(cart.getId());
		if (cartDetails.size() <= 0) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Không có sản phẩm trong giỏ hàng');");
			resp.getWriter().println("location.replace('/PH18485_ASM/index');");
			resp.getWriter().println("</script>");
			return;
		}
		BigDecimal totalMoney = cartService.getTotalMoney(cart.getId());
		req.setAttribute("totalMoney", totalMoney);
		req.getRequestDispatcher("/WEB-INF/view/cart-confirm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		int accountId = (Integer) session.getAttribute("id");
		Cart cart = cartService.getLastByAccountId(accountId);
		if (cart == null) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Không có sản phẩm trong giỏ hàng');");
			resp.getWriter().println("location.replace('/PH18485_ASM/index');");
			resp.getWriter().println("</script>");
			return;
		}
		List<CartDetail> cartDetails = cartDetailService.getByCartId(cart.getId());
		if (cartDetails.size() <= 0) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Không có sản phẩm trong giỏ hàng');");
			resp.getWriter().println("location.replace('/PH18485_ASM/index');");
			resp.getWriter().println("</script>");
			return;
		}
		String fullname = req.getParameter("fullname");
		String phoneNumber = req.getParameter("phoneNumber");
		String address = req.getParameter("address");
		if (fullname == null || phoneNumber == null || address == null) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Vui lòng nhập đầy đủ các trường');");
			resp.getWriter().println("</script>");
			return;
		}
		if (fullname.trim().isEmpty() || phoneNumber.trim().isEmpty() || address.trim().isEmpty()) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Vui lòng nhập đầy đủ các trường');");
			resp.getWriter().println("</script>");
			return;
		}
		if (!phoneNumber.matches("(((\\+|)84)|0)(3|5|7|8|9)+([0-9]{8})")) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Số điện thoại không hợp lệ');");
			resp.getWriter().println("</script>");
			return;
		}
		cart.setFullname(fullname);
		cart.setPhoneNumber(phoneNumber);
		cart.setAddress(address);
		cart.setTotalMoney(cartService.getTotalMoney(cart.getId()));
		cart.setStatus(CartStatus.COMFIRMED);
		if (!cartService.update(cart)) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Xác nhận đơn hàng thất bại');");
			resp.getWriter().println("</script>");
		}
		resp.getWriter().println("<script type=\"text/javascript\">");
		resp.getWriter().println("alert('Xác nhận đơn hàng thành công');");
		resp.getWriter().println("</script>");

		req.setAttribute("cart", cart);
		req.setAttribute("cartDetails", cartDetails);
		req.getRequestDispatcher("/WEB-INF/view/cart-detail.jsp").forward(req, resp);
	}
	
}
