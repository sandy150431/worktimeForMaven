package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import check.ChineseChange;
import check.TypeChange;

import model.Holi;
import model.Workhours;
import dao.HoliDAO;
import dao.HurryWtDAO;
/**
 * Servlet implementation class HurryWtServlet
 */
public class HurryWtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     HurryWtDAO hurrywtdao = new HurryWtDAO();
     Workhours workhour = new Workhours();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HurryWtServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

    protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		JFrame jf = new JFrame();
		jf.setAlwaysOnTop(true);
		
		JOptionPane.showMessageDialog(jf, "催繳已送出");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			hurrywtdao.updateHurry(workhour);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("./managerHurryWt.jsp").forward(request, response);
	}

}
