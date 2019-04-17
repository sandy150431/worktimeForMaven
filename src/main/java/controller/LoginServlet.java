package controller;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.LoginDAO;
import dao.NWorktimeDAO;
import model.Emp;
import model.Workhours;

@Controller
public class LoginServlet {

	@RequestMapping(path = "/main")
	protected String main() {
		return "login";
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	protected String doPost(//
			@RequestParam(value = "userNo", required = true) String userNo, //
			@RequestParam(value = "password", required = true) String password) {

		Emp emp = new Emp();
		emp.setEmpNo(userNo);
		emp.setPw(password);
		LoginDAO logindao = new LoginDAO();
		try {
			String userValidate = logindao.authenticateUser(emp);
			if (userValidate.equals("Admin")) {

				// req.getSession().setAttribute("userNo", userNo);
				// req.getRequestDispatcher("/WEB-INF/views/adminMain.jsp").forward(req, res);
				return "admin/adminMain";

			} else if (userValidate.equals("Manager")) {

				// req.getSession().setAttribute("userNo", userNo);
				// req.getRequestDispatcher("/WEB-INF/views/managerMain.jsp").forward(req, res);
				return "manager/managerMain";

			} else if (userValidate.equals("Employee")) {
				String yn = "";
				Workhours workhour = new Workhours();
				workhour.setEmpNo(userNo);
				NWorktimeDAO nworktimedao = new NWorktimeDAO();
				yn = nworktimedao.findme(workhour);
				if (yn.equals("oops")) {
					// req.getSession().setAttribute("hurry", "盡快繳交當周工時 ! !");
				}
				// req.getSession().setAttribute("userNo", userNo);
				// req.getRequestDispatcher("/WEB-INF/views/employeeMain.jsp").forward(req,
				// res);
				return "employee/employeeMain";

			} else {
				JFrame jf = new JFrame();
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "帳號或密碼錯誤");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// req.getSession().setAttribute("userNo", "NotUser");
				// req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, res);
				return "login";
			}

		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			return "login";
		}
		return "login";
	}
}