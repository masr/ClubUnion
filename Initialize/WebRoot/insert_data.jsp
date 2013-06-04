<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" import ="cn.edu.nju.clubunion.InsertData.InsertStart"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">

  </head>
  
  <body><br>
     <%(new InsertStart()).start(); %>
     It's running successfully!
  </body>
</html>
