package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import check.ChineseChange;
import check.SpaceSv;
import model.*;
import dao.*;
import day.Today;

public class NWorktimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Emp emp = new Emp();
	NWorktimeDAO nworktimedao = new NWorktimeDAO();
	Today today = new Today();
	Date dayy = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	JFrame jf = new JFrame();

	public NWorktimeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");
		if (action == null)
			return;
		else if (action.equals("新增")) {
			req.setAttribute("ST", 2);// 1 狀態:已送出
			insertWT(req, res);

			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "已送出");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			req.getRequestDispatcher("Employee.jsp").forward(req, res);
		} else if (action.equals("儲存")) {
			req.setAttribute("ST", 1);// 狀態未送出
			insertWT(req, res);

			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "已儲存");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			req.getRequestDispatcher("Employee.jsp").forward(req, res);
		}
	}

	public void insertWT(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String empNo = (String) req.getSession().getAttribute("userNo");
		int WE = today.getWeekOfYear(dayy);
		String Week = String.valueOf(WE);
		int i = (Integer) req.getAttribute("ST");
		SpaceSv ss = new SpaceSv();
		String WT1 = req.getParameter("wt1");
		String CT1 = ss.isnull(req.getParameter("ct1"));
		String OT1 = req.getParameter("ot1");
		String PJ1 = ss.pnnull(req.getParameter("project1"));
		String OTC1 = ss.isnull(req.getParameter("otc1"));
		Date SDT1 = today.getMonDayOfWeek(dayy);
		
		String WT2 = req.getParameter("wt2");
		String CT2 = ss.isnull(req.getParameter("ct2"));
		String OT2 = req.getParameter("ot2");
		String PJ2 = ss.pnnull(req.getParameter("project2"));
		String OTC2 = ss.isnull(req.getParameter("otc2"));
		Date SDT2 = today.getTuesDayOfWeek(dayy);
		
		String WT3 = req.getParameter("wt3");
		String CT3 = ss.isnull(req.getParameter("ct3"));
		String OT3 = req.getParameter("ot3");
		String PJ3 = ss.pnnull(req.getParameter("project3"));
		String OTC3 = ss.isnull(req.getParameter("otc3"));
		Date SDT3 = today.getWednesDayOfWeek(dayy);
		
		String WT4 = req.getParameter("wt4");
		String CT4 = ss.isnull(req.getParameter("ct4"));
		String OT4 = req.getParameter("ot4");
		String PJ4 = ss.pnnull(req.getParameter("project4"));
		String OTC4 = ss.isnull(req.getParameter("otc4"));
		Date SDT4 = today.getThursDayOfWeek(dayy);
		
		String WT5 = req.getParameter("wt5");
		String CT5 = ss.isnull(req.getParameter("ct5"));
		String OT5 = req.getParameter("ot5");
		String PJ5 = ss.pnnull(req.getParameter("project5"));
		String OTC5 = ss.isnull(req.getParameter("otc5"));
		Date SDT5 = today.getFriDayOfWeek(dayy);
		
		String WT6 = req.getParameter("wt6");
		String CT6 = ss.isnull(req.getParameter("ct6"));
		String OT6 = req.getParameter("ot6");
		String PJ6 = ss.pnnull(req.getParameter("project6"));
		String OTC6 = ss.isnull(req.getParameter("otc6"));
		Date SDT6 = today.getSaturDayOfWeek(dayy);
		
		String WT7 = req.getParameter("wt7");
		String CT7 = ss.isnull(req.getParameter("ct7"));
		String OT7 = req.getParameter("ot7");
		String PJ7 = ss.pnnull(req.getParameter("project7"));
		String OTC7 = ss.isnull(req.getParameter("otc7"));
		Date SDT7 = today.getSunDayOfWeek(dayy);
		
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
			workhour1.setWe(Week);
			workhour1.setStat(Integer.toString(i));
			java.util.Date SUDT1 = sdf.parse(sdf.format(SDT1));
			java.sql.Date SQDT1 = new java.sql.Date(SUDT1.getTime()); // util ->
																		// sqls
			workhour1.setDd(SQDT1);
			nworktimedao.findname(workhour1);
			nworktimedao.findproname(workhour1);
			nworktimedao.insertNW(workhour1);

			workhour2.setWhr(ss.getInteger(WT2));
			workhour2.setCont(CT2);
			workhour2.setOt(ss.getInteger(OT2));
			workhour2.setProCode(PJ2);
			workhour2.setOtCont(OTC2);
			workhour2.setEmpNo(empNo);
			workhour2.setWe(Week);
			workhour2.setStat(Integer.toString(i));

			java.util.Date SUDT2 = sdf.parse(sdf.format(SDT2));
			java.sql.Date SQDT2 = new java.sql.Date(SUDT2.getTime()); // util ->
																		// sqls
			workhour2.setDd(SQDT2);
			nworktimedao.findname(workhour2);
			nworktimedao.findproname(workhour2);
			nworktimedao.insertNW(workhour2);

			workhour3.setWhr(ss.getInteger(WT3));
			workhour3.setCont(CT3);
			workhour3.setOt(ss.getInteger(OT3));
			workhour3.setProCode(PJ3);
			workhour3.setOtCont(OTC3);
			workhour3.setEmpNo(empNo);
			workhour3.setWe(Week);
			workhour3.setStat(Integer.toString(i));

			java.util.Date SUDT3 = sdf.parse(sdf.format(SDT3));
			java.sql.Date SQDT3 = new java.sql.Date(SUDT3.getTime()); // util ->
																		// sqls
			workhour3.setDd(SQDT3);
			nworktimedao.findname(workhour3);
			nworktimedao.findproname(workhour3);
			nworktimedao.insertNW(workhour3);

			workhour4.setWhr(ss.getInteger(WT4));
			workhour4.setCont(CT4);
			workhour4.setOt(ss.getInteger(OT4));
			workhour4.setProCode(PJ4);
			workhour4.setOtCont(OTC4);
			workhour4.setEmpNo(empNo);
			workhour4.setWe(Week);
			workhour4.setStat(Integer.toString(i));

			java.util.Date SUDT4 = sdf.parse(sdf.format(SDT4));
			java.sql.Date SQDT4 = new java.sql.Date(SUDT4.getTime()); // util ->
																		// sqls
			workhour4.setDd(SQDT4);
			nworktimedao.findname(workhour4);
			nworktimedao.findproname(workhour4);
			nworktimedao.insertNW(workhour4);

			workhour5.setWhr(ss.getInteger(WT5));
			workhour5.setCont(CT5);
			workhour5.setOt(ss.getInteger(OT5));
			workhour5.setProCode(PJ5);
			workhour5.setOtCont(OTC5);
			workhour5.setEmpNo(empNo);
			workhour5.setWe(Week);
			workhour5.setStat(Integer.toString(i));
			java.util.Date SUDT5 = sdf.parse(sdf.format(SDT5));
			java.sql.Date SQDT5 = new java.sql.Date(SUDT5.getTime()); // util ->
																		// sqls
			workhour5.setDd(SQDT5);
			nworktimedao.findname(workhour5);
			nworktimedao.findproname(workhour5);
			nworktimedao.insertNW(workhour5);

			workhour6.setWhr(ss.getInteger(WT6));
			workhour6.setCont(CT6);
			workhour6.setOt(ss.getInteger(OT6));
			workhour6.setProCode(PJ6);
			workhour6.setOtCont(OTC6);
			workhour6.setEmpNo(empNo);
			workhour6.setWe(Week);
			workhour6.setStat(Integer.toString(i));
			java.util.Date SUDT6 = sdf.parse(sdf.format(SDT6));
			java.sql.Date SQDT6 = new java.sql.Date(SUDT6.getTime()); // util ->
																		// sqls
			workhour6.setDd(SQDT6);
			nworktimedao.findname(workhour6);
			nworktimedao.findproname(workhour6);
			nworktimedao.insertNW(workhour6);

			workhour7.setWhr(ss.getInteger(WT7));
			workhour7.setCont(CT7);
			workhour7.setOt(ss.getInteger(OT7));
			workhour7.setProCode(PJ7);
			workhour7.setOtCont(OTC7);
			workhour7.setEmpNo(empNo);
			workhour7.setWe(Week);
			workhour7.setStat(Integer.toString(i));
			java.util.Date SUDT7 = sdf.parse(sdf.format(SDT7));
			java.sql.Date SQDT7 = new java.sql.Date(SUDT7.getTime()); // util ->
																		// sqls
			workhour7.setDd(SQDT7);
			nworktimedao.findname(workhour7);
			nworktimedao.findproname(workhour7);
			nworktimedao.insertNW(workhour7);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
