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
			<div class="container marginbot-50" style="height:1000px;">


				<!-- 表格 -->
				<div class="container">
				
					<div
						class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
						<div class="panel panel-info" style="border-color: white;">

							<!-- 標題 -->
							<div class="contentTitle" id="contentTitle"  style="font-family: Microsoft JhengHei;">員工資料查詢結果</div>
							
							<form action="./EmpController" >
							<div class="panel-body">


				<% //讓數字轉成中文
					java.util.Map<Integer, String> sexMap = new java.util.HashMap<Integer, String>();
						sexMap.put(1, "Male");
						sexMap.put(2, "Female");
						pageContext.setAttribute("sexMap", sexMap);

						java.util.Map<Integer, String> resignMap = new java.util.HashMap<Integer, String>();
						resignMap.put(1, "離職");
						resignMap.put(2, "");
						pageContext.setAttribute("resignMap", resignMap);
				%>
												<!-- 內容 -->

									<div class="form-group required fontChinese"  style="height:30px;">
										<label for="empNo" class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">員工編號</label>
										<div class="controls col-md-8 ">
											<label class="control-label col-md-4  requiredField" style="width:500px;"> ${emp.empNo}</label>
										</div>
									</div>

									<div class="form-group required fontChinese" style="height:30px;">
										<label for="empName" class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">員工姓名</label>
										<div class="controls col-md-8 ">
											<label class="control-label col-md-4  requiredField" style="width:500px; font-family: Microsoft JhengHei;"> ${emp.empName}</label>
										</div>
									</div>

									<div class="form-group required fontChinese" style="height:30px;">
										<label for="twid"
											class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">身分證字號</label>
										<div class="controls col-md-8 ">
											<label class="control-label col-md-4  requiredField" style="width:500px;"> ${emp.twid}</label>
										</div>
									</div>

									<div class="form-group required fontChinese" style="height:30px;">
										<label for="email" class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">Email</label>
										<div class="controls col-md-8 ">
											<label class="control-label col-md-4  requiredField" style="width:500px;"> ${emp.email}</label>
										</div>
									</div>

									<div class="form-group required" style="height:30px;">
										<label for="sex" class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">性別</label>
										<div class="controls col-md-8 " style="margin-bottom: 10px">
											<label class="control-label col-md-4  requiredField" style="width:500px;"> ${sexMap[emp.sex]}</label>
										</div>
									</div>

									<div class="form-group required" style="height:30px;">
										<label for="authority" class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">權限</label>
										<div class="controls col-md-8 " style="margin-bottom: 10px">
											<label class="control-label col-md-4  requiredField" style="width:500px;"> ${emp.authority}</label>
										</div>
									</div>

									<div class="form-group required fontChinese" style="height:30px;">
										<label for="firstDate" class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">到職日</label>
										<div class="controls col-md-8 ">
											<label class="control-label col-md-4  requiredField" style="width:500px;"> ${emp.firstDate}</label>
										</div>
									</div>
									
									<div class="form-group required fontChinese" style="height:30px;">
										<label for="resignDate"
											class="control-label col-md-4  requiredField"  style="font-family: Microsoft JhengHei;">離職日</label>
										<div class="controls col-md-8 ">
											<label class="control-label col-md-4  requiredField" style="width:500px;"> ${emp.resignDate}</label>
										</div>
									</div>
								</div>
								
									<div class="contentTitle" id="contentTitle">
										<div class="col-lg-12">
											<!-- name指動作；value指要做什麼動作 -->
											<input type="submit" name="action" value="修改" class="btn btn-skin btn-lg"  style="font-family: Microsoft JhengHei;"/>
											
											<!-- button純粹轉址 -->
											<input type ="button" onclick="javascript:location.href='adminFindEmp.jsp'" value="重新查詢" class="btn btn-skin btn-lg"  style="font-family: Microsoft JhengHei;">
											<input type="submit" name="action" value="刪除" class="btn btn-skin btn-lg"  style="font-family: Microsoft JhengHei;"/>
										</div>
									</div>



								</form>
							
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
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript">	$(".active3").addClass("active");</script>
	<!--<script src="js/inputAlernt.js"></script>-->


</body>

</html>
