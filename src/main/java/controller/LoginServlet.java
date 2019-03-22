package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Emp;
import model.Workhours;
import dao.LoginDAO;
import dao.NWorktimeDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html ; charset=UTF-8");

		String userNo = req.getParameter("userNo");
		String passWord = req.getParameter("password");
		Emp emp = new Emp();
		emp.setEmpNo(userNo);
		emp.setPw(passWord);
		LoginDAO logindao = new LoginDAO();
		try {
			String userValidate = logindao.authenticateUser(emp);
			if (userValidate.equals("Admin")) {

				req.getSession().setAttribute("userNo", userNo);
				req.getRequestDispatcher("/WEB-INF/views/adminMain.jsp").forward(req, res);
			} else if (userValidate.equals("Manager")) {
				
				req.getSession().setAttribute("userNo", userNo);
				req.getRequestDispatcher("/WEB-INF/views/managerMain.jsp").forward(req, res);

			} else if (userValidate.equals("Employee")) {
				String yn = "";
				Workhours workhour = new Workhours();
				workhour.setEmpNo(userNo);
				NWorktimeDAO nworktimedao = new NWorktimeDAO();
				yn = nworktimedao.findme(workhour);
				if (yn.equals("oops")) {
					req.getSession().setAttribute("hurry", "盡快繳交當周工時 ! !");
				}
				req.getSession().setAttribute("userNo", userNo);
				req.getRequestDispatcher("/WEB-INF/views/employeeMain.jsp").forward(req, res);

			} else {
				JFrame jf = new JFrame();
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "帳號或密碼錯誤");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				req.getSession().setAttribute("userNo", "NotUser");
				req.getRequestDispatcher("/WEB-INF/views/Login.jsp").forward(req, res);
			}
			
		} catch (IOException ie) {
			ie.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}