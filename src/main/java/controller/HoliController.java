package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import check.ChineseChange;
import check.InputCheck;
import check.TypeChange;
import dao.HoliDAO;
import model.Holi;

@Controller
public class HoliController {

	String holiday = null;

	@RequestMapping("/adminMain")
	public String adminMain() {
		return "admin/adminMain";
	}

	@RequestMapping("/addHolidayMain")
	public String AddHolidayMain() {
		return "admin/adminAddHoliday";
	}

	@RequestMapping("/resignMain")
	public String resignMain() {
		return "admin/adminResign";
	}

	@RequestMapping("/addEmpMain")
	public String addEmpMain() {
		return "admin/adminAddEmp";
	}

	@RequestMapping("/findHoliday")
	public String findHoliday() {
		return "admin/adminFindHoliday";
	}

	@RequestMapping("/findEmp")
	public String findEmp() {
		return "admin/adminFindEmp";
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		holiday = request.getParameter("holiday");

		// 測試
		// ServletOutputStream out = response.getOutputStream();
		// out.println("action=" + action);
		// out.println("holiday=" + holiday);

		if (ChineseChange.u2i("查詢例假日").equals(action)) {
			// searchHoli(request, response);
		} else if (ChineseChange.u2i("修改例假日").equals(action)) {
			// updateHolidayByHoli(request, response);
		} else if (ChineseChange.u2i("新增例假日").equals(action)) {
			// insertHoliday(request, response);
		} else if (ChineseChange.u2i("刪除例假日").equals(action)) {
			//deleteHolidayByHoli(request, response);
		} else if (ChineseChange.u2i("新增年度例假日").equals(action)) {
			addYearHoliday(request, response);
		} else if ("all".equals(action)) {
			displayAllHoli(request, response);
		}
	}

	// 新增例假日
	@RequestMapping("/insertHoliday")
	public String insertHoliday(//
			@RequestParam(value = "holiday", required = true) String holidays, //
			@RequestParam(value = "holiReason", required = true) String holiReason, //
			@RequestParam(value = "hrs", required = true) String hrsString) {
		JFrame jf = new JFrame();
		HoliDAO holiDAO = new HoliDAO();

		// 輸入格式檢查
		boolean inputCheck = InputCheck.insertHoliCheck(holidays, holiReason, hrsString);

		// 存不存在
		boolean holidayNotExist = false;
		try {
			holidayNotExist = InputCheck.isNull(holiDAO.findHoliByDate(TypeChange.stringToSqlDate(holidays)));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (inputCheck && holidayNotExist) {
			Holi holi = new Holi();
			holi.setHoliday(TypeChange.stringToSqlDate(holidays));
			holi.setHoliReason(holiReason);
			holi.setHrs(Integer.valueOf(hrsString));
			try {
				holiDAO.insertHoliday(holi);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// HttpSession session = request.getSession();
			// session.setAttribute("holi", holi);
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "例假日已經更新");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// request.getRequestDispatcher("adminFindHoliday.jsp").forward(request,response);
			return "admin/adminFindHoliday";
		} else {
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "輸入錯誤");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// request.getRequestDispatcher("adminAddOneHoliday.jsp").forward(request,response);
			return "admin/adminAddOneHoliday";
		}

	}

	// 新增年度例假日
	private void addYearHoliday(HttpServletRequest request, HttpServletResponse response) {
		JFrame jf = new JFrame();
		HoliDAO holiDAO = new HoliDAO();
		holiday = request.getParameter("holiday");

		boolean inputCheck1 = InputCheck.isNull(holiday);
		boolean inputCheck2 = InputCheck.isInteger(holiday);

		if (inputCheck1 == false && inputCheck2) {
			try {
				holiDAO.addYearHoliday(holiday);
			} catch (Exception e) {
				e.printStackTrace();
			}

			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "已新增完成");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			try {
				request.getRequestDispatcher("adminFindHoliday.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}

		} else {
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "請輸入正確的日期格式");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			try {
				request.getRequestDispatcher("adminAddHoliday.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}

	}

	// 修改例假日
	@RequestMapping("/updateHolidayByHoli")
	private String updateHolidayByHoli(//
			@RequestParam(value = "holiday", required = true) String holidays, //
			@RequestParam(value = "hrs", required = true) String hrs, //
			@RequestParam(value = "holiReason", required = true) String holiReason, //
			final Model model) throws IOException {
		
		JFrame jf = new JFrame();
		Holi holi = new Holi();
		
		// 如果沒有輸入，hrs不是整數，回原畫面
		if (InputCheck.isInteger(hrs) == false) {
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "請輸入整數");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			searchHoli(holiday, model);
		} else {
			// 如果hrs不在範圍1~8內，回原畫面
			if (InputCheck.isInRange(Integer.valueOf(hrs), 1, 8) == false) {
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "假日時數最多8小時，最少1小時");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				searchHoli(holiday, model);
			} else {
				HoliDAO holiDAO = new HoliDAO();
				// 填進holi
				holi.setHoliday(TypeChange.stringToSqlDate(holidays));
				holi.setHoliReason(holiReason);
				holi.setHrs(Integer.valueOf(hrs));

				try {
					holiDAO.updateHolidayByDate(holi);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "例假日已經更新");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		}
		return "admin/adminFindHoliday";
	}

	// 刪除例假日
	@RequestMapping("/deleteHolidayByHoli")
	private String deleteHolidayByHoli(//
			@RequestParam(value = "holiday", required = true) String holidays, //
			@RequestParam(value = "hrs", required = true) String hrs, //
			@RequestParam(value = "holiReason", required = true) String holiReason) {
		
		JFrame jf = new JFrame();
		Holi holi = new Holi();
		HoliDAO holiDAO = new HoliDAO();

		// 填進holi
		holi.setHoliday(TypeChange.stringToSqlDate(holidays));

		try {
			holiDAO.deleteHoliByDate(holi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		jf.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(jf, "刪除假日");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		return "admin/adminFindHoliday";
	}

	// 查詢全部假日
	private void displayAllHoli(HttpServletRequest request, HttpServletResponse response) {
		if (holiday == null || holiday.equals("")) {
			try {
				request.getRequestDispatcher("adminFindHoliday.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}
	}

	// 查詢例假日
	@RequestMapping("/searchHoli")
	public String searchHoli(//
			@RequestParam(value = "holiday", required = true) String holidays, //
			final Model model) {

		JFrame jf = new JFrame();
		HoliDAO holiDAO = new HoliDAO();
		Holi holi = null;
		// 沒有輸入東西，留在原本畫面
		if (InputCheck.isNull(holidays)) {
			return "admin/adminFindHoliday";
			// request.getRequestDispatcher("adminFindHoliday.jsp").forward(request,response);
		} else {
			try {
				holi = holiDAO.findHoliByDate(TypeChange.stringToSqlDate(holidays));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 判斷日期格式對不對
			if (InputCheck.isValidDate(holidays) == false) {
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "請輸入正確的日期格式");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// 沒有抓到東西，留在原本畫面
				// request.getRequestDispatcher("adminFindHoliday.jsp").forward(request,response);//
				return "admin/adminFindHoliday";

			} else if (InputCheck.isNull(holi)) {
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "新增假日");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				// request.getRequestDispatcher("adminAddOneHoliday.jsp").forward(request,response);
				return "admin/adminAddOneHoliday";
			} else {// 有抓到東西，才去
				// HttpSession session = request.getSession();
				// session.setAttribute("holi", holi);
				// request.getRequestDispatcher("adminFindHolidayResult.jsp").forward(request,response);
				model.addAttribute("holi", holi);
				return "admin/adminFindHolidayResult";
			}

		}
	}

}
