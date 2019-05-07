package controller;

import javax.servlet.http.HttpServlet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dao.ProDAO;
import model.Pro;

//Servlet implementation class DepartmentController
@Controller
public class ProController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String proCode = null;

	// @see HttpServlet#HttpServlet()

	public ProController() {
		super();
	}

	@RequestMapping("/managerAddPro")
	public String managerAddPro() {
		return "manager/managerAddPro";
	}

	@RequestMapping("/insPro")
	private String insPro(//
			@RequestParam(value = "proCode", required = true) String proCode, //
			@RequestParam(value = "proName", required = true) String proName, //
			Model model) {

		JFrame jf = new JFrame();
		String returnPath = "manager/managerAddPro";

		String check = checkPro(proCode, proName);

		if (StringUtils.isEmpty(check)) {
			try {
				ProDAO proDAO = new ProDAO();
				Pro pro = new Pro();
				pro.setProCode(proCode);
				pro.setProName(proName);
				proDAO.insertPro(pro);

			} catch (Exception e) {
				e.printStackTrace();
			}

			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "新增成功");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		} else {
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, check);
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

		return returnPath;
	}

	private String checkPro(String proCode, String proName) {

		StringBuilder check = new StringBuilder();
		ProDAO proDAO = new ProDAO();
		try {

			if (StringUtils.isEmpty(proCode)) {
				check.append("請輸入專案編號\n");
			} else {
				
				if (!proCode.matches("^[A-Z]{2}[0-9]{4}")) {
					check.append("請輸入正確的專案編號，例如:[AB1234]\n");
				}
				if (!StringUtils.isEmpty(proDAO.findProByCode(proCode).getProCode())) {
					check.append("此專案編號已存在\n");
				}
			}
			
			if (StringUtils.isEmpty(proName)) {
				check.append("請輸入專案名稱\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return check.toString();
	}
}
