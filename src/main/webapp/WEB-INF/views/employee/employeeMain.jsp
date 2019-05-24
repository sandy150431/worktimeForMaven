<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="dao.*, model.*, java.sql.*, java.util.*,controller.*"%>

<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
	<div id="wrapper">

		<%@ include file="/resources/template/navbar_employee.file"%>

		<!-- Section: word -->
		<section id="callaction" class="home-section paddingtop-40 paddingbot-300">
			<div class="container" style="margin-top: 10%;">
				<div class="wow fadeInUp" data-wow-delay="0.1s">
					<div class="cta-text">
						<h3>Wellcome Employee!</h3>
						<p>請點選功能</p>
					</div>
					<%
						String yn = (String) session.getAttribute("hurry");
						if (yn == null) {
							out.print("");
						} else {
							out.print(yn);
						}
					%>
					<div class="input-group input-group-lg" style="font-size: 30px;">
						<span class="glyphicon glyphicon-envelope" style="color: red"></span>
						退回通知
					</div>
				</div>
			</div>
		</section>
		<!-- /Section: word -->

		<%@ include file="/resources/template/footer.file"%>
	</div>

	<!-- Core JavaScript Files -->
	<!-- 功能表換色 -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript">
		$(".active0").addClass("active");
	</script>
	<%@ include file="/resources/template/jsFile.file"%>
</body>

</html>
