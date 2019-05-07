package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.HurryWtDAO;
import model.Workhours;

@Controller
public class HurryWtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HurryWtDAO hurrywtdao = new HurryWtDAO();
	Workhours workhour = new Workhours();

	public HurryWtServlet() {
		super();
	}

	@RequestMapping("/managerHurryWt")
	public String managerHurryWt() {
		return "manager/managerHurryWt";
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");

		JFrame jf = new JFrame();
		jf.setAlwaysOnTop(true);

		JOptionPane.showMessageDialog(jf, "催繳已送出");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			hurrywtdao.updateHurry(workhour);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("./managerHurryWt.jsp").forward(request, response);
	}

}
