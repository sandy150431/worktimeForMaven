package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Workhours;

import check.TypeChange;
import dao.NWTDAO;

@Controller
public class NWTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NWTServlet() {
		super();
	}
	
	@RequestMapping("/fixWt")
	public String fixWt(){
		return "employ/fixWt";
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

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
