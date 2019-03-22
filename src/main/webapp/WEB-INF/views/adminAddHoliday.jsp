<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="controller.*, dao.*, model.*, java.sql.*, java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
	<div id="wrapper">
		<%@ include file="/resources/template/navbar_admin.file"%>


		<!-- Section: team -->
		<section id="doctor" class="home-section bg-gray paddingbot-300 ">
			<div class="container marginbot-50">


				<form action="./HoliController" class="contactForm lead">
					<div class="section-heading text-center">
						<h2 class="h-bold">新增年度假日</h2>
						<div class="divider-short"></div>
						<!-- 查詢列 -->
						<table class="front_input">
							<tr>
								<td class="td1"><label for="year">請輸入年份        </label></td>
								<td class="td2">
									<input type="text" name="holiday" id="year" 
									placeholder="yyyy" required class="form-control input-md"></td>
								<td class="td4">
									<input type="submit" name="action" value="新增年度例假日" class="btn btn-skin btn-sm" style="font-family: Microsoft JhengHei;"></td>
							</tr>
						</table>
						
					<c:if test="${not empty errorMsgs['job']}">
                		<div class="col-xs-12 col-sm-4 form-control-static">
                  		<div class="fju-input-error">${errorMsgs['job']}</div>
                		</div>
              		</c:if>
						
						
						
						<!-- 查詢列結束 -->
					</div>
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
		$(".active2").addClass("active");
	</script>

	<!--<script src="js/inputAlernt.js"></script>-->



</body>

</html>
