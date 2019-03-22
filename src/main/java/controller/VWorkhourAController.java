package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import check.ChineseChange;
import dao.VWorkhourADAO;
import model.VWorkhourA;

public class VWorkhourAController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VWorkhourADAO vWorkhourADAO = null;
	String proCode = null;
	public VWorkhourAController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		//String proCode = request.getParameter("proCode");

		if (ChineseChange.u2i("查詢").equals(action)) {// 查詢
			displayWorkhour(request, response);
		} else if ("SearchAll".equals(action)) {// 列全部員工
			displayAllWorkhour(request, response);
		} 
	}
	

	// 列出單筆員工
	private void displayWorkhour(HttpServletRequest request,HttpServletResponse response) {
		VWorkhourADAO vWorkhourADAO = new VWorkhourADAO();
		List<VWorkhourA> vWorkhourA = null;
		JFrame jf = new JFrame();
		String proCode = request.getParameter("proCode");
		
		// 沒有輸入東西，留在原本畫面
		if (proCode == null || proCode.equals("")) {
			
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "請輸入欲查詢之專案代碼");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			try {
				request.getRequestDispatcher("managerProSearch1.jsp").forward(request, response);//EmpWithSubmit
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		} else {
			try {
				vWorkhourA = vWorkhourADAO.findAllWorkhoure(proCode);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 沒有抓到東西，留在原本畫面
			if (vWorkhourA == null || vWorkhourA.equals("") || vWorkhourA.isEmpty() ) {
				
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "查無該專案");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
				try {
					request.getRequestDispatcher("managerProSearch1.jsp").forward(request, response);//EmpWithSubmit
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ServletException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			} else {// 有抓到東西，才去
				HttpSession session = request.getSession();
				//session.setAttribute("vWorkhourA", vWorkhourA);
				session.setAttribute("proCode", proCode);
				try {
					request.getRequestDispatcher("managerProSearch2.jsp").forward(request, response);
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
	private void displayAllWorkhour(HttpServletRequest request, HttpServletResponse response) {
		String proCode = request.getParameter("proCode");
		if (proCode == null || proCode.equals("")) {
			try {
				request.getRequestDispatcher("managerProSearch1.jsp").forward(request, response);//EmpWithSubmit
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}
	}
}
