package controller;

import java.io.IOException;
import java.util.Map;
//import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dao.*;
import model.*;
import check.*;

public class HoliController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String holiday = null;
	public HoliController() {
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
		holiday = request.getParameter("holiday");

		// 測試
		// ServletOutputStream out = response.getOutputStream();
		// out.println("action=" + action);
		// out.println("holiday=" + holiday);

		if (ChineseChange.u2i("查詢例假日").equals(action)) {
			searchHoli(request, response);
		} else if (ChineseChange.u2i("修改例假日").equals(action)) {
			updateHolidayByHoli(request, response);
		} else if (ChineseChange.u2i("新增例假日").equals(action)) {
			insertHoliday(request, response);
		} else if (ChineseChange.u2i("刪除例假日").equals(action)) {
			deleteHolidayByHoli(request, response);
		} else if(ChineseChange.u2i("新增年度例假日").equals(action)) {
			addYearHoliday(request, response);
		} else if("all".equals(action)) {
			displayAllHoli(request, response);
		}
	}
	// 新增例假日
	private void insertHoliday(HttpServletRequest request,HttpServletResponse response) {
		JFrame jf = new JFrame();
		HoliDAO holiDAO = new HoliDAO();
		holiday = request.getParameter("holiday");
		String holiReason = ChineseChange.u2i2(request.getParameter("holiReason"));
		String hrsString = request.getParameter("hrs");

		// 輸入格式檢查
		boolean inputCheck = InputCheck.insertHoliCheck(holiday, holiReason,
		hrsString);
		
		// 存不存在
		boolean holidayNotExist = false;
		try {
			holidayNotExist = InputCheck.isNull(holiDAO.findHoliByDate(TypeChange.stringToSqlDate(holiday)));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (inputCheck && holidayNotExist){
			Holi holi = new Holi();
			holi.setHoliday(TypeChange.stringToSqlDate(holiday));
			holi.setHoliReason(holiReason);
			holi.setHrs(Integer.valueOf(hrsString));
			try {
				holiDAO.insertHoliday(holi);
			} catch (Exception e) {
				e.printStackTrace();
			}
			HttpSession session = request.getSession();
			session.setAttribute("holi", holi);
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "例假日已經更新");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			try {
				request.getRequestDispatcher("adminFindHoliday.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		}else{
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "輸入錯誤");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			try {
				request.getRequestDispatcher("adminAddOneHoliday.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}			
		}

	}

	//新增年度例假日
	private void addYearHoliday(HttpServletRequest request,
			HttpServletResponse response) {
		JFrame jf = new JFrame();
		HoliDAO holiDAO = new HoliDAO();
		holiday = request.getParameter("holiday");
		
		boolean inputCheck1 = InputCheck.isNull(holiday);
		boolean inputCheck2 = InputCheck.isInteger(holiday);
		
		if (inputCheck1==false && inputCheck2) {			
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
	private void updateHolidayByHoli(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		JFrame jf = new JFrame();
		HttpSession session = request.getSession();
		Holi holi = (Holi) session.getAttribute("holi");
		holiday = holi.getHoliday() + " ";
		String holiReason = null;
		int hrs = 0;
		
		//如果沒有輸入，hrs不是整數，回原畫面
		if (InputCheck.isInteger(request.getParameter("hrs")) == false) {
			jf.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(jf, "請輸入整數");
			jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			searchHoli(request, response);
		} else {
			holiReason = ChineseChange.u2i2(request.getParameter("holiReason"));
			hrs = Integer.valueOf(request.getParameter("hrs"));
			//如果hrs不在範圍1~8內，回原畫面
			if (InputCheck.isInRange(hrs, 1, 8) == false) {
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "假日時數最多8小時，最少1小時");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				searchHoli(request, response);
			} else {
				HoliDAO holiDAO = new HoliDAO();
				// 填進holi
				holi.setHoliday(TypeChange.stringToSqlDate(holiday));
				holi.setHoliReason(holiReason);
				holi.setHrs(hrs);

				try {
					holiDAO.updateHolidayByDate(holi);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "例假日已經更新");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			}

	// 刪除例假日
	private void deleteHolidayByHoli(HttpServletRequest request, HttpServletResponse response) {
		JFrame jf = new JFrame();
		HttpSession session = request.getSession();
		Holi holi = (Holi) session.getAttribute("holi");
		HoliDAO holiDAO = new HoliDAO();
		
		try {
			holiDAO.deleteHoliByDate(holi);
		} catch (Exception e) {
			e.printStackTrace();
		}
		jf.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(jf, "刪除假日");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			request.getRequestDispatcher("adminFindHoliday.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException se) {
			// TODO Auto-generated catch block
			se.printStackTrace();
		}
	}

	// 查詢全部假日
	private void displayAllHoli(HttpServletRequest request,
			HttpServletResponse response) {
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
	private void searchHoli(HttpServletRequest request,
			HttpServletResponse response) {
		JFrame jf = new JFrame();
		HoliDAO holiDAO = new HoliDAO();
		Holi holi = null;
		// 沒有輸入東西，留在原本畫面
		if (InputCheck.isNull(holiday)) {
			try {
				request.getRequestDispatcher("adminFindHoliday.jsp").forward(request, response);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException se) {
				// TODO Auto-generated catch block
				se.printStackTrace();
			}
		} else {
			try {
				holi = holiDAO.findHoliByDate(TypeChange
						.stringToSqlDate(holiday));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 判斷日期格式對不對
			if (InputCheck.isValidDate(holiday)==false) {
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "請輸入正確的日期格式");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				try {
					request.getRequestDispatcher("adminFindHoliday.jsp").forward(request, response);//沒有抓到東西，留在原本畫面
				} catch (IOException e) {
					e.printStackTrace();
				}catch (ServletException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			} else if (InputCheck.isNull(holi)) {
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "新增假日");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				try {
					request.getRequestDispatcher("adminAddOneHoliday.jsp").forward(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ServletException se) {
					// TODO Auto-generated catch block
					se.printStackTrace();
				}
			} else {// 有抓到東西，才去
				HttpSession session = request.getSession();
				session.setAttribute("holi", holi);
				try {
					request.getRequestDispatcher("adminFindHolidayResult.jsp").forward(request, response);
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
