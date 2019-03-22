<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*, model.*, java.sql.*, java.util.*,controller.*"%>

<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
	<div id="wrapper">
		<%@ include file="/resources/template/navbar_manager.file"%>


		<!-- Section: team -->
		<section id="doctor" class="home-section bg-gray paddingbot-60">
			<div class="container marginbot-50">


				<form action="./VWorkhourAController?action=SearchOne">
					<div class="section-heading text-center">
						<h2 class="h-bold">查詢專案</h2>
						<div class="divider-short"></div>

						<!-- 查詢列 -->
						<table class="front_input">
							<tr>
								<td class="td2" style="width: 220px; font-size: 15px;"><label
									for="proCode">請輸入欲查詢之專案代碼 </label></td>
								<td class="td2"><input type="text" name="proCode"
									id="proCode" required class="form-control input-md"></td>
								<td class="td3"><input type="submit" name="action"
									value="查詢" class="btn btn-skin btn-sm"
									style="margin-left: 20px;"></td>
							</tr>
						</table>
					</div>
				</form>

				<!-- css是tablecss.css -->
				<div class="wrapper">
					<%
						VWorkhourADAO vWorkhourADAO = new VWorkhourADAO();
													List<VWorkhourA> vWorkhourAs = vWorkhourADAO.findAllWorkhour();
													VWorkhourA vWorkhourA = null;
					%>
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
							for(int i = 0; i<vWorkhourAs.size(); i++){
																	vWorkhourA = vWorkhourAs.get(i);
																	request.setAttribute("vWorkhourA", vWorkhourA);
						%>

						<tbody>
							<tr>
								<!-- emp抓到後一列，用getEmpNo()分別取出各欄位的值，填進表格中 -->
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



</body>

</html>
