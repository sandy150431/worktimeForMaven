package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import check.ChineseChange;
import dao.UpdPwDAO;
import model.Emp;

public class UpdPwController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdPwController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if (ChineseChange.u2i("更改密碼").equals(action)) {// 查詢
			updPassword(request, response);
		} 
	}
	
	
	private void updPassword(HttpServletRequest request,
			HttpServletResponse response) {
		JFrame jf = new JFrame();
		UpdPwDAO updPwDAO = new UpdPwDAO();
		Emp emp = new Emp();
		String empNo = request.getParameter("userID");
		String passwordOld = request.getParameter("passwordOld");
		String passwordNew = request.getParameter("passwordNew");
		String passwordCheck = request.getParameter("passwordCheck");
		
		Emp pwReturn = null;
		try {
			pwReturn = updPwDAO.checkPw(empNo);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//使用者舊密碼確認
		if (pwReturn == null || pwReturn.equals("")){
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "帳號或舊密碼輸入錯誤，請再次確認");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}else if (pwReturn.getPw().equals(passwordOld)) {
			if(passwordNew.equals(passwordCheck)){
				emp.setEmpNo(empNo);
				emp.setPw(passwordNew);
				try {
					updPwDAO.updatePw(emp);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "更新成功");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				try {
					request.getRequestDispatcher("Login.jsp").forward(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ServletException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
				
			}else{
				// 格式錯，留在原畫面
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "兩次新密碼輸入不一致，請再次確認");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				try {
					request.getRequestDispatcher("updatePassword.jsp").forward(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ServletException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			}

		} else {
			// 格式錯，留在原畫面
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "帳號或舊密碼輸入錯誤，請再次確認");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			try {
				request.getRequestDispatcher("updatePassword.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}
		
	}

}
