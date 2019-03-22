package dao;

import java.io.FileOutputStream;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import model.Workhours;


public class ExcelDAO {
	static String sql = "select * from Workhours ";
	static Connection conn = null;
	static Statement stat = null;
	static PreparedStatement pstat = null;
	static ResultSet rs = null;
	static Workhours whr = null;
	static List<Workhours> workhours = new ArrayList<Workhours>();
	
	public static  List<Workhours> excelStat() { 
		ExcelDAO excelDAO = new ExcelDAO();
		List<Workhours> workhours=null;
		try {
			workhours = excelDAO.findAllNotReviewWorkhours();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JFrame jf = new JFrame();
		
        // 第一步，创建一个webbook，对应一个Excel文件  
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
        HSSFSheet sheet = wb.createSheet("已審核工時");  
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
        HSSFRow row = sheet.createRow((int) 0);  
        // 第四步，创建单元格，并设置值表头 设置表头居中  
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式  
  
        HSSFCell cell = row.createCell((short) 0);  
        cell.setCellValue("員編");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 1);  
        cell.setCellValue("員工姓名");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 2);  
        cell.setCellValue("周別");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 3);  
        cell.setCellValue("日期");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 4);   
        cell.setCellValue("專案名稱");  
        cell.setCellStyle(style);  
        cell = row.createCell((short) 5);  
        cell.setCellValue("正常時數");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 6);  
        cell.setCellValue("工作內容");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 7);  
        cell.setCellValue("加班時數");  
        cell.setCellStyle(style); 
        cell = row.createCell((short) 8);  
        cell.setCellValue("工作內容");  
        cell.setCellStyle(style);
        cell = row.createCell((short) 9); 
        cell.setCellValue("狀態");  
        cell.setCellStyle(style);  
        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
  
        for (int i = 0; i < workhours.size(); i++)  
        {  
            row = sheet.createRow((int) i + 1);  
            Workhours wh = (Workhours) workhours.get(i);  
            // 第四步，创建单元格，并设置值  
            row.createCell((short) 0).setCellValue(wh.getEmpNo());  
            row.createCell((short) 1).setCellValue(wh.getEmpName());  
            row.createCell((short) 2).setCellValue(wh.getWe());  
            cell = row.createCell((short) 3);  
            cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(wh
                    .getDd()));  
            row.createCell((short) 4).setCellValue(wh.getProName()); 
            row.createCell((short) 5).setCellValue(wh.getWhr()); 
            row.createCell((short) 6).setCellValue(wh.getCont()); 
            row.createCell((short) 7).setCellValue(wh.getOt()); 
            row.createCell((short) 8).setCellValue(wh.getOtCont()); 
            row.createCell((short) 9).setCellValue(wh.getStat()); 
        }  
        // 第六步，将文件存到指定位置  
        try  
        {  
            FileOutputStream fout = new FileOutputStream("C:\\Users\\Demo\\Desktop\\workhour.xls");  
            wb.write(fout);  
            fout.close();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }
		return workhours;  
    }  

	public List<Workhours> findAllNotReviewWorkhours() throws Exception {
		conn = ConnectionHelper.getConnection();
		sql =  " select emp_no, emp_name,we,Pro_Name,DD,whr,cont,ot,otcont,stat "
			 + " from workhours "
			 + " where stat='3' "
			 + " order by emp_no ";
		stat = conn.createStatement();
		pstat = conn.prepareStatement(sql);
		rs = stat.executeQuery(sql);

		while (rs.next()) {
			whr = new Workhours();// 用emp產生每一列的資料
			whr.setEmpNo(rs.getString("emp_No"));
			whr.setEmpName(rs.getString("emp_Name"));
			whr.setProName(rs.getString("pro_Name"));
			whr.setWe(rs.getString("we"));
			whr.setDd(rs.getDate("dd"));
			whr.setStat(rs.getString("stat"));
			whr.setWhr(rs.getInt("whr"));
			whr.setCont(rs.getString("cont"));
			whr.setOt(rs.getInt("ot"));
			whr.setOtCont(rs.getString("otCont"));
			whr.setStat(rs.getString("stat"));
			workhours.add(whr);
		}
		conn.close();
		return workhours;
	}

	public void close() throws Exception {
		rs.close();
		pstat.close();
		conn.close();
		rs = null;
		pstat = null;
		conn = null;
	}

}

	