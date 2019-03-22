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


				<form action="./EmpController?action=list" class="contactForm lead">
					<div class="section-heading text-center">
						<h2 class="h-bold">離職員工註記</h2>
						<div class="divider-short"></div>

						<!-- 查詢列 -->
						<table class="front_input">
							<tr>
								<td class="td11">
								<label for="empNo">請輸入員工編號  </label></td>
								<td class="td2">
									<input type="text" name="empNo" id="empNo"
									required
									class="form-control input-md"></td>
								<td class="td3">
									<input type="submit" name="action" value="離職查詢"
									class="btn btn-skin btn-sm" style="margin-left:20px;"></td>
							</tr>
						</table>
					</div>
				</form>



			<%
				EmpDAO empDAO = new EmpDAO();//新增EmpDAO
				List<Emp> emps = empDAO.findAllEmp();//新增一個emps陣列用來裝findAllEmp()找到的值
				Emp emp = null;
			%>

				<!-- css是tablecss.css -->
				<div class="wrapper">
					<table id="acrylic">
						<thead>
							<tr>
								<th>員工編號</th>
								<th>員工姓名</th>
								<th>是否離職</th>
								<th>離職日</th>
							</tr>
						</thead>

			<%
			    for(int i = 0; i<emps.size();i++){
			     emp = emps.get(i);
			     request.setAttribute("emp", emp);
			%>

						<tbody>
							<tr>
								<td>${emp.empNo}</td>
								<td>${emp.empName}</td>
								<td>${emp.resign}</td>
								<td>${emp.resignDate}</td>
							</tr>
			<%
				}
			%>
						</tbody>
					</table>
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
		$(".active4").addClass("active");
	</script>

	<!--<script src="js/inputAlernt.js"></script>-->



</body>

</html>
