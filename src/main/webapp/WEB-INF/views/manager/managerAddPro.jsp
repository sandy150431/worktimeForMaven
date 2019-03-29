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
				<form action="ProController">

					<!-- 表格 -->
					<div class="container">

						<div
							class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
							<div class="panel panel-info" style="border-color: white;">

								<!-- 標題 -->
								<div class="section-heading text-center">
									<h2 class="h-bold">新增專案</h2>
									<div class="divider-short"></div>
								</div>


								<div class="panel-body">

									<!-- 內容 -->


									<div class="form-group required fontChinese">
										<label for="proCode"
											class="control-label col-md-3  requiredField"
											style="font-family: Microsoft JhengHei;">專案編號</label>
										<div class="controls col-md-8 ">
											<input id="proCode" name="proCode" readonly="readonly"
												value="自動編號"
												class="input-md  textinput textInput form-control"
												style="margin-bottom: 10px" type="text" />
										</div>
									</div>

									<div class="form-group required fontChinese">
										<label for="proName"
											class="control-label col-md-3  requiredField"
											style="font-family: Microsoft JhengHei;">專案名稱</label>
										<div class="controls col-md-8 ">
											<input id="proName" name="proName" maxlength="30"
												class="input-md  textinput textInput form-control"
												style="margin-bottom: 10px" type="text" required />
										</div>
									</div>

								</div>
								<!-- 內容結束 -->

								<div class="section-heading text-center">
									<div class="col-lg-12">
										<input type="submit" name="action" value="新增專案"
											class="btn btn-skin btn-lg"
											style="font-family: Microsoft JhengHei;">
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
		$(".active4").addClass("active");
	</script>
	<!--<script src="js/inputAlernt.js"></script>-->
</body>
</html>
