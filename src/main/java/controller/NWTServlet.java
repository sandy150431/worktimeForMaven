package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import check.TypeChange;
import dao.NWTDAO;
import day.Today;
import model.Workhours;

@Controller
public class NWTServlet{

	@Autowired
	private LoginServlet login;
	
	
	@RequestMapping("/fixWt")
	public String fixWt() {
		return "employee/fixWt";
	}

	@RequestMapping("/newWorktime")
	public String newWorktime(@RequestParam(value = "dd", required = true) String dd,Model model) {

		String returnPath = "";
		JFrame jf = new JFrame();

		java.sql.Date date = TypeChange.stringToSqlDate(dd);
		NWTDAO nwtdao = new NWTDAO();
		Workhours workhour = new Workhours();
		workhour.setEmpNo(login.getCurrentUser());
		workhour.setDd(date);

		try {
			String st = nwtdao.find(workhour);
			
			if (st.equals("done")) {
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "當週工時已新增");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				returnPath= "employee/employee";

			} else if (st.equals("OK")) {
				
				java.util.Date dayy = new java.util.Date();
				SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
				int week = Today.getWeekOfYear(dayy);
				
				String day1 = sdf.format(Today.getMonDayOfWeek(dayy));
				String day2 = sdf.format(Today.getTuesDayOfWeek(dayy));
				String day3 = sdf.format(Today.getWednesDayOfWeek(dayy));
				String day4 = sdf.format(Today.getThursDayOfWeek(dayy));
				String day5 = sdf.format(Today.getFriDayOfWeek(dayy));
				String day6 = sdf.format(Today.getSaturDayOfWeek(dayy));
				String day7 = sdf.format(Today.getSunDayOfWeek(dayy));
				
				model.addAttribute("week", week);
				model.addAttribute("day1", day1);
				model.addAttribute("day2", day2);
				model.addAttribute("day3", day3);
				model.addAttribute("day4", day4);
				model.addAttribute("day5", day5);
				model.addAttribute("day6", day6); 
				model.addAttribute("day7", day7); 
				
				returnPath =  "employee/newWorktime";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnPath;
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		JFrame jf = new JFrame();

		String userNo = (String) req.getSession().getAttribute("userNo");
		String ddS = req.getParameter("dd");
		java.sql.Date dd = TypeChange.stringToSqlDate(ddS);
		NWTDAO nwtdao = new NWTDAO();
		Workhours workhour = new Workhours();
		workhour.setEmpNo(userNo);
		workhour.setDd(dd);

		try {
			String st = nwtdao.find(workhour);
			if (st.equals("done")) {
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "當週工時已新增");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				req.getRequestDispatcher("Employee.jsp").forward(req, res);
			} else if (st.equals("OK")) {
				req.getRequestDispatcher("newWorktime.jsp").forward(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
