<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dao.*, model.*, java.sql.*, java.util.*,controller.*"%>

<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
	<div id="wrapper">
		<%@ include file="/resources/template/navbar_employee.file"%>


		<!-- Section: team -->
		<section id="doctor" class="home-section bg-gray paddingbot-300 ">
			<div class="container marginbot-50">


				<div class="section-heading text-center">
					<h2 class="h-bold">修改工時</h2>
					<div class="divider-short"></div>
					
					<!-- 查詢列 -->
					<form action="./SWTServlet" method="post" class="contactForm lead">
					<table class="front_input">
						<tr>
							<td class="td11"> <label for="dd">請輸入日期    </label></td>
							<td class="td2">
								<input type="text" name="dd" id="dd" required placeholder="yyyy-mm-dd" class="form-control input-md">
							</td>
							<td class="td4">
								<a href="./NWTServlet">
								<input type="submit" value="修改工時" class="btn btn-skin btn-sm">
								</a>
							</td>
						</tr>
						</table>
						</form>
						<!-- 查詢列結束 -->
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
		$(".active2").addClass("active");
	</script>

	<!--<script src="js/inputAlernt.js"></script>-->



</body>

</html>
