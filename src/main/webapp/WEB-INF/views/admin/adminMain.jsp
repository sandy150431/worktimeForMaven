<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom" style="backgroud-color:white;">
	<div id="wrapper">
		<%@ include file="/resources/template/navbar_admin.file"%>


		<!-- Section: word -->
		<section id="callaction" class="home-section paddingtop-40 paddingbot-300">
			<div class="container" style="margin-top: 10%;">
				<div class="wow fadeInUp" data-wow-delay="0.1s">
					<div class="cta-text">
						<h3>Wellcome Admin!</h3>
						<p>請點選功能</p>
					</div>
				</div>
			</div>
		</section>
		<!-- /Section: word -->

	</div>
	<%@ include file="/resources/template/footer.file"%>
	<!-- Core JavaScript Files -->
	<!-- 功能表換色 -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript">	$(".active0").addClass("active");
	</script>
	
	<%@ include file="/resources/template/jsFile.file"%>
	
</body>

</html>
