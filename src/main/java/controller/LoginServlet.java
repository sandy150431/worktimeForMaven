package controller;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.LoginDAO;
import dao.NWorktimeDAO;
import model.Emp;
import model.Workhours;

@Controller
public class LoginServlet {

	private String currentUser;

	@RequestMapping(path = "/main")
	public String main() {
		return "login";
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String doPost(//
			@RequestParam(value = "userNo", required = true) String userNo, //
			@RequestParam(value = "password", required = true) String password,Model model) {

		Emp emp = new Emp();
		emp.setEmpNo(userNo);
		emp.setPw(password);
		LoginDAO logindao = new LoginDAO();
		try {
			String userValidate = logindao.authenticateUser(emp);
			if (userValidate.equals("Admin")) {
				return "admin/adminMain";
			} else if (userValidate.equals("Manager")) {
				return "manager/managerMain";
			} else if (userValidate.equals("Employee")) {
				this.currentUser = userNo;
				String yn = "";
				Workhours workhour = new Workhours();
				workhour.setEmpNo(userNo);
				NWorktimeDAO nworktimedao = new NWorktimeDAO();

				//TODO 查詢是否需要催繳工時訊息
				yn = nworktimedao.findme(workhour);
				if (yn.equals("oops")) {
					// req.getSession().setAttribute("hurry", "盡快繳交當周工時 ! !");
				}

				model.addAttribute("userNo", userNo);
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
	
	 public String getCurrentUser() {
	        return this.currentUser;
	    }
}