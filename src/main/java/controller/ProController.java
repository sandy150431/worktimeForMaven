package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import check.ChineseChange;
import dao.ProDAO;
import model.Pro;

//Servlet implementation class DepartmentController

public class ProController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String proCode = null;

	// @see HttpServlet#HttpServlet()

	public ProController() {
		super();
	}

	// @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	// response)
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	// @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	// response)
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		proCode = request.getParameter("proCode");
		if (action.equals(ChineseChange.u2i("新增專案"))) {
			insPro(request, response);
		}
	}

	private void insPro(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String proName = request.getParameter("proName");
		JFrame jf = new JFrame();
		
		if (proName == null || proName.equals("")) {
			
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "請輸入專案名稱");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			try {
				request.getRequestDispatcher("managerAddPro.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		} else {
			try {
				ProDAO ProDAO = new ProDAO();
				Pro pro = new Pro();
				pro.setProName(proName);
				proCode = ProDAO.insProById(pro);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "新增成功");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			try {
				request.getRequestDispatcher("managerAddPro.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}
	}
}
