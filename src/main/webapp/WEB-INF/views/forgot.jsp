<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
	<div id="wrapper">

		<!-- 功能列  -->
		<%@ include file="/resources/template/navbar_login.file"%>


		<!-- Section: intro -->
		<section id="doctor" class="home-section bg-gray paddingbot-60">
			<div class="container marginbot-50">

				<!-- 這裡是登入框框 css是套用css資料夾中的style-->
				<div class="col-lg-6">
					<div class="form-wrapper">
						<div class="wow fadeInRight" data-wow-duration="2s"
							data-wow-delay="0.2s">
							<div class="panel panel-skin">
							
								<!-- 登入框Title -->
								<div class="panel-heading">
									<h3 class="panel-title">
										<span class="fa fa-pencil-square-o"></span> 忘記密碼 </br>
										<small>       (請輸入身分驗證資料：身分確認後將自動寄發密碼到您的個人信箱中)</small>
									</h3>
								</div>
								<div class="panel-body">

									<form action="ForgotPwController">
										<div class="col-xs-6 col-sm-6 col-md-6">
											<div class="form-group">
												<label for="userID">帳號</label> 
												<input type="text"	name="userID" id="userID" 
												placeholder="請輸入帳號" required
												class="form-control input-md">
											</div>
										</div>
										
										<div class="col-xs-6 col-sm-6 col-md-6">
											<div class="form-group">
												<label for="twid">身分證字號</label> 
												<input type="text" placeholder="請輸入身分證字號" required
													name="twid" id="twid" class="form-control input-md">
												<div class="validation"></div>
											</div>
										</div>
										
										<div class="form-group">
												<label for="email">Email</label> <small>(需與原申請之E-MAIL相同)</small>
												<input type="text" placeholder="請輸入email" required
													name="email" id="email" class="form-control input-md">
												<div class="validation"></div>
										</div>
											
										<input type="submit" name="action" value="忘記密碼" class="btn btn-skin btn-block btn-lg">
									</form>
								</div>
							</div>

						</div>
					</div>
				</div>
				<!-- 這裡是登入框框結束-->

			</div>
		</section>


		<%@ include file="/resources/template/footer.file"%>
	</div>

	<!-- Core JavaScript Files -->
	<!--<script src="js/inputAlernt.js"></script>-->
	<%@ include file="/resources/template/jsFile.file"%>

</body>

</html>
