package controller;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.List;

import model.Workhours;
import check.ChineseChange;
import check.InputCheck;
import check.TypeChange;
import dao.ExcelDAO;
import dao.ManagerQueryDAO;
import dao.ManagerReviewDAO;

public class ManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ManagerQueryDAO mqDAO = null;
	String empNo = null;

	
	public ManagerController() {
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

		if (ChineseChange.u2i("查詢").equals(action)) {// 查詢
			displayMq(request, response);
		} else if ("listAll".equals(action)) {// 列全部員工
			// displayAllMq(request, response);
		} else if (ChineseChange.u2i("送出審核").equals(action)) {// 審核
			updateStat(request, response);
		}else if (ChineseChange.u2i("匯出").equals(action)) {// 匯出
			excelStat(request, response);
		}
	}
	private void excelStat(HttpServletRequest request,HttpServletResponse response) {
		// TODO Auto-generated method stub
		ExcelDAO excelADAO = new ExcelDAO();
		List<Workhours> workhours = null;
		JFrame jf = new JFrame();
		try {
			workhours = ExcelDAO.excelStat();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "workhour已新增在桌面");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			try {
				request.getRequestDispatcher("managerReview.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}



	private void updateStat(HttpServletRequest request,
			HttpServletResponse response) {
		JFrame jf = new JFrame();
		ManagerReviewDAO mrDAO = new ManagerReviewDAO();
		String[] statList = request.getParameterValues("stat");
		String[] empNoList = request.getParameterValues("empNo");
		String[] weList = request.getParameterValues("we");
		String[] rrList = request.getParameterValues("rr");

		if (InputCheck.isNull(statList) == false) {
			// 取得這個陣列大小
			int size = java.lang.reflect.Array.getLength(statList);

			for (int i = 0; i < size; i++) {
				try {
					mrDAO.updateStat(statList[i], ChineseChange.u2i2(rrList[i]), empNoList[i], weList[i]);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "審核結果已送出");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			try {
				request.getRequestDispatcher("managerReview.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		} else {
			
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "請勾選審核通過或不通過");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			try {
				request.getRequestDispatcher("managerReview.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}
	}

	// 列出單筆員工
	private void displayMq(HttpServletRequest request,
			HttpServletResponse response) {
		JFrame jf = new JFrame();
		ManagerQueryDAO mqDAO = new ManagerQueryDAO();
		Workhours whr1 = new Workhours();
		Workhours whr2 = new Workhours();
		Workhours whr3 = new Workhours();
		Workhours whr4 = new Workhours();
		Workhours whr5 = new Workhours();
		Workhours whr6 = new Workhours();
		Workhours whr7 = new Workhours();

		String empNo = request.getParameter("empNo");
		String ddS = request.getParameter("dd");
		java.sql.Date dd = TypeChange.stringToSqlDate(ddS);
		boolean empNoIsNull = InputCheck.isNull(empNo);
		boolean ddIsNull = InputCheck.isNull(dd);
		
		// 沒有輸入東西，留在原本畫面
		if (empNoIsNull && ddIsNull) {
			
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "請輸入員工編號和欲搜尋日期");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			try {
				request.getRequestDispatcher("managerQuery.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		} else {
			try {
				whr1 = mqDAO.findWorkhoursByNo(empNo, dd, 1);
				whr2 = mqDAO.findWorkhoursByNo(empNo, dd, 2);
				whr3 = mqDAO.findWorkhoursByNo(empNo, dd, 3);
				whr4 = mqDAO.findWorkhoursByNo(empNo, dd, 4);
				whr5 = mqDAO.findWorkhoursByNo(empNo, dd, 5);
				whr6 = mqDAO.findWorkhoursByNo(empNo, dd, 6);
				whr7 = mqDAO.findWorkhoursByNo(empNo, dd, 7);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 沒有抓到東西，留在原本畫面
			if (whr1 == null || whr1.equals("")) {
				
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "查無該員工該日工時紀錄");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				try {
					request.getRequestDispatcher("managerQuery.jsp").forward(request, response);;
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ServletException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			} else {// 有抓到東西，才去
				HttpSession session = request.getSession();
				session.setAttribute("whr1", whr1);
				session.setAttribute("whr2", whr2);
				session.setAttribute("whr3", whr3);
				session.setAttribute("whr4", whr4);
				session.setAttribute("whr5", whr5);
				session.setAttribute("whr6", whr6);
				session.setAttribute("whr7", whr7);

				try {
					request.getRequestDispatcher("managerQueryResult.jsp").forward(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ServletException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			}

		}
	}

	/*
	 * // 列出全部員工 private void displayAllMq(HttpServletRequest request,
	 * HttpServletResponse response) { String empNo =
	 * request.getParameter("empNo"); if (empNo == null || empNo.equals("")) {
	 * try { response.sendRedirect("EmpWithSubmit.jsp"); } catch (IOException e)
	 * { e.printStackTrace(); } } }
	 */

}
