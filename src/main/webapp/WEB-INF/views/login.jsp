<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.sql.*,java.util.*,dao.*,model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
	<div id="wrapper">

		<!-- 功能列  -->
		<%@ include file="/resources/template/navbar_login.file"%>

		<!-- Section: intro -->

		<section id="doctor" class="home-section bg-gray paddingbot-300">
			<div class="container marginbot-50">

				<!-- 這裡是登入框框 css是套用css資料夾中的style-->
				<div class="col-lg-6 col-md-offset-3">
					<div class="form-wrapper">
						<div class="wow fadeInRight" data-wow-duration="2s"
							data-wow-delay="0.2s">
							<div class="panel panel-skin">
							
								<!-- 登入框Title -->
								<div class="panel-heading">
									<h3 class="panel-title">
										<span class="fa fa-pencil-square-o"></span> 登入 <small>(login)</small>
									</h3>
								</div>
								<div class="panel-body">
									<form action="login" method="post" class="contactForm lead">
										<div class="col-xs-6 col-sm-6 col-md-6">
											<div class="form-group">
												<label for="userNo">帳號</label> 
												<input type="text"  name="userNo" id="userno" maxlength="9" placeholder="請輸入你的帳號" required class="form-control input-md"/>
											</div>
										</div>

										<div class="col-xs-6 col-sm-6 col-md-6">
											<div class="form-group">
												<label for="password">密碼</label> 
												<input type="password"  name="password" maxlength="20"  placeholder="請輸入你的密碼" required id="password" class="form-control input-md">
												<div class="validation"></div>
											</div>
										</div>


										<div class="col-xs-6 col-sm-6 col-md-6">
											<div class="form-group">
												<input type="submit" value="登入" class="btn btn-skin btn-block btn-lg">
											</div>
										</div>

										<div class="col-xs-6 col-sm-6 col-md-6">
											<div class="form-group">
												<input type="button" value="忘記密碼" class="btn btn-skin btn-block btn-lg" 
											onclick="javascript:location.href='Forgot.jsp'">
											</div>
										</div>
									
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
	<!--<script src="<c:url value="/resources/js/inputalernt.js" />"></script>-->
	<%@ include file="/resources/template/jsFile.file"%>

</body>

</html>
