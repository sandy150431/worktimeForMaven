package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Emp;
import mail.GmailSendMailviaTLS;

import check.ChineseChange;
import check.InputCheck;
import check.TypeChange;
import dao.*;

public class ForgotPwController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ForgotPwController() {
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
		
		if (ChineseChange.u2i("忘記密碼").equals(action)) {// 查詢
			try {
				updPassword(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
	}
	private void updPassword(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JFrame jf = new JFrame();
		ForgotDAO forgotDAO = new ForgotDAO();
		
		String empNo = request.getParameter("userID");
		String twid = request.getParameter("twid");
		String email = request.getParameter("email");
		Emp emp = new Emp();
		emp.setEmpNo(empNo);
		String ti , ema ;
		ti = forgotDAO.checkId(emp);
		ema = forgotDAO.checkMail(emp);
		
		//使用者身分確認
		if (ti == null || ema ==null){
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "帳號輸入錯誤，請再次確認");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//導回原畫面
			try {
				request.getRequestDispatcher("Forgot.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
			
		}else{
			//身分驗證錯誤
			if (!ti.equals(twid) || !ema.equals(email)) {
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "非該帳號原申請之身分證字號或Email，請再次確認");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
				
				//導回原畫面
				try {
					request.getRequestDispatcher("Forgot.jsp").forward(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ServletException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
				
			}else{
				//寄信
				GmailSendMailviaTLS sem = new GmailSendMailviaTLS();
				sem.main(null);
				emp.setPw(twid);
				forgotDAO.updatePw(emp);
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "新密碼已寄出");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
					
				try {
					request.getRequestDispatcher("Login.jsp").forward(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ServletException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			}
		} 
	}
}
