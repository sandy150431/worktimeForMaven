package controller;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import check.IdTest;
import check.InputCheck;
import check.TypeChange;
import dao.EmpDAO;
import model.Emp;

@Controller
public class EmpController {

	EmpDAO empDAO = null;
	String empNo = null;

	@RequestMapping("/resignMain")
	public String resignMain() {
		return "admin/adminResignFind";
	}

	@RequestMapping("/addEmpMain")
	public String addEmpMain() {
		return "admin/adminAddEmp";
	}

	@RequestMapping("/findEmp")
	public String findEmp() {
		return "admin/adminFindEmp";
	}

	//刪除離職註記
	@RequestMapping("/deleEmpResign")
	private String deleEmpResign(//
			@RequestParam(value = "empNo", required = true) String empNo,Model model) {
		
		JFrame jf = new JFrame();
		EmpDAO empDAO = new EmpDAO();
		try {
			empDAO.delResignById(empNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jf.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(jf, "刪除離職註記成功");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		return "admin/adminResignFind";

	}

	// 離職註記查詢
	@RequestMapping("/displayEmpResign")
	private String displayEmpResign(//
			@RequestParam(value = "empNo", required = true) String empNo,Model model) {

		JFrame jf = new JFrame();
		EmpDAO empDAO = new EmpDAO();
		Emp emp = null;

		if (empNo == null || empNo.equals("")) {
			return "admin/adminResignFind";
		} else {
			try {
				emp = empDAO.findEmpResignByNo(empNo);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (emp == null) {
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "查無該員工");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				return "admin/adminResignFind";
				
			} else {
				model.addAttribute("emp", emp);
				return "admin/adminResign";
			}
		}
	}

	// 修改離職註記
	@RequestMapping("/updateEmpResign")
	private String updateEmpResign(//
			@RequestParam(value = "empNo", required = true) String empNo,//
			@RequestParam(value = "resign", required = true) String resign,//
			@RequestParam(value = "resignDate", required = true) String resignDate,//
			Model model) {
		
		JFrame jf = new JFrame();
		String returnPath;
		
		if (InputCheck.isValidDate(resignDate) == false) {
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "請輸入正確的日期格式");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			returnPath = displayEmpResign(empNo, model);
			
		} else {
			Emp emp = new Emp();
			EmpDAO empDAO = new EmpDAO();
			// 填進emp
			emp.setEmpNo(empNo);
			emp.setResign(Integer.parseInt(resign));
			emp.setResignDate(TypeChange.stringToSqlDate(resignDate));

			try {
				empDAO.updateEmpResign(emp);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			returnPath="admin/adminResignFind";
		}
		return returnPath;
	}

	// 新增員工資料
	@RequestMapping("/addEmp")
	private String insEmp(//
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
			@RequestParam(value = "sex", required = true) String sex, Model model) throws IOException {

		JFrame jf = new JFrame();
		java.sql.Date firstDate = null;
		Emp emp = new Emp();
		String returnPath = "";

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

				if (emp == null) {// 沒有抓到東西，留在原本畫面
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

	// 列出全部員工
	@RequestMapping("/displayAllEmp")
	private String displayAllEmp() {
		return "admin/adminFindEmp";
	}

}
