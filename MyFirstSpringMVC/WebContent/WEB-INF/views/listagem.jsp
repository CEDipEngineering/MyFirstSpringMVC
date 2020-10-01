<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table>
<tr><td>Descrição</td><td>Finalizado</td><td>dataFinalização</td></tr>
<c:forEach var="task" items="${tasks}" varStatus="id">
	
	<tr>
	<td>task.descricao</td>
	<td>NULL</td>
	<td>NULL</td>
	</tr>
	
</c:forEach>
</table>
</body>
</html>