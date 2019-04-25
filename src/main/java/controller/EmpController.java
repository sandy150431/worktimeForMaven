package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import check.ChineseChange;
import check.IdTest;
import check.InputCheck;
import check.TypeChange;
import dao.EmpDAO;
import model.Emp;

@Controller
public class EmpController {

	EmpDAO empDAO = null;
	String empNo = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		empNo = request.getParameter("empNo");

		// 測試
		// ServletOutputStream out = response.getOutputStream();
		// out.println("action="+action);
		// out.println("emp="+empNo);

		if (ChineseChange.u2i("查詢").equals(action)) {// 查詢
			// displayEmp(request, response);
		} else if ("listAll".equals(action)) {// 列全部員工
			// displayAllEmp(request, response);
		} else if (ChineseChange.u2i("修改").equals(action)) {// 去修改員工頁面
			//preupdateEmp(request, response);
		} else if (ChineseChange.u2i("確認修改").equals(action)) {// 修改員工
			//updateEmp(request, response);
		} else if (ChineseChange.u2i("離職查詢").equals(action)) {// 修改員工
			displayEmpResign(request, response);
		} else if (ChineseChange.u2i("修改離職註記").equals(action)) {// 修改員工
			updateEmpResign(request, response);
		} else if (ChineseChange.u2i("刪除離職註記").equals(action)) {// 修改員工
			deleEmpResign(request, response);
		} else if (ChineseChange.u2i("新增").equals(action)) {// 修改員工
			// insEmp(request, response);
		} else if (ChineseChange.u2i("刪除").equals(action)) {// 刪除員工
			// deleEmp(request, response);
		}

	}

	// 刪除員工
	@RequestMapping("/deleEmp")
	private String deleEmp(@RequestParam(value = "empNo", required = true) String empNo) {
		JFrame jf = new JFrame();
		EmpDAO empDAO = new EmpDAO();
		try {
			empDAO.delEmpById(empNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jf.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(jf, "刪除成功");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// request.getRequestDispatcher("adminFindEmp.jsp").forward(request, response);
		return "admin/adminFindEmp";
	}

	private void deleEmpResign(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		JFrame jf = new JFrame();
		HttpSession session = request.getSession();
		Emp emp = (Emp) session.getAttribute("emp");
		EmpDAO empDAO = new EmpDAO();
		try {
			int cnt = empDAO.delResignById(emp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jf.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(jf, "刪除離職註記成功");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			request.getRequestDispatcher("adminResignFind.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}

	}

	// 離職註記查詢
	private void displayEmpResign(HttpServletRequest request, HttpServletResponse response) {
		JFrame jf = new JFrame();
		EmpDAO empDAO = new EmpDAO();
		Emp emp = null;

		if (empNo == null || empNo.equals("")) {
			try {
				request.getRequestDispatcher("adminResignFind.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		} else {
			try {
				emp = empDAO.findEmpResignByNo(empNo);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (emp == null || emp.equals("")) {

				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "查無該員工");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				try {
					request.getRequestDispatcher("adminResignFind.jsp").forward(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ServletException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("emp", emp);
				try {
					request.getRequestDispatcher("adminResign.jsp").forward(request, response);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ServletException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			}
		}
	}

	// 修改離職註記
	private void updateEmpResign(HttpServletRequest request, HttpServletResponse response) {
		JFrame jf = new JFrame();
		HttpSession session = request.getSession();
		Emp emp = (Emp) session.getAttribute("emp");
		empNo = emp.getEmpNo();

		Integer resign = null;
		String resignDateString = request.getParameter("resignDate");
		java.sql.Date resignDate = null;

		if (InputCheck.isValidDate(resignDateString) == false) {
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "請輸入正確的日期格式");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			displayEmpResign(request, response);
		} else {
			resignDate = TypeChange.stringToSqlDate(resignDateString);
			resign = Integer.parseInt(request.getParameter("resign"));
			// resign = Integer.valueOf(request.getParameter("hrs"));
			EmpDAO empDAO = new EmpDAO();
			// 填進emp
			emp.setResign(resign);
			emp.setResignDate(resignDate);

			try {
				empDAO.updateEmpResign(emp);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				request.getRequestDispatcher("adminResignFind.jsp").forward(request, response);
				;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				se.printStackTrace();
			}
		}
	}

	// 新增員工資料
	@RequestMapping("/addEmp")
	public String insEmp(//
			@RequestParam(value = "empName", required = true) String empName, //
			@RequestParam(value = "pw", required = true) String pw, //
			@RequestParam(value = "twid", required = true) String twid, //
			@RequestParam(value = "email", required = true) String email, //
			@RequestParam(value = "firstDate", required = true) String firstDateString, //
			@RequestParam(value = "authority", required = true) String authority, //
			@RequestParam(value = "sex", required = true) String sexs) {

		JFrame jf = new JFrame();
		boolean id, date, mail;
		id = IdTest.isValidTWPID(twid);
		mail = IdTest.isValidEmail(email);
		date = IdTest.isValidDate(firstDateString);
		int sex = 0;
		java.sql.Date firstDate = null;

		if (id == false || date == false || mail == false) {

			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "請輸入正確的格式");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			return "admin/adminAddEmp";

		} else {
			sex = Integer.parseInt(sexs);
			firstDate = TypeChange.stringToSqlDate(firstDateString);
			try {
				EmpDAO empDAO = new EmpDAO();
				Emp emp = new Emp();
				emp.setEmpName(empName);
				emp.setPw(pw);
				emp.setTwid(twid);
				emp.setSex(sex);
				emp.setEmail(email);
				emp.setFirstDate(firstDate);
				emp.setAuthority(authority);
				empNo = empDAO.insEmp(emp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// TODO寄信功能
			// NewEmpMail.main(null);
			// NewEmpMail.sendMail(email);
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "新增成功");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			return "admin/adminFindEmp";
		}
	}

	// 修改員工資料
	@RequestMapping("/updateEmp")
	private String updateEmp(//
			@RequestParam(value = "empNo", required = true) String empNo, //
			@RequestParam(value = "empName", required = true) String empName, //
			@RequestParam(value = "twid", required = true) String twid, //
			@RequestParam(value = "email", required = true) String email, //
			@RequestParam(value = "firstDate", required = true) String firstDateString, //
			@RequestParam(value = "authority", required = true) String authority, //
			@RequestParam(value = "sex", required = true) String sex,Model model) throws IOException {
		
		JFrame jf = new JFrame();
		java.sql.Date firstDate = null;
		Emp emp = new Emp();
		String returnPath="";
		
		if (InputCheck.updateEmpCheck(empName, email, twid, firstDateString)) {
			firstDate = TypeChange.stringToSqlDate(firstDateString);
			EmpDAO empDAO = new EmpDAO();
			// 填進emp
			emp.setEmpNo(empNo);
			emp.setEmpName(empName);
			emp.setSex(Integer.parseInt(sex));
			emp.setEmail(email);
			emp.setTwid(twid);
			emp.setFirstDate(firstDate);
			emp.setAuthority(authority);

			try {
				empDAO.updateEmp(emp);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 新增完回到查詢頁面
			returnPath = displayEmp(empNo, model);
			
		} else {
			// 格式錯或沒輸入，留在原畫面
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "輸入格式錯誤，請再次檢查");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			returnPath = preupdateEmp(empNo, model);
		}
		return returnPath;

	}

	// 去修改員工資料畫面
	@RequestMapping("/preupdateEmp")
	private String preupdateEmp(@RequestParam(value = "empNo", required = false) String empNo, Model model) {
		try {
			EmpDAO empDAO = new EmpDAO();
			Emp emp = empDAO.findEmpByNo(empNo);
			model.addAttribute("emp", emp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin/adminUpdateEmp";
	}

	// 查詢：列出單筆員工
	@RequestMapping("/displayEmp")
	private String displayEmp(@RequestParam(value = "empNo", required = true) String empNo, Model model) {

		String returnPath = "admin/adminFindEmp";
		JFrame jf = new JFrame();
		EmpDAO empDAO = new EmpDAO();
		Emp emp = null;

		try {
			// 沒有輸入東西，留在原本畫面
			if (empNo == null || empNo.equals("")) {
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "請輸入員工編號");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			} else {
				emp = empDAO.findEmpByNo(empNo);

				if (emp == null || emp.equals("")) {// 沒有抓到東西，留在原本畫面
					jf.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(jf, "查無該員工");
					jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				} else {// 有抓到東西，才去
					model.addAttribute("emp", emp);
					returnPath = "admin/adminOneEmp";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnPath;
	}

	// 列出全部員工
	@RequestMapping("/displayAllEmp")
	private String displayAllEmp() {
		return "admin/adminFindEmp";
	}

}
