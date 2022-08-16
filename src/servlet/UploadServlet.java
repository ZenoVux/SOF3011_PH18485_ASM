package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/upload.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Part part =	req.getPart("image");
//		String path = req.getServletContext().getRealPath("/images");
//		String fileName = part.getSubmittedFileName();
//		if (!Files.exists(Path.of(path))) {
//			Files.createDirectory(Path.of(path));
//		}
//		part.write(path + "/" + fileName);
//		resp.getWriter().append("<html><img src=\"images/" + fileName + "\" alt=\"\"></html>");
	}
}
