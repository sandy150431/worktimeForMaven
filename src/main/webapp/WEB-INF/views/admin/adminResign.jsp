<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*, model.*, java.sql.*, java.util.*"%>

<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
	<div id="wrapper">
		<%@ include file="/resources/template/navbar_admin.file"%>


		<!-- Section: team -->
		<section id="doctor" class="home-section bg-gray paddingbot-60">
			<div class="container marginbot-50" style="height: 1000px;">
				<form id="postForm" action="" method="post">

					<!-- 表格 -->
					<div class="container">

						<div class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
							<div class="panel panel-info" style="border-color: white;">

								<!-- 標題 -->
								<div class="contentTitle" id="contentTitle" style="font-family: Microsoft JhengHei;">離職註記</div>

								<div class="panel-body">

									<!-- 內容 -->

									<div class="form-group required fontChinese" style="height: 30px;">
										<label for="empNo" class="control-label col-md-4  requiredField" style="font-family: Microsoft JhengHei;">員工編號</label>
										<div class="controls col-md-8 ">
											<input type="hidden" id="empNo" name="empNo" value=${emp.empNo } />
											<label class="control-label col-md-4  requiredField" style="width: 500px;"> ${emp.empNo}</label>
										</div>
									</div>

									<div class="form-group required fontChinese" style="height: 30px;">
										<label for="empName" class="control-label col-md-4  requiredField" style="font-family: Microsoft JhengHei;">員工姓名</label>
										<div class="controls col-md-8 ">
											<label class="control-label col-md-4  requiredField" style="width: 500px; font-family: Microsoft JhengHei;"> ${emp.empName}</label>
										</div>
									</div>

									<div class="form-group required" style="height: 30px;">
										<label for="resign" class="control-label col-md-4  requiredField" style="font-family: Microsoft JhengHei;">是否離職</label>
										<div class="controls col-md-8 " style="margin-bottom: 10px">
											<label class="control-label col-md-4  requiredField" style="width: 500px;">
												<input type="radio" name="resign" checked value=1 style="margin-bottom: 10px">
												是
											</label>
										</div>
									</div>

									<div class="form-group required" style="height: 30px;">
										<label for="authority" class="control-label col-md-4  requiredField" style="font-family: Microsoft JhengHei;">離職日期</label>
										<div class="controls col-md-8 ">
											<input type="text" value="${emp.resignDate}" name="resignDate" required placeholder="yyyy-mm-dd" class="input-md  textinput textInput form-control" style="margin-bottom: 10px; font-family: Microsoft JhengHei;" type="text" />
										</div>
									</div>
									<!-- 內容結束 -->
								</div>


								<div class="contentTitle" id="contentTitle">
									<div class="col-lg-12">
										<input type="submit" id="update" value="修改離職註記" class="btn btn-skin btn-lg">

										<!-- button純粹轉址 -->
										<input type="submit" id="reSearch" value="重新查詢" class="btn btn-skin btn-lg">
										<input type="submit" id="delete" value="刪除離職註記" class="btn btn-skin btn-lg">
									</div>
								</div>


							</div>
						</div>
					</div>
				</form>
				<!-- 表格結束 -->

			</div>
		</section>
		<!-- /Section: team -->


		<%@ include file="/resources/template/footer.file"%>
	</div>

	<!-- Core JavaScript Files -->
	<!-- 功能表換色 -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript">
		$(function() {
			var form = $("#postForm");
			var action = form.attr('action');
			$(".active4").addClass("active");

			$("#update").click(function() {
				form.attr('action', 'updateEmpResign');
				form.submit();
			})
			$("#delete").click(function() {
				form.attr('action', 'deleEmpResign');
				form.submit();
			})
			$("#reSearch").click(function() {
				form.attr('action', 'resignMain');
				form.submit();
			})
		})
	</script>

	<!--<script src="js/inputAlernt.js"></script>-->



</body>

</html>
