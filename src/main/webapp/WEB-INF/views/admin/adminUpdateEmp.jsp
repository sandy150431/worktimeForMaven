<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*, model.*, java.sql.*, java.util.*"%>

<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
	<div id="wrapper">
		<%@ include file="/resources/template/navbar_admin.file"%>


		<!-- Section: team -->
		<section id="doctor" class="home-section bg-gray paddingbot-60">
			<div class="container marginbot-50">
			<form action="EmpController?action=updateEmp">


				<!-- 表格 -->
				<div class="container">

					<div
						class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
						<div class="panel panel-info" style="border-color: white;">

							<!-- 標題 -->
							<div class="contentTitle" id="contentTitle"  style="font-family: Microsoft JhengHei;">員工資料修改</div>

							<!-- 內容 -->
							<div class="panel-body">


									<div class="form-group required fontChinese">
										<label for="empNo"
											class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">員工編號</label>
										<div class="controls col-md-8 ">
											<input id="empNo" name="empNo" value=${emp.empNo }
												readonly="readonly"
												class="input-md  textinput textInput form-control"
												style="margin-bottom: 10px" type="text" />
										</div>
									</div>

									<div class="form-group required fontChinese">
										<label for="empName"
											class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">員工姓名</label>
										<div class="controls col-md-8 ">
											<input id="empName" name="empName" value=${emp.empName }
												maxlength="30" required
												class="input-md  textinput textInput form-control"
												style="margin-bottom: 10px; font-family: Microsoft JhengHei;" type="text" />
										</div>
									</div>

									<div class="form-group required fontChinese">
										<label for="twid"
											class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">身分證字號</label>
										<div class="controls col-md-8 ">
											<input id="twid" name="twid" value=${emp.twid } maxlength="10"
												class="input-md  textinput textInput form-control"
												style="margin-bottom: 10px" type="text" required />
										</div>
									</div>

									<div class="form-group required fontChinese">
										<label for="email"
											class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">Email</label>
										<div class="controls col-md-8 ">
											<input id="email" name="email" value=${emp.email }
												class="input-md  textinput textInput form-control"
												style="margin-bottom: 10px" type="text"  required/>
										</div>
									</div>

									<div class="form-group required">
										<label for="sex" class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">性別</label>
										<div class="controls col-md-8 " style="margin-bottom: 10px">
											<label class="radio-inline"  style="font-family: Microsoft JhengHei;"> 
											<input type="radio" checked name="sex" value=1 style="margin-bottom: 10px">男
											</label> <label class="radio-inline"  style="font-family: Microsoft JhengHei;"> 
											<input type="radio"	name="sex" value=2 style="margin-bottom: 10px">女
											</label>
										</div>
									</div>

									<div class="form-group required">
										<label for="authority"
											class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">權限</label>
										<div class="controls col-md-8 " style="margin-bottom: 10px">
											<label class="radio-inline"> 
											<input type="radio"	checked name="authority" value="Admin" style="margin-bottom: 10px">Admin
											</label> <label class="radio-inline"  style="font-family: Microsoft JhengHei;"> 
											<input type="radio"	name="authority" value="Manager" style="margin-bottom: 10px">主管
											</label> <label class="radio-inline"  style="font-family: Microsoft JhengHei;"> 
											<input type="radio"	name="authority" value="Employee" style="margin-bottom: 10px">員工
											</label>
										</div>
									</div>

									<div class="form-group required fontChinese">
										<label for="firstDate"
											class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">到職日</label>
										<div class="controls col-md-8 ">
											<input id="firstDate" name="firstDate" value=${emp.firstDate }
												class="input-md  textinput textInput form-control"
												style="margin-bottom: 10px" type="text" required />
										</div>
									</div>
					
							</div>
							<!-- 內容結束 -->

									<div class="section-heading text-center">
										<div class="col-lg-12">
											<!-- name指動作；value指要做什麼動作 -->
											<input type="submit" name="action" value="確認修改" class="btn btn-skin btn-lg"  style="font-family: Microsoft JhengHei;">
										</div>
									</div>

						</div>
					</div>
				</div>
				<!-- 表格結束 -->

			</form>
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
	<!--<script src="js/inputAlernt.js"></script>-->


</body>

</html>
