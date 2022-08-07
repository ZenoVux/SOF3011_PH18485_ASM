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
		// check null
		if (strName == null || strPrice == null || strQuantity == null || strScreenSize == null || strOS == null
				|| strResolution == null || strScreenType == null || strBrand == null) {
			req.setAttribute("message", "Vui lòng nhập đầy đủ các trường");
			req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
			return;
		}
		// cut space char and check empty
		if (strName.trim().isEmpty() || strPrice.trim().isEmpty() || strQuantity.trim().isEmpty()
				|| strScreenSize.trim().isEmpty() || strOS.trim().isEmpty() || strResolution.trim().isEmpty()
				|| strScreenType.trim().isEmpty() || strBrand.trim().isEmpty()) {
			req.setAttribute("message", "Vui lòng nhập đầy đủ các trường");
			req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
			return;
		}

		try {
			double numPrice = Double.parseDouble(strPrice);
			if (numPrice < 0) {
				req.setAttribute("message", "Giá phải lớn hơn 0");
				req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
				return;
			}
			int numQuantity = Integer.parseInt(strQuantity);
			if (numQuantity < 0) {
				req.setAttribute("message", "Số lượng phải lớn hơn 0");
				req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
				return;
			}
			short numScreenSize = Short.parseShort(strScreenSize);
			if (numScreenSize < 0) {
				req.setAttribute("message", "Kích thước màn hình phải lớn hơn 0");
				req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
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
				req.setAttribute("message", "Hãng không hợp lệ");
				req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
				return;
			}
			tivi.setBrand(brand);
			Account account = accountService.getByUsername(username);
			tivi.setCreateUser(account);
			tivi.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			tivi.setLastModifiedUser(account);
			tivi.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
			tivi.setDeleted(false);

			if (!tiviService.createTivi(tivi)) {
				req.setAttribute("message", "Thêm mới sản phẩm thất bại");
				req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
				return;
			}
		} catch (Exception e) {
			req.setAttribute("message", "Dữ liệu không hợp lệ");
			req.getRequestDispatcher("/WEB-INF/view/edit.jsp").forward(req, resp);
			return;
		}

		req.setAttribute("message", "Thêm mới sản phẩm thành công");
		req.getRequestDispatcher("/WEB-INF/view/add.jsp").forward(req, resp);
	}
}
