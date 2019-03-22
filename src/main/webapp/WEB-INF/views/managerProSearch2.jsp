<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*, model.*, java.sql.*, java.util.*,controller.*"%>

<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">

<form action="VWorkhourAController">

	<div id="wrapper">
		<%@ include file="/resources/template/navbar_manager.file"%>


		<!-- Section: team -->
		<section id="doctor" class="home-section bg-gray paddingbot-60">
			<div class="container marginbot-50">

					<div class="section-heading text-center">
						<h2 class="h-bold">專案查詢結果</h2>
						<div class="divider-short"></div>
					<%
						String proCode = (String)session.getAttribute("proCode");
						VWorkhourADAO vWorkhourADAO = new VWorkhourADAO();
						List<VWorkhourA> vWorkhourAe = vWorkhourADAO.findAllWorkhoure(proCode);
						VWorkhourA vWorkhourA = null;
					%>



					<!-- css是tablecss.css -->
					<div class="wrapper">
						<table id="acrylic">
							<thead>
								<tr>
									<th>專案代碼</th>
									<th>專案名稱</th>
									<th>日期</th>
									<th>每月總工作時數</th>
								</tr>
							</thead>
							<%
								for(int i = 0; i<vWorkhourAe.size(); i++){
																									vWorkhourA = vWorkhourAe.get(i);
																									request.setAttribute("vWorkhourA", vWorkhourA);
							%>

							<tbody>
								<tr>
									<td>${vWorkhourA.proCode}</td>
									<td>${vWorkhourA.proName}</td>
									<td>${vWorkhourA.yyyymm}</td>
									<td>${vWorkhourA.ccc}</td>
								</tr>

								<%
									}
								%>

							</tbody>
						</table>
					</div>
					<!-- tablecss結束 -->

					<div class="section-heading text-center">
						<div style="margin-top: 30px;" class="col-lg-12">
							<!-- button純粹轉址 -->
							<input type="button"
								onclick="javascript:location.href='managerProSearch1.jsp'"
								value="重新查詢" class="btn btn-skin btn-lg" style="font-family: Microsoft JhengHei;">
						</div>
					</div>
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
		$(".active5").addClass("active");
	</script>

	<!--<script src="js/inputAlernt.js"></script>-->

	</form>

</body>

</html>
