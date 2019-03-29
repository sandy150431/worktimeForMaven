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
										<span class="fa fa-pencil-square-o"></span> 更改密碼 
									</h3>
								</div>
								<div class="panel-body">

									<form action="UpdPwController" >
										<div class="col-xs-6 col-sm-6 col-md-6">
											<div class="form-group">
												<label for="userID">帳號</label> 
												<input type="text"	name="userID" id="userID" 
												placeholder="請輸入帳號" required
												class="form-control input-md">
											</div>
											<div class="form-group">
												<label for="password">舊密碼</label> 
												<input type="password" placeholder="請輸入舊密碼" required
													name="passwordOld" id="password" class="form-control input-md">
												<div class="validation"></div>
											</div>
										</div>
										
										<div class="col-xs-6 col-sm-6 col-md-6">
											<div class="form-group">
												<label for="password">輸入新密碼</label> 
												<input type="password" placeholder="請輸入新密碼" required
													name="passwordNew" id="password" class="form-control input-md">
												<div class="validation"></div>
											</div>
											<div class="form-group">
												<label for="password">再次輸入新密碼</label> 
												<input type="password" placeholder="請再次輸入新密碼" required
													name="passwordCheck" id="password" class="form-control input-md">
												<div class="validation"></div>
											</div>
										</div>

										<input type="submit" name="action" value="更改密碼" class="btn btn-skin btn-block btn-lg">
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
	<!-- 	<script src="./js/inputalernt.js"></script> -->
	<%@ include file="/resources/template/jsFile.file"%>

</body>

</html>
