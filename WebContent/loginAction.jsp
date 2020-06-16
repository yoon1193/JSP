<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="human.HumanDao" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="human" class="human.Human" scope="page"/>
<jsp:setPropetty name="human" property="userID"/>
<jsp:setPropetty name="human" property="userPassword"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8">

<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		HumanDao humanDao = new HumanDao();
		int result = humanDao.login(human.getUserID(),human.getUserPassword());
		if(result==1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href ='main.jsp'");
			script.println("</script>");
		}
		else if(result==0){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 다릅니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		else if(result==-1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하지않는 아이디입니다')");
			script.println("history.back()");
			
			script.println("</script>");
		}
		else if(result==-1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('DB오류')");
			script.println("history.back()");
			script.println("</script>");
		}
	
	
	
	%>
</body>
</html> 