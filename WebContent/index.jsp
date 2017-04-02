<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.sql.*"%>
<%@ page import="org.projectdiaries.utility.DatabaseConnection" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
 <head>
  <title>ProjectRecords</title>
  <script type="text/javascript">
  var myVar=setInterval(function () {myTimer()}, 1000);
  var counter = 0;
  function myTimer() {
    var date = new Date();
    document.getElementById("demo").innerHTML = date.toLocaleTimeString();}
  </script>
  
  <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="css/indexpage.css">
	<script type="text/javascript" src="jquery-1.8.3.js"></script>
	<script type="text/javascript">
	$(".dropdown-menu li a").click(function(){

		  $(this).parents(".btn-group").find('.selection').text($(this).text());
		  $(this).parents(".btn-group").find('.selection').val($(this).text());

		});
	</script>
    <link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
 </head>
 
 <body>
 <% ServletContext sc = request.getServletContext();
	DatabaseConnection db = (DatabaseConnection) sc.getAttribute("db");
	Connection conn = db.getConnection();
	Statement statement = conn.createStatement() ;
	ResultSet rs =statement.executeQuery("select project_name from project order by project_name asc") ;%>
 <form action="./ProjectAddController" method="post">
    <div id = "container" style = "width:100%; padding: 50px;">
       <div class="timer" style = "float:left; width: 25%">
          <div id = "demo" style="font-family: fantasy; font-size: xx-large; text-align: center; margin-top: 5px">
       </div>
    </div>
 
    <div id="start"><input type="submit" name="start" class="btn btn-success btn-lg" value="start"></div>
    <div id="start"><input type="submit" name="stop" class="btn btn-danger btn-lg" value="stop"></div>
    <div id="lbl"><h2 style= "font-family: fantasy; font-weight: bold; text-align: right;">Project Diaries !</h2></div>
    </div>
 
    <div id = "container" style = "width:100%; padding: 150px">
       <div class="form-group">
          <div class="prjlbl">
             <label>Select Project Name</label>
             <label style="margin-left: 100px;">Billing Rate ($)</label>
          </div>
          <div class="dropdown">
             <select name="projname" id="projname" style="width: 250px; height: 30px;">
                <%  while(rs.next()){ %>
                <option> <%= rs.getString("project_name")%></option>
                <% } %>
             </select>
          </div>
          <div style="margin-left: 155px; margin-top: -53px; width: 50% ">
             <input type="text" class="form-control" id="rate" name="rate">
          </div>
          <div id="invoice">
             <input type="submit" class="btn btn-primary btn-lg" name="invoice" value="Generate Invoice">
          </div>
       </div>
       <table class="table">
          <thead>
             <tr>
                <th>SNO</th>
                <th>ProjectName</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Date</th>
                <th>Billing Rate</th>
                <th>Wages</th>
                <th>Status</th>
             </tr>
          </thead>
          <tbody>
          <%
        	  List<List<String>> list = (List<List<String>>) request.getAttribute("mylist");
              if(list != null)
              {
        	  for(int i=0; i<list.size(); i++)
        	  {  
        		  %>
        		  <tr>
        		  <%
        		  for(int j=0; j<list.get(i).size(); j++)
        		  {
        	      %>
                      <td><%=list.get(i).get(j) %></td>
                  <%
        	      }
        		  %>
        		  </tr>
        		  <%
        		  }
              } 
          %>
          </tbody>
       </table>
    </div>
 </form>
 <div class="form-group">
    <h3 style="font-family: fantasy; size:6px; color: red; text-align: center;">
    <b><%
       String err_msg = (String)request.getAttribute("errormsg");  
       if(err_msg!=null) { %>
    	   <%=err_msg %>
    	   <% } %>
       </b>
       </h3>
    </div> 
</body>
</html>