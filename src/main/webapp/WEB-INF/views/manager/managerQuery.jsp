<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*, java.sql.*, model.*, java.util.*, controller.*"%>


<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
	<div id="wrapper">
		<%@ include file="/resources/template/navbar_manager.file"%>

		<!-- Section: team -->
		<section id="doctor" class="home-section bg-gray paddingbot-300">
			<div class="container marginbot-50">


				<form action="./ManagerController?action=list" class="contactForm lead">
					<div class="section-heading text-center">
						<h2 class="h-bold">查詢工時</h2>
						<div class="divider-short"></div>

						<!-- 查詢列 -->
						<table class="front_input">
							<tr>
								<td class="td1">
									<label for="empNo">請輸入員工編號     </label></td>
								<td class="td2">
									<input type="text" name="empNo" id="empNo" 
									required
									class="form-control input-md"></td>
							</tr>
							<tr>
								<td class="td1">
									<label for="ddYear">及欲查詢日期     </label></td>
								<td class="td2">
									<input type="text" name="dd" id="dd" 
									required placeholder="yyyy-mm-dd"
									class="form-control input-md"></td>
							</tr>
							<tr>
								<td class="td2"  COLSPAN=2>
									<input type="submit" name="action" value="查詢" class="btn btn-skin btn-sm">
								</td>
								
								
							</tr>

						</table>
					</div>
				</form>

								<!-- css是tablecss.css -->
					<%
						ManagerQueryDAO mqDAO = new ManagerQueryDAO();//新增EmpDAO
						List<Workhours> whrs = mqDAO.findAllWorkhours();
						Workhours whr = null;
					%>

				<div class="wrapper">
					<table id="acrylic">
						<thead>
							<tr>
								<th style="white-space:nowrap;">員工編號</th>
								<th style="white-space:nowrap;">員工姓名</th>
								<th style="white-space:nowrap;">周別</th>
								<th style="white-space:nowrap;">日期</th>
								<th style="white-space:nowrap;">專案名稱</th>
								<th style="white-space:nowrap;">工作時數</th>
								<th style="white-space:nowrap;">工作內容</th>
								<th style="white-space:nowrap;">加班時數</th>
								<th style="white-space:nowrap;">加班內容</th>
								<th style="white-space:nowrap;">退件原因</th>
								<th style="white-space:nowrap;">狀態</th>
							</tr>
						</thead>

					<% //讓數字轉成中文
						java.util.Map<String, String> weMap = new java.util.HashMap<String, String>();
						weMap.put("1", "第1週");weMap.put("11", "第11週");weMap.put("21", "第21週");
						weMap.put("2", "第2週");weMap.put("12", "第12週");weMap.put("22", "第22週");
						weMap.put("3", "第3週");weMap.put("13", "第13週");weMap.put("23", "第23週");
						weMap.put("4", "第4週");weMap.put("14", "第14週");weMap.put("24", "第24週");
						weMap.put("5", "第5週");weMap.put("15", "第15週");weMap.put("25", "第25週");
						weMap.put("6", "第6週");weMap.put("16", "第16週");weMap.put("26", "第26週");
						weMap.put("7", "第7週");weMap.put("17", "第17週");weMap.put("27", "第27週");
						weMap.put("8", "第8週");weMap.put("18", "第18週");weMap.put("28", "第28週");
						weMap.put("9", "第9週");weMap.put("19", "第19週");weMap.put("29", "第29週");
						weMap.put("10", "第10週");weMap.put("20", "第20週");weMap.put("30", "第30週");
						weMap.put("31", "第31週");weMap.put("41", "第41週");weMap.put("51", "第51週");
						weMap.put("32", "第32週");weMap.put("42", "第42週");weMap.put("52", "第52週");
						weMap.put("33", "第33週");weMap.put("43", "第43週");
						weMap.put("34", "第34週");weMap.put("44", "第44週");
						weMap.put("35", "第35週");weMap.put("45", "第45週");
						weMap.put("36", "第36週");weMap.put("46", "第46週");
						weMap.put("37", "第37週");weMap.put("47", "第47週");
						weMap.put("38", "第38週");weMap.put("48", "第48週");
						weMap.put("39", "第39週");weMap.put("49", "第49週");
						weMap.put("40", "第40週");weMap.put("50", "第50週");
						pageContext.setAttribute("weMap", weMap);

						java.util.Map<String, String> statMap = new java.util.HashMap<String, String>();
						statMap.put("1", "未送出");
						statMap.put("2", "未審核");
						statMap.put("3", "已審核");
						pageContext.setAttribute("statMap", statMap);

						for(int i = 0; i < whrs.size(); i++){
							whr = whrs.get(i);
							request.setAttribute("whr", whr);
					%>

						<tbody>
							<tr>
								<td style="white-space:nowrap;">${whr.empNo}</td>
								<td style="white-space:nowrap;">${whr.empName}</td>
								<td style="white-space:nowrap;">${weMap[whr.we]}</td>
								<td style="white-space:nowrap;">${whr.dd}</td>
								<td style="white-space:nowrap;">${whr.proName}</td>
								<td style="white-space:nowrap;">${whr.whr}</td>
								<td style="white-space:nowrap;">${whr.cont}</td>
								<td style="white-space:nowrap;">${whr.ot}</td>
								<td style="white-space:nowrap;">${whr.otCont}</td>
								<td style="white-space:nowrap;">${whr.rejectReason}</td>
								<td style="white-space:nowrap;">${statMap[whr.stat]}</td>
							</tr>
					<%
						}
					%>
						
						</tbody>
					</table>
				</div>
				<!-- tablecss結束 -->


			</div>
		</section>
		<!-- /Section: team -->


		<%@ include file="/resources/template/footer.file"%>
	</div>

	<!-- Core JavaScript Files -->
	<!-- 功能表換色 -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript">$(".active3").addClass("active");</script>
	<%@ include file="/resources/template/jsFile.file"%>
</body>

</html>
