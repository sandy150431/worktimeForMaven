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

import check.TypeChange;
import dao.SWTDAO;
import model.Workhours;

@Controller
public class SWTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SWTServlet() {
		super();
	}
	
	@RequestMapping("/employee")
	public String employee(){
		return "employ/employee";
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html ; charset=UTF-8");

		JFrame jf = new JFrame();

		String userNo = (String) req.getSession().getAttribute("userNo");
		String ddS = req.getParameter("dd");
		java.sql.Date dd = TypeChange.stringToSqlDate(ddS);
		SWTDAO nwtdao = new SWTDAO();
		Workhours workhour = new Workhours();
		workhour.setEmpNo(userNo);
		workhour.setDd(dd);

		try {
			String st = nwtdao.find(workhour);
			if (st.equals("OK")) {
				jf.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(jf, "沒有工時可以修改，請至新增工時");
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				req.getRequestDispatcher("Employee.jsp").forward(req, res);
			} else if (st.equals("done")) {
				req.getRequestDispatcher("employeeWTUpdate.jsp").forward(req, res);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
