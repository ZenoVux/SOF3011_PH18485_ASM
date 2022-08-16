package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Account;
import model.Brand;
import model.OperatingSystem;
import model.Resolution;
import model.ScreenType;
import model.Tivi;
import service.AccountService;
import service.BrandService;
import service.OperatingSystemService;
import service.ResolutionService;
import service.ScreenTypeService;
import service.TiviService;

@MultipartConfig
@WebServlet("/tivi/edit")
public class EditTiviServlet extends HttpServlet {

	private final TiviService tiviService = new TiviService();
	private final AccountService accountService = new AccountService();
	private final BrandService brandService = new BrandService();
	private final ResolutionService resolutionService = new ResolutionService();
	private final ScreenTypeService screenTypeService = new ScreenTypeService();
	private final OperatingSystemService operatingSystemService = new OperatingSystemService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		String id = req.getParameter("id");
		if (id != null) {
			try {
				int numId = Integer.parseInt(id);
				Tivi tivi = tiviService.getById(numId);
				req.setAttribute("tivi", tivi);
				req.setAttribute("brands", brandService.getAll());
				req.setAttribute("resolutions", resolutionService.getAll());
				req.setAttribute("screenTypes", screenTypeService.getAll());
				req.setAttribute("operatingSystems", operatingSystemService.getAll());
				req.getRequestDispatcher("/WEB-INF/view/edit.jsp").forward(req, resp);
			} catch (Exception e) {
				resp.sendRedirect("/PH18485_ASM/tivi");
			}
		} else {
			resp.sendRedirect("/PH18485_ASM/tivi");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");

		String id = req.getParameter("id");
		String strName = req.getParameter("name");
		String strDescription = req.getParameter("description");
		String strPrice = req.getParameter("price");
		String strQuantity = req.getParameter("quantity");
		String strScreenSize = req.getParameter("screenSize");
		String strOS = req.getParameter("os");
		String strResolution = req.getParameter("resolution");
		String strScreenType = req.getParameter("screenType");
		String strBrand = req.getParameter("brand");
		// check null
		if (id == null || strName == null || strPrice == null || strQuantity == null || strScreenSize == null
				|| strOS == null || strResolution == null || strScreenType == null || strBrand == null) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Vui lòng nhập đầy đủ các trường');");
//			resp.getWriter().println("location.replace('/PH18485_ASM/tivi/edit?id=" + id + "');");
			resp.getWriter().println("</script>");
			return;
		}
		if (id.trim().isEmpty() || strName.trim().isEmpty() || strPrice.trim().isEmpty() || strQuantity.trim().isEmpty()
				|| strScreenSize.trim().isEmpty() || strOS.trim().isEmpty() || strResolution.trim().isEmpty()
				|| strScreenType.trim().isEmpty() || strBrand.trim().isEmpty()) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Vui lòng nhập đầy đủ các trường');");
//			resp.getWriter().println("location.replace('/PH18485_ASM/tivi/edit?id=" + id + "');");
			resp.getWriter().println("</script>");
			return;
		}

		try {
			int numId = Integer.parseInt(id);
			double numPrice = Double.parseDouble(strPrice);
			if (numPrice < 0) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Giá phải lớn hơn 0');");
//				resp.getWriter().println("location.replace('/PH18485_ASM/tivi/edit?id=" + id + "');");
				resp.getWriter().println("</script>");
				return;
			}
			int numQuantity = Integer.parseInt(strQuantity);
			if (numQuantity < 0) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Số lượng phải lớn hơn 0');");
//				resp.getWriter().println("location.replace('/PH18485_ASM/tivi/edit?id=" + id + "');");
				resp.getWriter().println("</script>");
				return;
			}
			short numScreenSize = Short.parseShort(strScreenSize);
			if (numScreenSize < 0) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Kích thước màn hình phải lớn hơn 0');");
//				resp.getWriter().println("location.replace('/PH18485_ASM/tivi/edit?id=" + id + "');");
				resp.getWriter().println("</script>");
				return;
			}
			int osId = Integer.parseInt(strOS);
			int resolutionId = Integer.parseInt(strResolution);
			int screenTypeId = Integer.parseInt(strScreenType);
			int brandId = Integer.parseInt(strBrand);
			// get Tivi by id
			Tivi tivi = tiviService.getById(numId);
			tivi.setName(strName);
			tivi.setDescription(strDescription);
			tivi.setPrice(BigDecimal.valueOf(numPrice));
			tivi.setQuantity(numQuantity);
			tivi.setScreenSize(numScreenSize);
			// get OS by id
			OperatingSystem operatingSystem = operatingSystemService.getById(osId);
			if (operatingSystem == null) {
				req.setAttribute("message", "Hệ điều hành không hợp lệ");
				req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
				return;
			}
			tivi.setOs(operatingSystem);
			// get Resolution by id
			Resolution resolution = resolutionService.getById(resolutionId);
			if (resolution == null) {
				req.setAttribute("message", "Độ phân giải không hợp lệ");
				req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
				return;
			}
			tivi.setResolution(resolution);
			// get ScreenType by id
			ScreenType screenType = screenTypeService.getById(screenTypeId);
			if (screenType == null) {
				req.setAttribute("message", "Loại màn hình không hợp lệ");
				req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
				return;
			}
			tivi.setScreenType(screenType);
			// get Brand by id
			Brand brand = brandService.getById(brandId);
			if (brand == null) {
				req.setAttribute("id", id);
				req.setAttribute("message", "Hãng không hợp lệ");
				doGet(req, resp);
//				req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
				return;
			}
			tivi.setBrand(brand);
			Account account = accountService.getByUsername(username);
			tivi.setLastModifiedUser(account);
			tivi.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));

			Part part =	req.getPart("image");
			String path = req.getServletContext().getRealPath("/images");
			String fileName = part.getSubmittedFileName();
			if (!Files.exists(Path.of(path))) {
				Files.createDirectory(Path.of(path));
			}
			part.write(path + "/" + fileName);
			tivi.setImage(fileName);
			
			if (!tiviService.updateTivi(tivi)) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Cập nhật sản phẩm thất bại');");
//				resp.getWriter().println("location.replace('/PH18485_ASM/tivi/edit?id=" + id + "');");
				resp.getWriter().println("</script>");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Dữ liệu không hợp lệ');");
//			resp.getWriter().println("location.replace('/PH18485_ASM/tivi/edit?id=" + id + "');");
			resp.getWriter().println("</script>");
			return;
		}
		resp.getWriter().println("<script type=\"text/javascript\">");
		resp.getWriter().println("alert('Cập nhật sản phẩm thành công');");
//		resp.getWriter().println("location.replace('/PH18485_ASM/tivi/edit?id=" + id + "');");
		resp.getWriter().println("</script>");
	}
}
