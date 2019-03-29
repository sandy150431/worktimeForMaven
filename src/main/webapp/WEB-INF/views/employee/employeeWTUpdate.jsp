<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.* ,java.io.*, java.util.List, dao.*, model.* "%>
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
					<h2 class="h-bold ">修改工時</h2>
					<div class="divider-short"></div>
				</div>

				<!-- 輸入列 -->

				<%
					WorkhoursDAO wqDAO = new WorkhoursDAO();
					String empNo = (String) request.getSession().getAttribute("userNo");
					java.sql.Date dd = wqDAO.findNewDateByNo(empNo);

					Workhours whr1 = new Workhours();
					Workhours whr2 = new Workhours();
					Workhours whr3 = new Workhours();
					Workhours whr4 = new Workhours();
					Workhours whr5 = new Workhours();
					Workhours whr6 = new Workhours();
					Workhours whr7 = new Workhours();

					whr1 = wqDAO.findWorkhoursByNo(empNo, dd, 1);
					whr2 = wqDAO.findWorkhoursByNo(empNo, dd, 2);
					whr3 = wqDAO.findWorkhoursByNo(empNo, dd, 3);
					whr4 = wqDAO.findWorkhoursByNo(empNo, dd, 4);
					whr5 = wqDAO.findWorkhoursByNo(empNo, dd, 5);
					whr6 = wqDAO.findWorkhoursByNo(empNo, dd, 6);
					whr7 = wqDAO.findWorkhoursByNo(empNo, dd, 7);

					session.setAttribute("whr1", whr1);
					session.setAttribute("whr2", whr2);
					session.setAttribute("whr3", whr3);
					session.setAttribute("whr4", whr4);
					session.setAttribute("whr5", whr5);
					session.setAttribute("whr6", whr6);
					session.setAttribute("whr7", whr7);
				%>

				<form action="WorkhourController" method="post" class="contactForm lead">

					<table class="table table-bordered table-hover col-md-12">
						<thead>
							<tr>
								<th class="col-md-2" style="white-space: nowrap;">第${whr1.we}週</th>
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
							<th><input id="workhour" name="dd1" maxlength="1"
								class="  col-md-4 form-control" type="text"
								value="${whr1.dd}" readonly /></th>
							<th><input id="workhour" name="dd2" maxlength="1"
								class="  col-md-4 form-control" type="text"
								value="${whr2.dd}" readonly /></th>
							<th><input id="workhour" name="dd3" maxlength="1"
								class="  col-md-4 form-control" type="text"
								value="${whr3.dd}" readonly /></th>
							<th><input id="workhour" name="dd4" maxlength="1"
								class="  col-md-4 form-control" type="text"
								value="${whr4.dd}" readonly /></th>
							<th><input id="workhour" name="dd5" maxlength="1"
								class="  col-md-4 form-control" type="text"
								value="${whr5.dd}" readonly /></th>
							<th><input id="workhour" name="dd6" maxlength="1"
								class="  col-md-4 form-control" type="text"
								value="${whr6.dd}" readonly /></th>
							<th><input id="workhour" name="dd7" maxlength="1"
								class="  col-md-4 form-control" type="text"
								value="${whr7.dd}" readonly /></th>
						</tr>
						<tbody>
						<tr>
						<%
								Connection conn = null;
								PreparedStatement pstmt = null;
								ResultSet rs = null;
								String sql = "select Pro_code ,Pro_name from Pro order by Pro_code";
								conn = ConnectionHelper.getConnection();
								pstmt = conn.prepareStatement(sql);
								rs = pstmt.executeQuery();
							%>
								<td style="white-space:nowrap;">執行專案名稱</td>
								<td style="font-size:17px;">
								<select id="project" name="project1">
										<option selected>請選擇專案</option>
										<%
											while (rs.next()) {
												String pj1 = rs.getString("PRO_CODE");
												String nm1 = rs.getString("PRO_NAME");
										%>
										<option value="<%=pj1%>" >
											<%=nm1%>
										</option>
										<%
											}
											conn.commit();
										%>
								</select></td>
								<%
									conn = ConnectionHelper.getConnection();
									pstmt = conn.prepareStatement(sql);
									rs = pstmt.executeQuery();
								%>
								<td style="font-size:17px;">
								<select id="project" name="project2">
										<option selected>請選擇專案</option>
										<%
											while (rs.next()) {
												String pj2 = rs.getString("PRO_CODE");
												String nm2 = rs.getString("PRO_NAME");
										%>
										<option value="<%=pj2%>"><%=nm2%></option>
										<%
											}
											conn.commit();
										%>
								</select></td>
								<%
									conn = ConnectionHelper.getConnection();
									pstmt = conn.prepareStatement(sql);
									rs = pstmt.executeQuery();
								%>
								<td style="font-size:17px;">
								<select id="project" name="project3">
										<option selected>請選擇專案</option>
										<%
											while (rs.next()) {
												String pj3 = rs.getString("PRO_CODE");
												String nm3 = rs.getString("PRO_NAME");
										%>
										<option value="<%=pj3%>"><%=nm3%></option>
										<%
											}
											conn.commit();
										%>
								</select></td>
								<%
									conn = ConnectionHelper.getConnection();
									pstmt = conn.prepareStatement(sql);
									rs = pstmt.executeQuery();
								%>
								<td style="font-size:17px;">
								<select id="project" name="project4">
										<option selected>請選擇專案</option>
										<%
											while (rs.next()) {
												String pj4 = rs.getString("PRO_CODE");
												String nm4 = rs.getString("PRO_NAME");
										%>
										<option value="<%=pj4%>"><%=nm4%></option>
										<%
											}
											conn.commit();
										%>
								</select></td>
								<%
									conn = ConnectionHelper.getConnection();
									pstmt = conn.prepareStatement(sql);
									rs = pstmt.executeQuery();
								%>
								<td style="font-size:17px;">
								<select id="project" name="project5">
										<option selected>請選擇專案</option>
										<%
											while (rs.next()) {
												String pj5 = rs.getString("PRO_CODE");
												String nm5 = rs.getString("PRO_NAME");
										%>
										<option value="<%=pj5%>"><%=nm5%></option>
										<%
											}
											conn.commit();
										%>
								</select></td>
								<%
									conn = ConnectionHelper.getConnection();
									pstmt = conn.prepareStatement(sql);
									rs = pstmt.executeQuery();
								%>
								<td style="font-size:17px;">
								<select id="project" name="project6">
										<option selected>請選擇專案</option>
										<%
											while (rs.next()) {
												String pj6 = rs.getString("PRO_CODE");
												String nm6 = rs.getString("PRO_NAME");
										%>
										<option value="<%=pj6%>"><%=nm6%></option>
										<%
											}
											conn.commit();
										%>
								</select></td>
								<%
									conn = ConnectionHelper.getConnection();
									pstmt = conn.prepareStatement(sql);
									rs = pstmt.executeQuery();
								%>
								<td style="font-size:17px;">
								<select id="project" name="project7">
										<option selected>請選擇專案</option>
										<%
											while (rs.next()) {
												String pj7 = rs.getString("PRO_CODE");
												String nm7 = rs.getString("PRO_NAME");
										%>
										<option value="<%=pj7%>"><%=nm7%></option>
										<%
											}
											conn.commit();
										%>

								</select></td>
							</tr>

							<tr>
								<td>工時</td>
								<td><input id="workhour" name="wt1" maxlength="1"
									class="  col-md-4 form-control" type="text"
									value="${whr1.whr}" /></td>
								<td><input id="workhour" name="wt2" maxlength="1"
									class="  col-md-4 form-control" type="text"
									value="${whr2.whr}" /></td>
								<td><input id="workhour" name="wt3" maxlength="1"
									class="  col-md-4 form-control" type="text"
									value="${whr3.whr}" /></td>
								<td><input id="workhour" name="wt4" maxlength="1"
									class="  col-md-4 form-control" type="text"
									value="${whr4.whr}" /></td>
								<td><input id="workhour" name="wt5" maxlength="1"
									class="  col-md-4 form-control" type="text"
									value="${whr5.whr}" /></td>
								<td><input id="workhour" name="wt6" maxlength="1"
									class="  col-md-4 form-control" type="text"
									value="${whr6.whr}" /></td>
								<td><input id="workhour" name="wt7" maxlength="1"
									class="  col-md-4 form-control" type="text"
									value="${whr7.whr}" /></td>


							</tr>
							<tr>
								<td style="height: 100px;">工作內容</td>
								<td><textarea rows="3" name="ct1"
										class="  col-md-4 form-control">${whr1.cont}</textarea></td>
								<td><textarea rows="3" name="ct2"
										class="  col-md-4 form-control">${whr2.cont}</textarea></td>
								<td><textarea rows="3" name="ct3"
										class="  col-md-4 form-control">${whr3.cont}</textarea></td>
								<td><textarea rows="3" name="ct4"
										class="  col-md-4 form-control">${whr4.cont}</textarea></td>
								<td><textarea rows="3" name="ct5"
										class="  col-md-4 form-control">${whr5.cont}</textarea></td>
								<td><textarea rows="3" name="ct6"
										class="  col-md-4 form-control">${whr6.cont}</textarea></td>
								<td><textarea rows="3" name="ct7"
										class="  col-md-4 form-control">${whr7.cont}</textarea></td>

							</tr>
							<tr>
								<td>加班工時</td>
								<td><input id="workhour" name="ot1" maxlength="1"
									class="  col-md-4 form-control" type="text"
									value="${whr1.ot}" /></td>
								<td><input id="workhour" name="ot2" maxlength="1"
									class="  col-md-4 form-control" type="text"
									value="${whr2.ot}" /></td>
								<td><input id="workhour" name="ot3" maxlength="1"
									class="  col-md-4 form-control" type="text"
									value="${whr3.ot}" /></td>
								<td><input id="workhour" name="ot4" maxlength="1"
									class="  col-md-4 form-control" type="text"
									value="${whr4.ot}" /></td>
								<td><input id="workhour" name="ot5" maxlength="1"
									class="  col-md-4 form-control" type="text"
									value="${whr5.ot}" /></td>
								<td><input id="workhour" name="ot6" maxlength="1"
									class="  col-md-4 form-control" type="text"
									value="${whr6.ot}" /></td>
								<td><input id="workhour" name="ot7" maxlength="1"
									class="  col-md-4 form-control" type="text"
									value="${whr7.ot}" /></td>

							</tr>
							<tr>
								<td style="height: 100px;">工作內容</td>
								<td><textarea rows="3" name="otc1"
										class="  col-md-4 form-control">${whr1.otCont}</textarea></td>
								<td><textarea rows="3" name="otc2"
										class="  col-md-4 form-control">${whr2.otCont}</textarea></td>
								<td><textarea rows="3" name="otc3"
										class="  col-md-4 form-control">${whr3.otCont}</textarea></td>
								<td><textarea rows="3" name="otc4"
										class="  col-md-4 form-control">${whr4.otCont}</textarea></td>
								<td><textarea rows="3" name="otc5"
										class="  col-md-4 form-control">${whr5.otCont}</textarea></td>
								<td><textarea rows="3" name="otc6"
										class="  col-md-4 form-control">${whr6.otCont}</textarea></td>
								<td><textarea rows="3" name="otc7"
										class="  col-md-4 form-control">${whr7.otCont}</textarea></td>
							</tr>
						</tbody>
					</table>
				

				<!-- 輸入列結束 -->


				<div class="section-heading text-center">
					<input type="submit" name="action" value="送出"
						class="btn btn-skin btn-lg"> <input type="submit"
						name="action" value="儲存" class="btn btn-skin btn-lg">

				</div>


			</div>
</form>

		</section>
	</div>
	<!-- /Section: word -->

	<%@ include file="/resources/template/footer.file"%>


	<!-- Core JavaScript Files -->
	<!-- 功能表換色 -->
	<script type="text/javascript"
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
	<script type="text/javascript">
		$(".active2").addClass("active");
	</script>
	<%@ include file="/resources/template/jsFile.file"%>
</body>

</html>
