package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Workhours;

import check.TypeChange;
import dao.NWTDAO;

/**
 * Servlet implementation class NWTServlet
 */
public class NWTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NWTServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
