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
@WebServlet("/tivi/add")
public class AddTiviServlet extends HttpServlet {

	private final TiviService tiviService = new TiviService();
	private final AccountService accountService = new AccountService();
	private final BrandService brandService = new BrandService();
	private final ResolutionService resolutionService = new ResolutionService();
	private final ScreenTypeService screenTypeService = new ScreenTypeService();
	private final OperatingSystemService operatingSystemService = new OperatingSystemService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Brand> brands = brandService.getAll();
		List<Resolution> resolutions = resolutionService.getAll();
		List<ScreenType> screenTypes = screenTypeService.getAll();
		List<OperatingSystem> operatingSystems = operatingSystemService.getAll();
		req.setAttribute("brands", brands);
		req.setAttribute("resolutions", resolutions);
		req.setAttribute("screenTypes", screenTypes);
		req.setAttribute("operatingSystems", operatingSystems);
		req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String strName = req.getParameter("name");
		String strDescription = req.getParameter("description");
		String strPrice = req.getParameter("price");
		String strQuantity = req.getParameter("quantity");
		String strScreenSize = req.getParameter("screenSize");
		String strOS = req.getParameter("os");
		String strResolution = req.getParameter("resolution");
		String strScreenType = req.getParameter("screenType");
		String strBrand = req.getParameter("brand");
		
		System.out.println(strName);
		// check null
		if (strName == null || strPrice == null || strQuantity == null || strScreenSize == null || strOS == null
				|| strResolution == null || strScreenType == null || strBrand == null) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Vui l??ng nh???p ?????y ????? c??c tr?????ng');");
			resp.getWriter().println("</script>");
			return;
		}
		// cut space char and check empty
		if (strName.trim().isEmpty() || strPrice.trim().isEmpty() || strQuantity.trim().isEmpty()
				|| strScreenSize.trim().isEmpty() || strOS.trim().isEmpty() || strResolution.trim().isEmpty()
				|| strScreenType.trim().isEmpty() || strBrand.trim().isEmpty()) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Vui l??ng nh???p ?????y ????? c??c tr?????ng');");
			resp.getWriter().println("</script>");
			return;
		}

		try {
			double numPrice = Double.parseDouble(strPrice);
			if (numPrice < 0) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Gi?? ph???i l???n h??n 0');");
				resp.getWriter().println("</script>");
				return;
			}
			int numQuantity = Integer.parseInt(strQuantity);
			if (numQuantity < 0) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('S??? l?????ng ph???i l???n h??n 0');");
				resp.getWriter().println("</script>");
				return;
			}
			short numScreenSize = Short.parseShort(strScreenSize);
			if (numScreenSize < 0) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('K??ch th?????c m??n h??nh ph???i l???n h??n 0');");
				resp.getWriter().println("</script>");
				return;
			}
			
			int osId = Integer.parseInt(strOS);
			int resolutionId = Integer.parseInt(strResolution);
			int screenTypeId = Integer.parseInt(strScreenType);
			int brandId = Integer.parseInt(strBrand);
			// create tivi obj
			Tivi tivi = new Tivi();
			tivi.setName(strName);
			tivi.setDescription(strDescription);
			tivi.setPrice(BigDecimal.valueOf(numPrice));
			tivi.setQuantity(numQuantity);
			tivi.setScreenSize(numScreenSize);
			// get OS by id
			OperatingSystem operatingSystem = operatingSystemService.getById(osId);
			if (operatingSystem == null) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('H??? ??i???u h??nh kh??ng h???p l???');");
				resp.getWriter().println("</script>");
				return;
			}
			tivi.setOs(operatingSystem);
			// get Resolution by id
			Resolution resolution = resolutionService.getById(resolutionId);
			if (resolution == null) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('????? ph??n gi???i kh??ng h???p l???');");
				resp.getWriter().println("</script>");
				return;
			}
			tivi.setResolution(resolution);
			// get ScreenType by id
			ScreenType screenType = screenTypeService.getById(screenTypeId);
			if (screenType == null) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Lo???i m??n h??nh kh??ng h???p l???');");
				resp.getWriter().println("</script>");
				return;
			}
			tivi.setScreenType(screenType);
			// get Brand by id
			Brand brand = brandService.getById(brandId);
			if (brand == null) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('H??ng kh??ng h???p l???');");
				resp.getWriter().println("</script>");
				return;
			}
			tivi.setBrand(brand);
			Account account = accountService.getByUsername(username);
			tivi.setCreateUser(account);
			tivi.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			tivi.setLastModifiedUser(account);
			tivi.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
			tivi.setDeleted(false);
			

			Part part =	req.getPart("image");
			String path = req.getServletContext().getRealPath("/images");
			String fileName = part.getSubmittedFileName();
			if (!Files.exists(Path.of(path))) {
				Files.createDirectory(Path.of(path));
			}
			part.write(path + "/" + fileName);
			tivi.setImage(fileName);

			if (!tiviService.createTivi(tivi)) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Th??m m???i s???n ph???m th???t b???i');");
				resp.getWriter().println("</script>");
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();

//			req.setAttribute("message", "Th??m m???i s???n ph???m th??nh c??ng");
//			req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('D??? li???u kh??ng h???p l???');");
			resp.getWriter().println("</script>");
			return;
		}

		resp.getWriter().println("<script type=\"text/javascript\">");
		resp.getWriter().println("alert('Th??m m???i s???n ph???m th??nh c??ng');");
		resp.getWriter().println("location.replace('/PH18485_ASM/tivi/add');");
		resp.getWriter().println("</script>");
	}
}
