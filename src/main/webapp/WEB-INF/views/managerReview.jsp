<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import="dao.*, java.sql.*, model.*, java.util.*, controller.* "%>


<!DOCTYPE html>
<html lang="zh-Hant">

<%@ include file="/resources/template/head.file"%>
<script type="text/javascript"
 src="https://code.jquery.com/jquery-latest.pack.js"></script>
<script>
 $(function() {
  $(".toggle").click(function() {
   var _this = $(this).attr("href");
   if ($(_this).css("display") == "none") {
    $(_this).slideDown();

   } else {
    $(_this).slideUp();

   }
   return false;
  });
 }); 
</script>


<style type="text/css">
input.bLarger {
 width: 20px;
 height: 20px;
 margin-left: 20px;
}
</style>

<body id="page-top" data-spy="scroll" data-target=".navbar-custom">
 <div id="wrapper">
  <%@ include file="/resources/template/navbar_manager.file"%>

  <!-- Section: team -->
  <section id="doctor" class="home-section bg-gray paddingbot-60">
   <div class="container marginbot-50">

    <div class="section-heading text-center">
     <h2 class="h-bold" style="font-family: Microsoft JhengHei;">審核工時</h2>
     <div class="divider-short"></div>
     <br>
    </div>

    <!-- 輸入列 -->
    <form action="ManagerController">



     <% //讓數字轉成中文
      java.util.Map<String, String> weMap = new java.util.HashMap<String, String>();
      weMap.put("1", "第1週");weMap.put("11", "第11週");weMap.put("21", "第21週");
      weMap.put("2", "第2週");weMap.put("12", "第12週");weMap.put("22", "第22週");
      weMap.put("3", "第3週");weMap.put("13", "第13週");weMap.put("23", "第23週");
      weMap.put("4", "第4週");weMap.put("14", "第14週");weMap.put("24", "第24週");
      weMap.put("5", "第5週");weMap.put("15", "第15週");weMap.put("25", "第25週");
      weMap.put("6", "第6週");weMap.put("16", "第16週");weMap.put("26", "第26週");
      weMap.put("7", "第7週");weMap.put("17", "第17週");weMap.put("27", "第27週");
      weMap.put("8", "第8週");weMap.put("18", "第18週");weMap.put("28", "第28週");
      weMap.put("9", "第9週");weMap.put("19", "第19週");weMap.put("29", "第29週");
      weMap.put("10", "第10週");weMap.put("20", "第20週");weMap.put("30", "第30週");
      weMap.put("31", "第31週");weMap.put("41", "第41週");weMap.put("51", "第51週");
      weMap.put("32", "第32週");weMap.put("42", "第42週");weMap.put("52", "第52週");
      weMap.put("33", "第33週");weMap.put("43", "第43週");
      weMap.put("34", "第34週");weMap.put("44", "第44週");
      weMap.put("35", "第35週");weMap.put("45", "第45週");
      weMap.put("36", "第36週");weMap.put("46", "第46週");
      weMap.put("37", "第37週");weMap.put("47", "第47週");
      weMap.put("38", "第38週");weMap.put("48", "第48週");
      weMap.put("39", "第39週");weMap.put("49", "第49週");
      weMap.put("40", "第40週");weMap.put("50", "第50週");
      pageContext.setAttribute("weMap", weMap);
     %>
     
     <div class="col-md-11  "  >

      <table class="table table-hover ">
       <tbody >
        <tr>
         <th class="col-md-1 " style="width:200px;"></th>
         <th class="col-md-1 " style="text-align:center;">通過</th>
         <th class="col-md-1 " style="text-align:center;">不通過</th>
         <th class="col-md-1 " style="text-align:center;">原因</th>
         <th class="col-md-1 " style="white-space:nowrap; text-align:center;">詳細工時</th>
         <th class="col-md-1 " style="white-space:nowrap; text-align:center;">員工編號</th>
         <th class="col-md-1 " style="white-space:nowrap; text-align:center;">姓名</th>
         <th class="col-md-1 " style="text-align:center; width:50px;">週別</th>
         <th class="col-md-2 " style="white-space:nowrap; text-align:center;">一週普通工時</th>
         <th class="col-md-2 " style="white-space:nowrap; text-align:center;">一週加班工時</th>
        </tr>
       </tbody>
       
       <tbody>
        <tr>
         <th class="col-md-1 " style="font-weight:lighter;">全選</th>
         <th class="col-md-1 ">
          <input type="checkbox" class="bLarger passAll" id="passAll" > 
           <script type="text/javascript">
            //通過全選
            $("#passAll").click(function() {
             if($("#passAll").prop("checked")) {
                 $('.pass').each(function() {
                     $(this).prop("checked", true);
                 });
               } else {
                 $('.pass').each(function() {
                     $(this).prop("checked", false);
                 });           
               }
            });
            
           </script>
         </th>
         <th class="col-md-1 " >
          <input type="checkbox" class="bLarger failAll" id="failAll" > 
           <script type="text/javascript">
            //不通過全選
            $("#failAll").click(function() {
             if($("#failAll").prop("checked")) {
                 $('.fail').each(function() {
                     $(this).prop("checked", true);
                 });
               } else {
                 $('.fail').each(function() {
                     $(this).prop("checked", false);
                 });           
               }
            });
           </script>
         </th>
           <!-- 通過和不通過不能同時選 -->
           <script type="text/javascript">
            $("#failAll").click(function() {
             if($("#passAll").prop("checked")) {
                 $('.pass').each(function() {
                     $(this).prop("checked", false);
                 });
               } else {
                  $('.pass').each(function() {
                      $(this).prop("checked", false);
                  });           
                }
            });
            
            $("#passAll").click(function() {
             if($("#failAll").prop("checked")) {
                 $('.fail').each(function() {
                     $(this).prop("checked", false);
                 });
               } else {
                  $('.fail').each(function() {
                      $(this).prop("checked", false);
                  });           
                }
            });
            </script>
            
         <th class="col-md-1 "> </th>
         <th class="col-md-1 "> </th>
         <th class="col-md-2 "> </th>
         <th class="col-md-1 "> </th>
         <th class="col-md-2 "> </th>
         <th class="col-md-2 "> </th>
        </tr>
       </tbody>

       <%
        ManagerReviewDAO mrDAO = new ManagerReviewDAO();
        List<Workhours> whrs = mrDAO.findAllNotReviewWorkhours();
        Workhours whr = null;              
                 
        ManagerQueryDAO mqDAO = new ManagerQueryDAO();
        Workhours whr1 = new Workhours() ;
        Workhours whr2 = new Workhours() ;
        Workhours whr3 = new Workhours() ;
        Workhours whr4 = new Workhours() ;
        Workhours whr5 = new Workhours() ;
        Workhours whr6 = new Workhours() ;
        Workhours whr7 = new Workhours() ;
                   
        for(int i = 0; i < whrs.size(); i++){
         whr = whrs.get(i);
         request.setAttribute("whr", whr);
                                    
         String empNo = whr.getEmpNo();
         String we = whr.getWe();
         java.sql.Date dd = mqDAO.findDateByWe(we).getDd();

         whr1 = mqDAO.findWorkhoursByNo(empNo, dd, 1);
         whr2 = mqDAO.findWorkhoursByNo(empNo, dd, 2);
         whr3 = mqDAO.findWorkhoursByNo(empNo, dd, 3);
         whr4 = mqDAO.findWorkhoursByNo(empNo, dd, 4);
         whr5 = mqDAO.findWorkhoursByNo(empNo, dd, 5);
         whr6 = mqDAO.findWorkhoursByNo(empNo, dd, 6);
         whr7 = mqDAO.findWorkhoursByNo(empNo, dd, 7);
         
         session.setAttribute("whr1", whr1);
         session.setAttribute("whr2", whr2);
         session.setAttribute("whr3", whr3);
         session.setAttribute("whr4", whr4);
         session.setAttribute("whr5", whr5);
         session.setAttribute("whr6", whr6);
         session.setAttribute("whr7", whr7);
       %>
       

       <tbody>
        <tr>
         <th scope="row"><%=i+1%></th>
         <td><!-- 通過 -->
          <fieldset class="form-group">
           <input type="checkbox" class="bLarger <%=i+1%> pass" name="stat" value="3" id=<%=i+1%>> 
           <input type="checkbox" class="bLarger <%=i+1%> pass" name="empNo" value=${whr.empNo } id=<%=i+1%> style="display:none;"> 
           <input type="checkbox" class="bLarger <%=i+1%> pass" name="we" value=${whr.we } id=<%=i+1%> style="display:none;"> 
           <script type="text/javascript">
               //隱藏窗格全選
            $("#<%=i+1%>").click(function() {
             if($("#<%=i+1%>").prop("checked")) {
                 $('.<%=i+1%>').each(function() {
                     $(this).prop("checked", true);
                 });
               } else {
                 $('.<%=i+1%>').each(function() {
                     $(this).prop("checked", false);
                 });           
               }
            });
            
           </script>
          </fieldset>
         </td>
         <td><!-- 不通過 -->
          <fieldset class="form-group">
           <input type="checkbox" class="bLarger <%="not"+i+1%> fail" name="stat" value="3" id=<%="not"+i+1%>> 
           <input type="checkbox" class="bLarger <%="not"+i+1%> fail" name="empNo" value=${whr.empNo } id=<%="not"+i+1%> style="display:none;"> 
           <input type="checkbox" class="bLarger <%="not"+i+1%> fail" name="we" value=${whr.we } id=<%="not"+i+1%> style="display:none;"> 
           <script type="text/javascript">
            //隱藏窗格全選
            $("#<%="not"+i+1%>").click(function() {
             if($("#<%="not"+i+1%>").prop("checked")) {
                 $('.<%="not"+i+1%>').each(function() {
                     $(this).prop("checked", true);
                 });
               } else {
                 $('.<%="not"+i+1%>').each(function() {
                     $(this).prop("checked", false);
                 });           
               }
            });
            
           </script>
          </fieldset>
         </td>
           <!-- 通過和不通過不能同時選 -->
           <script type="text/javascript">
            $("#<%="not"+i+1%>").click(function() {
             if($("#<%=i+1%>").prop("checked")) {
                 $('.<%=i+1%>').each(function() {
                     $(this).prop("checked", false);
                 });
               } else {
                  $('.<%=i+1%>').each(function() {
                      $(this).prop("checked", false);
                  });           
                }
            });
            
            $("#<%=i+1%>").click(function() {
             if($("#<%="not"+i+1%>").prop("checked")) {
                 $('.<%="not"+i+1%>').each(function() {
                     $(this).prop("checked", false);
                 });
               } else {
                  $('.<%="not"+i+1%>').each(function() {
                      $(this).prop("checked", false);
                  });           
                }
            });
            
            </script>
            
         
         <td><!-- 拒絕窗格-->
          <textarea rows="1" name="rr" id=<%="needInput"+i+1%>
          class="col-md-4 form-control" style="width:100px;"></textarea>
           <script type="text/javascript">
           $("#<%="not"+i+1%>").click(function() {
            if($("#<%="not"+i+1%>").prop("checked")) {
             $("#<%="needInput"+i+1%>").prop('required', true);
              } else {
               $("#<%="needInput"+i+1%>").prop('required', null);      
            }
           });
           
           $("#<%=i+1%>").click(function() {
            if($("#<%="not"+i+1%>").prop("checked")) {
             $("#<%="needInput"+i+1%>").prop('required', true);
              } else {
               $("#<%="needInput"+i+1%>").prop('required', null);      
            }
           });
           </script>
         </td>
         
            
         <td style="text-align:center;"><!-- 放大鏡-->
          <a class="blue-text  toggle" href=#<%=i+1+"a"%> >
          <i class="fa fa-search fa-lg"></i></a>
         </td>
         <td style="white-space:nowrap;">${whr.empNo}</td>
         <td style="white-space:nowrap;">${whr.empName}</td>
         <td style="white-space:nowrap; text-align:center;">${weMap[whr.we]}</td>
         <td style="white-space:nowrap; text-align:center;">${whr.whr}</td>
         <td style="white-space:nowrap; text-align:center;">${whr.ot}</td>

        </tr>

        <tr>
         <td colspan="9" class="content">
          <div id=<%=i+1+"a"%> style="display: none;">



           <!-- 伸縮table -->
           <table class="table table-bordered table-hover col-md-12"
            style="width: 1000px; margin-left:100px;">
            <thead>
             <tr>
              <th class="col-md-2" style="width:80px;"></th>
              <th>一</th>
              <th>二</th>
              <th>三</th>
              <th>四</th>
              <th>五</th>
              <th>六</th>
              <th>日</th>
             </tr>
            </thead>

            <tbody>
             <tr>
              <td style="width:30px;">日期</td>
              <td>${whr1.dd}</td>
              <td>${whr2.dd}</td>
              <td>${whr3.dd}</td>
              <td>${whr4.dd}</td>
              <td>${whr5.dd}</td>
              <td>${whr6.dd}</td>
              <td>${whr7.dd}</td>
             </tr>


             <tr>
              <td style="width:30px;">普通工時</td>
              <td>${whr1.whr}</td>
              <td>${whr2.whr}</td>
              <td>${whr3.whr}</td>
              <td>${whr4.whr}</td>
              <td>${whr5.whr}</td>
               <td>${whr6.whr}</td>
              <td>${whr7.whr}</td>
             </tr>
             <tr>
              <td style="width:30px;">工作內容</td>
              <td>${whr1.cont}</td>
              <td>${whr2.cont}</td>
              <td>${whr3.cont}</td>
              <td>${whr4.cont}</td>
              <td>${whr5.cont}</td>
              <td>${whr6.cont}</td>
             <td>${whr7.cont}</td>
             </tr>
             <tr>
              <td style="width:30px;">加班工時</td>
              <td>${whr1.ot}</td>
              <td>${whr2.ot}</td>
              <td>${whr3.ot}</td>
              <td>${whr4.ot}</td>
              <td>${whr5.ot}</td>
              <td>${whr6.ot}</td>
              <td>${whr7.ot}</td>
             </tr>
             <tr>
              <td style="width:30px;">工作內容</td>
              <td>${whr1.otCont}</td>
              <td>${whr2.otCont}</td>
              <td>${whr3.otCont}</td>
              <td>${whr4.otCont}</td>
              <td>${whr5.otCont}</td>
              <td>${whr6.otCont}</td>
              <td>${whr7.otCont}</td>
             </tr>
            </tbody>
           </table>
           <!-- 伸縮table結束 -->



          </div>
         </td>
        </tr>

        <%
         }
        
        %>
       </tbody>
      </table>


     <div class="text-center">
      <input type="submit" name="action" value="送出審核"
       class="btn btn-skin btn-lg">
      <input type="submit" name="action" value="匯出"
       class="btn btn-skin btn-lg">
     </div>
     
     
     </div>
     <script type="text/javascript"
      src="/try/bootstrap/twitter-bootstrap-v2/docs/assets/js/jquery.js"></script>
     <script type="text/javascript"
      src="/try/bootstrap/twitter-bootstrap-v2/docs/assets/js/bootstrap-collapse.js"></script>



     <!-- 輸入列結束 -->

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
  $(".active1").addClass("active");
 </script>
 <%@ include file="/resources/template/jsFile.file"%>


</body>
</html>