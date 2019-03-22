package controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import check.ChineseChange;
import check.IdTest;
import check.InputCheck;
import check.TypeChange;

import dao.*;
import model.*;
import mail.NewEmpMail;
public class EmpController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmpDAO empDAO = null;
	String empNo = null;

	public EmpController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
			displayEmp(request, response);
		} else if ("listAll".equals(action)) {// 列全部員工
			displayAllEmp(request, response);
		} else if (ChineseChange.u2i("修改").equals(action)) {// 去修改員工頁面
			preupdateEmp(request, response);
		} else if (ChineseChange.u2i("確認修改").equals(action)) {// 修改員工
			updateEmp(request, response);
		} else if (ChineseChange.u2i("離職查詢").equals(action)) {// 修改員工
			displayEmpResign(request, response);
		} else if (ChineseChange.u2i("修改離職註記").equals(action)) {// 修改員工
			updateEmpResign(request, response);
		} else if (ChineseChange.u2i("刪除離職註記").equals(action)) {// 修改員工
			deleEmpResign(request, response);
		}else if (ChineseChange.u2i("新增").equals(action)) {// 修改員工
			insEmp(request, response);
		}else if (ChineseChange.u2i("刪除").equals(action)) {// 刪除員工  
			deleEmp(request, response);
		}

	}
    private void deleEmpResign(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
    	JFrame jf = new JFrame();
		HttpSession session = request.getSession(); 	
		Emp emp = (Emp)session.getAttribute("emp");
		EmpDAO empDAO = new EmpDAO();
		try {
			int cnt = empDAO.delResignById(emp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}jf.setAlwaysOnTop(true);
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

	//刪除員工
	private void deleEmp(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		JFrame jf = new JFrame();
		HttpSession session = request.getSession(); 	
		Emp emp = (Emp)session.getAttribute("emp");
		EmpDAO empDAO = new EmpDAO();
		try {
			int cnt = empDAO.delEmpById(emp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}jf.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(jf, "刪除成功");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			request.getRequestDispatcher("adminFindEmp.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
	}
		


	// 離職註記查詢
	private void displayEmpResign(HttpServletRequest request,
			HttpServletResponse response) {
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
	private void updateEmpResign(HttpServletRequest request,
			HttpServletResponse response) {
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
				request.getRequestDispatcher("adminResignFind.jsp").forward(request, response);;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}
	}

	// 新增員工資料
	private void insEmp(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		JFrame jf = new JFrame();
		String empName = ChineseChange.u2i2(request.getParameter("empName"));
		String pw = request.getParameter("pw");
		String twid = request.getParameter("twid");
		boolean id ,date ,mail ;
		id = IdTest.isValidTWPID(twid);
		String email = request.getParameter("email");
		mail = IdTest.isValidEmail(email);
		String firstDateString = request.getParameter("firstDate");
		date = IdTest.isValidDate(firstDateString);
		String authority = request.getParameter("authority");
		int sex = 0;
		java.sql.Date firstDate = null;
		
		if (id ==false || date == false || mail ==false) {
			
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "請輸入正確的格式");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			try {
				request.getRequestDispatcher("adminAddEmp.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		} else {
			sex = Integer.parseInt(request.getParameter("sex"));
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
			NewEmpMail.main(null);
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "新增成功");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			try {
				request.getRequestDispatcher("adminFindEmp.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}
	}

	// 修改員工資料
	private void updateEmp(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JFrame jf = new JFrame();
		HttpSession session = request.getSession();
		Emp emp = (Emp) session.getAttribute("emp");
		empNo = emp.getEmpNo();

		String empName = ChineseChange.u2i2(request.getParameter("empName"));
		String email = request.getParameter("email");
		String twid = request.getParameter("twid");
		String firstDateString = request.getParameter("firstDate");
		String authority = request.getParameter("authority");
		int sex = 0;
		java.sql.Date firstDate = null;
		
		if (InputCheck.updateEmpCheck(empName, email, twid, firstDateString)) {
			sex = Integer.parseInt(request.getParameter("sex"));
			firstDate = TypeChange.stringToSqlDate(firstDateString);
			EmpDAO empDAO = new EmpDAO();
			// 填進emp
			emp.setEmpName(empName);
			emp.setSex(sex);
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
			displayEmp(request, response);
		} else {
			// 格式錯或沒輸入，留在原畫面
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "輸入格式錯誤，請再次檢查");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			preupdateEmp(request, response);
		}

	}

	// 去修改員工資料畫面
	private void preupdateEmp(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("adminUpdateEmp.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}

	}

	// 查詢：列出單筆員工
	private void displayEmp(HttpServletRequest request,
			HttpServletResponse response) {
		JFrame jf = new JFrame();
		EmpDAO empDAO = new EmpDAO();
		Emp emp = null;

		// 沒有輸入東西，留在原本畫面
		if (empNo == null || empNo.equals("")) {
			try {
				request.getRequestDispatcher("adminFindEmp.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		} else {
			try {
				emp = empDAO.findEmpByNo(empNo);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 沒有抓到東西，留在原本畫面
			if (emp == null || emp.equals("")) {
				
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "查無該員工");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				try {
					request.getRequestDispatcher("adminFindEmp.jsp").forward(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ServletException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			} else {// 有抓到東西，才去
				HttpSession session = request.getSession();
				session.setAttribute("emp", emp);
				try {
					request.getRequestDispatcher("adminOneEmp.jsp").forward(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ServletException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			}

		}
	}

	// 列出全部員工
	private void displayAllEmp(HttpServletRequest request,
			HttpServletResponse response) {
		String empNo = request.getParameter("empNo");
		if (empNo == null || empNo.equals("")) {
			try {
				request.getRequestDispatcher("adminFindEmp.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}
	}

}
