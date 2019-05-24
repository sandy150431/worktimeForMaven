<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.* ,java.io.*,java.util.List, 
	java.text.SimpleDateFormat,dao.*,model.*,day.* "%>
<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
	<div id="wrapper">
		<%@ include file="/resources/template/navbar_employee.file"%>

		<!-- Section: word -->
		<section id="doctor" class="home-section bg-gray paddingbot-60">
			<div class="container" style="margin-left: 10%;">

				<div class=" container section-heading text-center ">
					<h2 class="h-bold ">新增工時</h2>
					<div class="divider-short"></div>
				</div>

				<!-- 輸入列 -->
				<form id="postForm" action="" method="post" class="contactForm lead">
					<input type="hidden" id="week" name="week" value="${week }" />
					<input type="hidden" id="projects" name="projects" value="" />
					<input type="hidden" id="wts" name="wts" value="" />
					<input type="hidden" id="cts" name="cts" value="" />
					<input type="hidden" id="ots" name="ots" value="" />
					<input type="hidden" id="otcs" name="otcs" value="" />
					
					<table class="table table-bordered table-hover col-md-12">
						<thead>
							<tr>
								<th class="col-md-2" style="white-space: nowrap;">第${week }週</th>
								<th style="text-align: center;">一</th>
								<th style="text-align: center;">二</th>
								<th style="text-align: center;">三</th>
								<th style="text-align: center;">四</th>
								<th style="text-align: center;">五</th>
								<th style="text-align: center;">六</th>
								<th style="text-align: center;">日</th>
							</tr>
						</thead>

						<tr>
							<th class="col-md-2" style="white-space: nowrap;">日期</th>
							<th>${day1 }</th>
							<th>${day2 }</th>
							<th>${day3 }</th>
							<th>${day4 }</th>
							<th>${day5 }</th>
							<th>${day6 }</th>
							<th>${day7 }</th>
						</tr>
						<tbody>
							<tr>
								<td style="white-space: nowrap;">執行專案名稱</td>
								<td style="font-size: 17px;">
									<select id="project1" name="project">
										<option value="0" selected>請選擇專案</option>
										<%
											ProDAO proDao = new ProDAO();
											ResultSet rs = proDao.getMapOfPro();
											while (rs.next()) {
												String nm = rs.getString("PRO_NAME");
												String pj = rs.getString("PRO_CODE");
										%>
										<option value="<%=pj%>"><%=nm%></option>
										<%
											}
										%>
									</select>
								</td>
								<td style="font-size: 17px;">
									<select id="project2" name="project">
										<option value="0" selected>請選擇專案</option>
										<%
											rs.beforeFirst();
											while (rs.next()) {
												String pj2 = rs.getString("PRO_CODE");
												String nm2 = rs.getString("PRO_NAME");
										%>
										<option value="<%=pj2%>"><%=nm2%></option>
										<%
											}
										%>
									</select>
								</td>
								<td style="font-size: 17px;">
									<select id="project3" name="project">
										<option value="0" selected>請選擇專案</option>
										<%
											rs.beforeFirst();
											while (rs.next()) {
												String pj3 = rs.getString("PRO_CODE");
												String nm3 = rs.getString("PRO_NAME");
										%>
										<option value="<%=pj3%>"><%=nm3%></option>
										<%
											}
										%>
									</select>
								</td>
								<td style="font-size: 17px;">
									<select id="project4" name="project">
										<option value="0" selected>請選擇專案</option>
										<%
											rs.beforeFirst();
											while (rs.next()) {
												String pj4 = rs.getString("PRO_CODE");
												String nm4 = rs.getString("PRO_NAME");
										%>
										<option value="<%=pj4%>"><%=nm4%></option>
										<%
											}
										%>
									</select>
								</td>
								<td style="font-size: 17px;">
									<select id="project5" name="project">
										<option value="0" selected>請選擇專案</option>
										<%
											rs.beforeFirst();
											while (rs.next()) {
												String pj5 = rs.getString("PRO_CODE");
												String nm5 = rs.getString("PRO_NAME");
										%>
										<option value="<%=pj5%>"><%=nm5%></option>
										<%
											}
										%>
									</select>
								</td>
								<td style="font-size: 17px;">
									<select id="project6" name="project">
										<option value="0" selected>請選擇專案</option>
										<%
											rs.beforeFirst();
											while (rs.next()) {
												String pj6 = rs.getString("PRO_CODE");
												String nm6 = rs.getString("PRO_NAME");
										%>
										<option value="<%=pj6%>"><%=nm6%></option>
										<%
											}
										%>
									</select>
								</td>
								<td style="font-size: 17px;">
									<select id="project7" name="project">
										<option value="0" selected>請選擇專案</option>
										<%
											rs.beforeFirst();
											while (rs.next()) {
												String pj7 = rs.getString("PRO_CODE");
												String nm7 = rs.getString("PRO_NAME");
										%>
										<option value="<%=pj7%>"><%=nm7%></option>
										<%
											}
										%>
									</select>
								</td>
							<tr>
								<td style="white-space: nowrap;">普通工時</td>
								<td>
									<input id="workhour1" name="wt" maxlength="1" class="  col-md-4 form-control" type="number" min="0" max="8" required />
								</td>
								<td>
									<input id="workhour2" name="wt" maxlength="1" class="  col-md-4 form-control" type="number" min="0" max="8" required />
								</td>
								<td>
									<input id="workhour3" name="wt" maxlength="1" class="  col-md-4 form-control" type="number" min="0" max="8" required />
								</td>
								<td>
									<input id="workhour4" name="wt" maxlength="1" class="  col-md-4 form-control" type="number" min="0" max="8" required />
								</td>
								<td>
									<input id="workhour5" name="wt" maxlength="1" class="  col-md-4 form-control" type="number" min="0" max="8" required />
								</td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td style="height: 100px; white-space: nowrap;">工作內容</td>
								<td>
									<textarea rows="3" id="content1"  name="ct" class="  col-md-4 form-control" required></textarea>
								</td>
								<td>
									<textarea rows="3" id="content2" name="ct" class="  col-md-4 form-control" required></textarea>
								</td>
								<td>
									<textarea rows="3" id="content3" name="ct" class="  col-md-4 form-control" required></textarea>
								</td>
								<td>
									<textarea rows="3" id="content4" name="ct" class="  col-md-4 form-control" required></textarea>
								</td>
								<td>
									<textarea rows="3" id="content5" name="ct" class="col-md-4 form-control" required></textarea>
								</td>
								<td>週六日為加班</td>
								<td>週六日為加班</td>
							</tr>
							<tr>
								<td style="white-space: nowrap;">加班工時</td>
								<td>
									<input id="overtime1" name="ot" maxlength="1" class="  col-md-4 form-control" type="number" min="0" max="3" />
								</td>
								<td>
									<input id="overtime2" name="ot" maxlength="1" class="  col-md-4 form-control" type="number" min="0" max="3" />
								</td>
								<td>
									<input id="overtime3" name="ot" maxlength="1" class="  col-md-4 form-control" type="number" min="0" max="3" />
								</td>
								<td>
									<input id="overtime4" name="ot" maxlength="1" class="  col-md-4 form-control" type="number" min="0" max="3" />
								</td>
								<td>
									<input id="overtime5" name="ot" maxlength="1" class="  col-md-4 form-control" type="number" min="0" max="3" />
								</td>
								<td>
									<input id="overtime6" name="ot" maxlength="1" class="  col-md-4 form-control" type="number" min="0" max="3" />
								</td>
								<td>
									<input id="overtime7" name="ot" maxlength="1" class="  col-md-4 form-control" type="number" min="0" max="3" />
								</td>
							</tr>
							<tr>
								<td style="height: 100px; white-space: nowrap;">工作內容</td>
								<td>
									<textarea rows="3" id="otc1" name="otc" class="col-md-4 form-control"></textarea>
								</td>
								<td>
									<textarea rows="3" id="otc2" name="otc" class="col-md-4 form-control"></textarea>
								</td>
								<td>
									<textarea rows="3" id="otc3" name="otc" class="col-md-4 form-control"></textarea>
								</td>
								<td>
									<textarea rows="3" id="otc4" name="otc" class="col-md-4 form-control"></textarea>
								</td>
								<td>
									<textarea rows="3" id="otc5" name="otc" class="col-md-4 form-control"></textarea>
								</td>
								<td>
									<textarea rows="3" id="otc6" name="otc" class="col-md-4 form-control"></textarea>
								</td>
								<td>
									<textarea rows="3" id="otc7" name="otc" class="col-md-4 form-control"></textarea>
								</td>
							</tr>
						</tbody>
					</table>

					<!-- 輸入列結束 -->


					<div class="section-heading text-center">
						<input type="button" id="new" value="新增" class="btn btn-skin btn-lg" style="font-family: Microsoft JhengHei;" />
						<input type="button" id="save" value="儲存" class="btn btn-skin btn-lg" style="font-family: Microsoft JhengHei;" />
					</div>
				</form>
			</div>
		</section>
	</div>
	<!-- /Section: word -->

	<%@ include file="/resources/template/footer.file"%>


	<!-- Core JavaScript Files -->
	<!-- 功能表換色 -->
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript">
	$(function() {
		var form = $("#postForm");
		var action = form.attr('action');
		$(".active1").addClass("active");

		$("#new").click(function() {
			form.attr('action', 'newWorkhours');
			$("#projects").val(procArray("project"));//store array
			$("#wts").val(procArray("wt"));//store array
			$("#cts").val(procArray("ct"));//store array
			$("#ots").val(procArray("ot"));//store array
			$("#otcs").val(procArray("otc"));//store array
			form.submit();
		})
		$("#save").click(function() {
			form.attr('action', 'saveWorkhours');
			form.submit();
		})

		function procArray(n){
			var array =[];
			$("#postForm").find("*[name="+n+"]").each(function(){
				var value = $(this).val();
				alert(value);
				array.push(value);
			});
			return array;
		}
	});

	</script>
	<%@ include file="/resources/template/jsFile.file"%>

</body>

</html>
