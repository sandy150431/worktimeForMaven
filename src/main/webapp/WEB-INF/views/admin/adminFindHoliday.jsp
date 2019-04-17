<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="controller.*, dao.*, model.*, java.sql.*, java.util.*"%>

<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
	<div id="wrapper">
		<%@ include file="/resources/template/navbar_admin.file"%>


		<!-- Section: team -->
		<section id="doctor" class="home-section bg-gray paddingbot-60">
			<div class="container marginbot-50">


				<form action="searchHoli" method="post" class="contactForm lead">
					<div class="section-heading text-center">
						<h2 class="h-bold">修改例假日</h2>
						<div class="divider-short"></div>

						<!-- 查詢列 -->
						<table class="front_input">
							<tr>
								<td class="td11" style="width:250px;"><label for="holiday">請輸入欲修改之例假日    </label></td>
								<td class="td2"><input type="text" name="holiday" id="holiday"
									required placeholder="yyyy-mm-dd"
									class="form-control input-md"></td>
								<td class="td3">
									<input type="submit" value="查詢例假日" class="btn btn-skin btn-sm" style="margin-left:20px; font-family: Microsoft JhengHei;" >
								</td>
							</tr>
						</table>
					</div>
				</form>

				<!-- css是tablecss.css -->
				<div class="wrapper">
					<%
						HoliDAO holiDAO = new HoliDAO();//新增EmpDAO
						List<Holi> holis = holiDAO.findAllHoli();
						Holi holi = null;
					%>
					<table id="acrylic">
						<thead>
							<tr>
								<th>假日日期</th>
								<th>假日日期</th>
								<th>假日時數</th>
							</tr>
						</thead>

					<%
						//跑一個迴圈，每一個圈都讓emp去抓emps的一列
						for(int i = 0; i<holis.size(); i++){
							holi = holis.get(i);
							request.setAttribute("holi", holi);
					%>

						<tbody>
							<tr>
								<td>${holi.holiday}</td>
								<td>${holi.holiReason}</td>
								<td>${holi.hrs}</td>
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
		$(".active1").addClass("active");
	</script>

	<!--<script src="js/inputAlernt.js"></script>-->



</body>

</html>
