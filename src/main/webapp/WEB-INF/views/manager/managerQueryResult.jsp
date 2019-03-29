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


				<form action="./ManagerController?action=list"
					class="contactForm lead">
					<div class="section-heading text-center">
						<h2 class="h-bold">查詢工時</h2>
						<div class="divider-short"></div>

						<!-- 查詢列 -->
						<table class="front_input">
							<tr>
								<td class="td1">
									<!-- button純粹轉址 --> <input type="button"
									onclick="javascript:location.href='managerQuery.jsp'"
									value="重新查詢" class="btn btn-skin btn-sm"
									style="font-family: Microsoft JhengHei;">
								</td>
							</tr>

						</table>
					</div>
				</form>

				<!-- css是tablecss.css -->
				<div class="wrapper">
					<%
						//讓數字轉成中文
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
									
									java.util.Map<String, String> rsMap = new java.util.HashMap<String, String>();
									rsMap.put(null, "無");
									pageContext.setAttribute("rsMap", rsMap);

									java.util.Map<String, String> statMap = new java.util.HashMap<String, String>();
									statMap.put("2", "未審核");
									statMap.put("3", "已審核");
									pageContext.setAttribute("statMap", statMap);
					%>

					<table class="table table-bordered table-hover">
						<tbody>
							<tr>
								<th style="width: 100px;">員工編號</th>
								<td>${whr1.empNo}</td>
							</tr>
							<tr>
								<th>員工姓名</th>
								<td>${whr1.empName}</td>
							</tr>
							<tr>
								<th>退件原因</th>
								<td>${rsMap[whr1.rejectReason]}</td>
							</tr>
							<tr>
								<th>是否審核</th>
								<td>${statMap[whr1.stat]}</td>
							</tr>
						</tbody>
					</table>

					<table class="table table-bordered table-hover col-md-12"
						style="text-align: center;">
						<thead>
							<tr>
								<th class="col-md-2">${weMap[whr1.we]}</th>
								<th style="text-align: center;">一</th>
								<th style="text-align: center;">二</th>
								<th style="text-align: center;">三</th>
								<th style="text-align: center;">四</th>
								<th style="text-align: center;">五</th>
								<th style="text-align: center;">六</th>
								<th style="text-align: center;">日</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td>日期</td>
								<td>${whr1.dd}</td>
								<td>${whr2.dd}</td>
								<td>${whr3.dd}</td>
								<td>${whr4.dd}</td>
								<td>${whr5.dd}</td>
								<td>${whr6.dd}</td>
								<td>${whr7.dd}</td>
							</tr>

							<tr>
								<td>專案名稱</td>
								<td>${whr1.proName}</td>
								<td>${whr2.proName}</td>
								<td>${whr3.proName}</td>
								<td>${whr4.proName}</td>
								<td>${whr5.proName}</td>
								<td>${whr6.proName}</td>
								<td>${whr7.proName}</td>
							</tr>
							<tr>
								<td>普通工時</td>
								<td>${whr1.whr}</td>
								<td>${whr2.whr}</td>
								<td>${whr3.whr}</td>
								<td>${whr4.whr}</td>
								<td>${whr5.whr}</td>
								<td>${whr6.whr}</td>
								<td>${whr7.whr}</td>


							</tr>
							<tr>
								<td style="height: 100px;">工作內容</td>
								<td>${whr1.cont}</td>
								<td>${whr2.cont}</td>
								<td>${whr3.cont}</td>
								<td>${whr4.cont}</td>
								<td>${whr5.cont}</td>
								<td>${whr6.cont}</td>
								<td>${whr7.cont}</td>
							</tr>
							<tr>
								<td>加班工時</td>
								<td>${whr1.ot}</td>
								<td>${whr2.ot}</td>
								<td>${whr3.ot}</td>
								<td>${whr4.ot}</td>
								<td>${whr5.ot}</td>
								<td>${whr6.ot}</td>
								<td>${whr7.ot}</td>
							</tr>
							<tr>
								<td style="height: 100px;">工作內容</td>
								<td>${whr1.otCont}</td>
								<td>${whr2.otCont}</td>
								<td>${whr3.otCont}</td>
								<td>${whr4.otCont}</td>
								<td>${whr5.otCont}</td>
								<td>${whr6.otCont}</td>
								<td>${whr7.otCont}</td>
							</tr>
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
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript">
		$(".active3").addClass("active");
	</script>
	<%@ include file="/resources/template/jsFile.file"%>
</body>

</html>
