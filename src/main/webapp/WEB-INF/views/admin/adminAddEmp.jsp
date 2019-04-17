<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.*, model.*, java.sql.*, java.util.*,controller.*"%>

<!DOCTYPE html>
<html lang="zh-Hant">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-latest.pack.js"></script>
<%@ include file="/resources/template/head.file"%>
<script>
</script>


<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
<form action="addEmp" method="post" >
	<div id="wrapper">
		<%@ include file="/resources/template/navbar_admin.file"%>


		<!-- Section: team -->
		<section id="doctor" class="home-section bg-gray paddingbot-60">
			<div class="container marginbot-50">
				
				<!-- 表格 -->
				<div class="container">

					<div
						class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
						<div class="panel panel-info" style="border-color: white;">

							<!-- 標題 -->
							<div class="section-heading text-center">
								<h2 class="h-bold">新增員工</h2>
							<div class="divider-short"></div>
							</div>
			

							<div class="panel-body">

								<!-- 內容 -->
								

									<div class="form-group required fontChinese">
										<label for="empNo" class="control-label col-md-4  requiredField" style="font-family: Microsoft JhengHei;">員工編號</label>
										<div class="controls col-md-8 " style="font-family: Microsoft JhengHei;">
											<input id="empNo" name="empNo" readonly="readonly" value="自動編號"
												class="input-md  textinput textInput form-control"
												style="margin-bottom: 10px" type="text" />
										</div>
									</div>

									<div class="form-group required fontChinese">
										<label for="empName" class="control-label col-md-4  requiredField" style="font-family: Microsoft JhengHei;">員工姓名</label>
										<div class="controls col-md-8 ">
											<input id="empName" name="empName" maxlength="30"
												class="input-md  textinput textInput form-control"
												style="margin-bottom: 10px" type="text" required />
										</div>
									</div>

									<div class="form-group required fontChinese">
										<label for="pw" class="control-label col-md-4  requiredField" style="font-family: Microsoft JhengHei;">密碼</label>
										<div class="controls col-md-8 ">
											<input id="pw" name="pw" maxlength="30"
												class="input-md  textinput textInput form-control"
												style="margin-bottom: 10px" type="text" required />
										</div>
									</div>

									<div class="form-group required fontChinese">
										<label for="twid"
											class="control-label col-md-4  requiredField" style="font-family: Microsoft JhengHei;">身分證字號</label>
										<div class="controls col-md-8 ">
											<input id="twid" name="twid" maxlength="10"
												class="input-md  textinput textInput form-control"
												style="margin-bottom: 10px" type="text" required />
										</div>
									</div>

									<div class="form-group required fontChinese">
										<label for="email"
											class="control-label col-md-4  requiredField" style="font-family: Microsoft JhengHei;">Email</label>
										<div class="controls col-md-8 ">
											<input id="email" name="email"
												class="input-md  textinput textInput form-control"
												style="margin-bottom: 10px" type="text"  required/>
										</div>
									</div>

									<div class="form-group required">
										<label for="sex" class="control-label col-md-4  requiredField" style="font-family: Microsoft JhengHei;">性別</label>
										<div class="controls col-md-8 " style="margin-bottom: 10px">
											<label class="radio-inline" style="font-family: Microsoft JhengHei;" style="font-family: Microsoft JhengHei;"> <input type="radio"
												name="sex" value=1 checked style="margin-bottom: 10px">男
											</label> <label class="radio-inline" style="font-family: Microsoft JhengHei;"> <input type="radio"
												name="sex" value=2 style="margin-bottom: 10px">女
											</label>
										</div>
									</div>

									<div class="form-group required">
										<label for="authority"
											class="control-label col-md-4  requiredField" style="font-family: Microsoft JhengHei;">權限</label>
										<div class="controls col-md-8 " style="margin-bottom: 10px">
											<label class="radio-inline" style="font-family: Microsoft JhengHei;">
											<input type="radio"	name="authority" value="Admin" style="margin-bottom: 10px">Admin
											</label> 
											<label class="radio-inline" style="font-family: Microsoft JhengHei;"> 
											<input type="radio" name="authority" value="Manager" style="margin-bottom: 10px">主管
											</label> 
											<label class="radio-inline" style="font-family: Microsoft JhengHei;"> 
											<input type="radio" name="authority" checked  value="Employee" style="margin-bottom: 10px">員工
											</label>
										</div>
									</div>

									<div class="form-group required fontChinese">
										<label for="firstDate"
											class="control-label col-md-4  requiredField" style="font-family: Microsoft JhengHei;">到職日</label>
										<div class="controls col-md-8 ">
											<input id="firstDate" name="firstDate"
												placeholder="yyyy-mm-dd"
												class="input-md  textinput textInput form-control"
												style="margin-bottom: 10px" type="text" required />
										</div>
									</div>



									<div class="section-heading text-center">
										<div style="margin-top: 30px;" class="col-lg-12">
											<input type="submit" name="action" value="新增" class="btn btn-skin btn-lg" style="font-family: Microsoft JhengHei;">
										</div>
									</div>




							</div>
							<!-- 內容結束 -->



						</div>
					</div>
				</div>
				<!-- 表格結束 -->


			</div>
		</section>
		<!-- /Section: team -->



		<%@ include file="/resources/template/footer.file"%>
	</div>

	<!-- Core JavaScript Files -->
	<!-- 功能表換色 -->
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript">	$(".active5").addClass("active");</script>
	<!--<script src="js/inputAlernt.js"></script>-->
	
	</form>
</body>
</html>
