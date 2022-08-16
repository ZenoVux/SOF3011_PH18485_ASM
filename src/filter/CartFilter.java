package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountRole;

@WebFilter("/cart/*")
public class CartFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		resp.setContentType("text/html;charset=UTF-8");

		if (session.getAttribute("username") != null) {
			chain.doFilter(request, response);
		} else {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Vui lòng đăng nhập');");
			resp.getWriter().println("location.replace('/PH18485_ASM/login');");
			resp.getWriter().println("</script>");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
