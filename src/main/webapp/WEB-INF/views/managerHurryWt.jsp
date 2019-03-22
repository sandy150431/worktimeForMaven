<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.*,dao.*"%>
<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>
<style type="text/css">
input.bLarger {
	width: 20px;
	height: 20px;
	margin-left: 20px
}
</style>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
	<div id="wrapper">
		<%@ include file="/resources/template/navbar_manager.file"%>

		<!-- Section: team -->
		<section id="doctor" class="home-section bg-gray paddingbot-300">
			<div class="container marginbot-50">

				<div class="section-heading text-center ">
					<h2 class="h-bold ">未繳工時名單</h2>
					<div class="divider-short"></div>

				</div>

				<%
				HurryWtDAO hurrywtdao = new HurryWtDAO();
				List<Workhours> workhours = hurrywtdao.Find();
				Workhours workhour = null;
				%>
				<div class="col-md-6 col-md-offset-3">


					<table class="table  table-hover">
						<thead>
							<tr>
								<th class="col-md-1"></th>
								<th class="col-md-2" style="text-align:center;">員工編號</th>
								<th class="col-md-2" style="text-align:center;">員工姓名</th>
								<th class="col-md-1" style="text-align:center;">週別</th>
								<th class="col-md-1" style="white-space: nowrap; text-align:center;">催繳次數</th>
							</tr>
						</thead>
						<%
						for(int i = 0; i<workhours.size(); i++){
						workhour = workhours.get(i);
						request.setAttribute("workhour", workhour);	
			     		%>
						<thead>
							<tr>
								<td class="col-md-1" style="text-align:center;"><%=i+1%></td>
								<td class="col-md-2" style="text-align:center;">${workhour.empNo}</td>
								<td class="col-md-2" style="text-align:center;">${workhour.empName}</td>
								<td class="col-md-1" style="text-align:center;">${workhour.we}</td>
								<td class="col-md-1" style="text-align:center;">${workhour.hurry}</td>
							</tr>
							<%
					}
    			
			%>
						</thead>
						<tbody>

						</tbody>
					</table>
					<!-- 輸入列結束 -->

					<div class="section-heading text-center">
						<form action="./HurryWtServlet" method="post" >
							<input type="submit" value="寄送催繳通知" class="btn btn-skin btn-lg" style="font-family: Microsoft JhengHei;">
						</form>
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
	<script type="text/javascript">$(".active2").addClass("active");</script>
	<%@ include file="/resources/template/jsFile.file"%>
</body>
</html>