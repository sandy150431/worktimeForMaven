package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import check.ChineseChange;
import check.InputCheck;
import check.SpaceSv;
import check.TypeChange;
import dao.WorkhoursDAO;
import model.Workhours;

@Controller
public class WorkhourController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String proCode = null;
	String whr1;
	String ot1;

	public WorkhourController() {
		super();
	}

	@RequestMapping("/employeeWorkhourQuery")
	public String employeeWorkhourQuery(){
		return "employ/employeeWorkhourQuery";
	}
	
	@RequestMapping("/employeeMain")
	public String employeeMain(){
		return "employ/employeeMain";
	}
	
	
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		proCode = request.getParameter("proCode");

		if (action == null)
			return;
		if (ChineseChange.u2i("查詢工時").equals(action)) {
			displayWq(request, response);
		} else if ("送出".equals(action)) {
			 updWTStat2(request, response);
		} else if ("儲存".equals(action)) {
			updWTStat1(request, response);
		}
	}
	
	//儲存
	private void updWTStat1(HttpServletRequest req, HttpServletResponse res) {
		JFrame jf = new JFrame();
		WorkhoursDAO whrDAO = new WorkhoursDAO();
		String empNo = (String) req.getSession().getAttribute("userNo");

		SpaceSv ss = new SpaceSv();
		String WT1 = req.getParameter("wt1");
		String CT1 = ss.isnull(req.getParameter("ct1"));
		String OT1 = req.getParameter("ot1");
		String PJ1 = ss.pnnull(req.getParameter("project1"));
		String OTC1 = ss.isnull(req.getParameter("otc1"));
		java.sql.Date SDT1 = TypeChange
				.stringToSqlDate(req.getParameter("dd1"));

		String WT2 = req.getParameter("wt2");
		String CT2 = ss.isnull(req.getParameter("ct2"));
		String OT2 = req.getParameter("ot2");
		String PJ2 = ss.pnnull(req.getParameter("project2"));
		String OTC2 = ss.isnull(req.getParameter("otc2"));
		java.sql.Date SDT2 = TypeChange
				.stringToSqlDate(req.getParameter("dd2"));

		String WT3 = req.getParameter("wt3");
		String CT3 = ss.isnull(req.getParameter("ct3"));
		String OT3 = req.getParameter("ot3");
		String PJ3 = ss.pnnull(req.getParameter("project3"));
		String OTC3 = ss.isnull(req.getParameter("otc3"));
		java.sql.Date SDT3 = TypeChange
				.stringToSqlDate(req.getParameter("dd3"));

		String WT4 = req.getParameter("wt4");
		String CT4 = ss.isnull(req.getParameter("ct4"));
		String OT4 = req.getParameter("ot4");
		String PJ4 = ss.pnnull(req.getParameter("project4"));
		String OTC4 = ss.isnull(req.getParameter("otc4"));
		java.sql.Date SDT4 = TypeChange
				.stringToSqlDate(req.getParameter("dd4"));

		String WT5 = req.getParameter("wt5");
		String CT5 = ss.isnull(req.getParameter("ct5"));
		String OT5 = req.getParameter("ot5");
		String PJ5 = ss.pnnull(req.getParameter("project5"));
		String OTC5 = ss.isnull(req.getParameter("otc5"));
		java.sql.Date SDT5 = TypeChange
				.stringToSqlDate(req.getParameter("dd5"));

		String WT6 = req.getParameter("wt6");
		String CT6 = ss.isnull(req.getParameter("ct6"));
		String OT6 = req.getParameter("ot6");
		String PJ6 = ss.pnnull(req.getParameter("project6"));
		String OTC6 = ss.isnull(req.getParameter("otc6"));
		java.sql.Date SDT6 = TypeChange
				.stringToSqlDate(req.getParameter("dd6"));

		String WT7 = req.getParameter("wt7");
		String CT7 = ss.isnull(req.getParameter("ct7"));
		String OT7 = req.getParameter("ot7");
		String PJ7 = ss.pnnull(req.getParameter("project7"));
		String OTC7 = ss.isnull(req.getParameter("otc7"));
		java.sql.Date SDT7 = TypeChange
				.stringToSqlDate(req.getParameter("dd7"));

		Workhours workhour1 = new Workhours();
		Workhours workhour2 = new Workhours();
		Workhours workhour3 = new Workhours();
		Workhours workhour4 = new Workhours();
		Workhours workhour5 = new Workhours();
		Workhours workhour6 = new Workhours();
		Workhours workhour7 = new Workhours();

		try {
			workhour1.setWhr(ss.getInteger(WT1));
			workhour1.setCont(CT1);
			workhour1.setOt(ss.getInteger(OT1));
			workhour1.setProCode(PJ1);
			workhour1.setOtCont(OTC1);
			workhour1.setEmpNo(empNo);
			workhour1.setDd(SDT1);
			whrDAO.updWorkhourById1(workhour1);

			workhour2.setWhr(ss.getInteger(WT2));
			workhour2.setCont(CT2);
			workhour2.setOt(ss.getInteger(OT2));
			workhour2.setProCode(PJ2);
			workhour2.setOtCont(OTC2);
			workhour2.setEmpNo(empNo);
			workhour2.setDd(SDT2);
			whrDAO.updWorkhourById1(workhour2);

			workhour3.setWhr(ss.getInteger(WT3));
			workhour3.setCont(CT3);
			workhour3.setOt(ss.getInteger(OT3));
			workhour3.setProCode(PJ3);
			workhour3.setOtCont(OTC3);
			workhour3.setEmpNo(empNo);
			workhour3.setDd(SDT3);
			whrDAO.updWorkhourById1(workhour3);

			workhour4.setWhr(ss.getInteger(WT4));
			workhour4.setCont(CT4);
			workhour4.setOt(ss.getInteger(OT4));
			workhour4.setProCode(PJ4);
			workhour4.setOtCont(OTC4);
			workhour4.setEmpNo(empNo);
			workhour4.setDd(SDT4);
			whrDAO.updWorkhourById1(workhour4);

			workhour5.setWhr(ss.getInteger(WT5));
			workhour5.setCont(CT5);
			workhour5.setOt(ss.getInteger(OT5));
			workhour5.setProCode(PJ5);
			workhour5.setOtCont(OTC5);
			workhour5.setEmpNo(empNo);
			workhour5.setDd(SDT5);
			whrDAO.updWorkhourById1(workhour5);

			workhour6.setWhr(ss.getInteger(WT6));
			workhour6.setCont(CT6);
			workhour6.setOt(ss.getInteger(OT6));
			workhour6.setProCode(PJ6);
			workhour6.setOtCont(OTC6);
			workhour6.setEmpNo(empNo);
			workhour6.setDd(SDT6);
			whrDAO.updWorkhourById1(workhour6);

			workhour7.setWhr(ss.getInteger(WT7));
			workhour7.setCont(CT7);
			workhour7.setOt(ss.getInteger(OT7));
			workhour7.setProCode(PJ7);
			workhour7.setOtCont(OTC7);
			workhour7.setEmpNo(empNo);
			workhour7.setDd(SDT7);
			whrDAO.updWorkhourById1(workhour7);

		} catch (Exception e) {
			e.printStackTrace();
		}

		jf.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(jf, "修改成功");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			req.getRequestDispatcher("employeeMain.jsp").forward(req, res);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		
	}


	private void updWTStat2(HttpServletRequest req, HttpServletResponse res) {
		JFrame jf = new JFrame();
		WorkhoursDAO whrDAO = new WorkhoursDAO();
		String empNo = (String) req.getSession().getAttribute("userNo");

		String WT1 = req.getParameter("wt1");
		String CT1 = req.getParameter("ct1");
		String OT1 = req.getParameter("ot1");
		String PJ1 = req.getParameter("project1");
		String OTC1 = req.getParameter("otc1");
		java.sql.Date SDT1 = TypeChange
				.stringToSqlDate(req.getParameter("dd1"));

		String WT2 = req.getParameter("wt2");
		String CT2 = req.getParameter("ct2");
		String OT2 = req.getParameter("ot2");
		String PJ2 = req.getParameter("project2");
		String OTC2 = req.getParameter("otc2");
		java.sql.Date SDT2 = TypeChange
				.stringToSqlDate(req.getParameter("dd2"));

		String WT3 = req.getParameter("wt3");
		String CT3 = req.getParameter("ct3");
		String OT3 = req.getParameter("ot3");
		String PJ3 = req.getParameter("project3");
		String OTC3 = req.getParameter("otc3");
		java.sql.Date SDT3 = TypeChange
				.stringToSqlDate(req.getParameter("dd3"));

		String WT4 = req.getParameter("wt4");
		String CT4 = req.getParameter("ct4");
		String OT4 = req.getParameter("ot4");
		String PJ4 = req.getParameter("project4");
		String OTC4 = req.getParameter("otc4");
		java.sql.Date SDT4 = TypeChange
				.stringToSqlDate(req.getParameter("dd4"));

		String WT5 = req.getParameter("wt5");
		String CT5 = req.getParameter("ct5");
		String OT5 = req.getParameter("ot5");
		String PJ5 = req.getParameter("project5");
		String OTC5 = req.getParameter("otc5");
		java.sql.Date SDT5 = TypeChange
				.stringToSqlDate(req.getParameter("dd5"));

		String WT6 = req.getParameter("wt6");
		String CT6 = req.getParameter("ct6");
		String OT6 = req.getParameter("ot6");
		String PJ6 = req.getParameter("project6");
		String OTC6 = req.getParameter("otc6");
		java.sql.Date SDT6 = TypeChange
				.stringToSqlDate(req.getParameter("dd6"));

		String WT7 = req.getParameter("wt7");
		String CT7 = req.getParameter("ct7");
		String OT7 = req.getParameter("ot7");
		String PJ7 = req.getParameter("project7");
		String OTC7 = req.getParameter("otc7");
		java.sql.Date SDT7 = TypeChange
				.stringToSqlDate(req.getParameter("dd7"));

		Workhours workhour1 = new Workhours();
		Workhours workhour2 = new Workhours();
		Workhours workhour3 = new Workhours();
		Workhours workhour4 = new Workhours();
		Workhours workhour5 = new Workhours();
		Workhours workhour6 = new Workhours();
		Workhours workhour7 = new Workhours();

		try {
			workhour1.setWhr(Integer.parseInt(WT1));
			workhour1.setCont(CT1);
			workhour1.setOt(Integer.parseInt(OT1));
			workhour1.setProCode(PJ1);
			workhour1.setOtCont(OTC1);
			workhour1.setEmpNo(empNo);
			workhour1.setDd(SDT1);
			whrDAO.updWorkhourById2(workhour1);

			workhour2.setWhr(Integer.parseInt(WT2));
			workhour2.setCont(CT2);
			workhour2.setOt(Integer.parseInt(OT2));
			workhour2.setProCode(PJ2);
			workhour2.setOtCont(OTC2);
			workhour2.setEmpNo(empNo);
			workhour2.setDd(SDT2);
			whrDAO.updWorkhourById2(workhour2);

			workhour3.setWhr(Integer.parseInt(WT3));
			workhour3.setCont(CT3);
			workhour3.setOt(Integer.parseInt(OT3));
			workhour3.setProCode(PJ3);
			workhour3.setOtCont(OTC3);
			workhour3.setEmpNo(empNo);
			workhour3.setDd(SDT3);
			whrDAO.updWorkhourById2(workhour3);

			workhour4.setWhr(Integer.parseInt(WT4));
			workhour4.setCont(CT4);
			workhour4.setOt(Integer.parseInt(OT4));
			workhour4.setProCode(PJ4);
			workhour4.setOtCont(OTC4);
			workhour4.setEmpNo(empNo);
			workhour4.setDd(SDT4);
			whrDAO.updWorkhourById2(workhour4);

			workhour5.setWhr(Integer.parseInt(WT5));
			workhour5.setCont(CT5);
			workhour5.setOt(Integer.parseInt(OT5));
			workhour5.setProCode(PJ5);
			workhour5.setOtCont(OTC5);
			workhour5.setEmpNo(empNo);
			workhour5.setDd(SDT5);
			whrDAO.updWorkhourById2(workhour5);

			workhour6.setWhr(Integer.parseInt(WT6));
			workhour6.setCont(CT6);
			workhour6.setOt(Integer.parseInt(OT6));
			workhour6.setProCode(PJ6);
			workhour6.setOtCont(OTC6);
			workhour6.setEmpNo(empNo);
			workhour6.setDd(SDT6);
			whrDAO.updWorkhourById2(workhour6);

			workhour7.setWhr(Integer.parseInt(WT7));
			workhour7.setCont(CT7);
			workhour7.setOt(Integer.parseInt(OT7));
			workhour7.setProCode(PJ7);
			workhour7.setOtCont(OTC7);
			workhour7.setEmpNo(empNo);
			workhour7.setDd(SDT7);
			whrDAO.updWorkhourById2(workhour7);

		} catch (Exception e) {
			e.printStackTrace();
		}

		jf.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(jf, "送出成功");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			req.getRequestDispatcher("employeeMain.jsp").forward(req, res);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
		
	}
	
	
	// 列出單筆員工週工時
	private void displayWq(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JFrame jf = new JFrame();

		String empNo = (String) request.getSession().getAttribute("userNo");
		WorkhoursDAO wqDAO = new WorkhoursDAO();
		Workhours whrinfo1 = new Workhours();
		Workhours whrinfo2 = new Workhours();
		Workhours whrinfo3 = new Workhours();
		Workhours whrinfo4 = new Workhours();
		Workhours whrinfo5 = new Workhours();
		Workhours whrinfo6 = new Workhours();
		Workhours whrinfo7 = new Workhours();

		String ddS = request.getParameter("dd");
		java.sql.Date dd = TypeChange.stringToSqlDate(ddS);
		boolean ddIsNull = InputCheck.isNull(dd);
		// 沒有輸入東西，留在原本畫面
		if (ddIsNull) {
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "請輸入欲搜尋日期，並鍵入正確格式'yyyy-mm-dd'");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			try {
				request.getRequestDispatcher("employeeWorkhourQuery.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				empNo = (String) request.getSession().getAttribute("userNo");
				whrinfo1 = wqDAO.findWorkhoursByNo(empNo, dd, 1);
				whrinfo2 = wqDAO.findWorkhoursByNo(empNo, dd, 2);
				whrinfo3 = wqDAO.findWorkhoursByNo(empNo, dd, 3);
				whrinfo4 = wqDAO.findWorkhoursByNo(empNo, dd, 4);
				whrinfo5 = wqDAO.findWorkhoursByNo(empNo, dd, 5);
				whrinfo6 = wqDAO.findWorkhoursByNo(empNo, dd, 6);
				whrinfo7 = wqDAO.findWorkhoursByNo(empNo, dd, 7);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 沒有抓到東西，留在原本畫面
			if (whrinfo1 == null || whrinfo1.equals("")) {
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "工時尚未送出或審核通過");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				request.getRequestDispatcher("employeeWorkhourQuery.jsp").forward(request, response);
			} else {// 有抓到東西，才去
				HttpSession session = request.getSession();
				session.setAttribute("whr1", whrinfo1);
				session.setAttribute("whr2", whrinfo2);
				session.setAttribute("whr3", whrinfo3);
				session.setAttribute("whr4", whrinfo4);
				session.setAttribute("whr5", whrinfo5);
				session.setAttribute("whr6", whrinfo6);
				session.setAttribute("whr7", whrinfo7);

				request.getRequestDispatcher("employeeWorkhourQueryResult.jsp").forward(request, response);
			}

		}
	}



}
