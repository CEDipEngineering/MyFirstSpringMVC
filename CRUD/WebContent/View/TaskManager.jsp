<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.insper.CRUD.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
        <!-- RESET -->
        <style>
            /* http://meyerweb.com/eric/tools/css/reset/ 
            v2.0 | 20110126
            License: none (public domain)
            */

            html, body, div, span, applet, object, iframe,
            h1, h2, h3, h4, h5, h6, p, blockquote, pre,
            a, abbr, acronym, address, big, cite, code,
            del, dfn, em, img, ins, kbd, q, s, samp,
            small, strike, strong, sub, sup, tt, var,
            b, u, i, center,
            dl, dt, dd, ol, ul, li,
            fieldset, form, label, legend,
            table, caption, tbody, tfoot, thead, tr, th, td,
            article, aside, canvas, details, embed, 
            figure, figcaption, footer, header, hgroup, 
            menu, nav, output, ruby, section, summary,
            time, mark, audio, video {
                margin: 0;
                padding: 0;
                border: 0;
                font-size: 100%;
                font: inherit;
                vertical-align: baseline;
            }
            /* HTML5 display-role reset for older browsers */
            article, aside, details, figcaption, figure, 
            footer, header, hgroup, menu, nav, section {
                display: block;
            }
            body {
                line-height: 1;
            }
            ol, ul {
                list-style: none;
            }
            blockquote, q {
                quotes: none;
            }
            blockquote:before, blockquote:after,
            q:before, q:after {
                content: '';
                content: none;
            }
            table {
                border-collapse: collapse;
                border-spacing: 0;
            }
        </style>
        <style>
            .section {
                display: flex;
                flex-flow: row wrap;
                justify-content: space-evenly;
                height: fit-content;
                padding: 0.3rem 3rem;
                margin: 0 3rem;
            }
            .Disp {
                display: flex;
                flex-flow: column nowrap;
                max-width: 35%;
            }
            body{
                font-family: 'Roboto', serif;
            }
            input[type=button], input[type=submit], input[type=reset] {
                border: 2px;
                border-radius: 5px;
                padding: 0.8rem 2rem;
                margin: 4px 2px;
                cursor: pointer;
                
            }
            input[type=text], input[type=textarea]{
                padding: 0.5rem;
            }
            input[type=text]:focus ,input[type=textarea]{
                background-color: lightblue;
            }
            .AddForm{
                display: flex;
                flex-flow: column nowrap;
                
                font-size: large;
                padding: 1rem 3rem;
                margin: 0 3rem;
                height: fit-content;
            }
            .header {
                display: flex;
                flex-flow: row nowrap;
                justify-content: space-between;
                padding: 1rem 4.5rem;
                font-size: x-large;
            }
            .buttons input{
                justify-content: start;
            }
            #SearchBar{
                width: 130px;
                box-sizing: border-box;
                border: 2px solid #ccc;
                border-radius: 4px;
                font-size: 16px;
                background-color: white;
                background-image: url('searchicon.png');
                background-position: 10px 10px; 
                background-repeat: no-repeat;
                padding: 0.3rem 1rem;
                margin-right: 2rem;
                -webkit-transition: width 0.4s ease-in-out;
                transition: width 0.4s ease-in-out;
            }
            #SearchBar:focus{
                width: 32rem;
            }
            input[type=submit]:hover{
                border-style: solid;
                border: 1px solid black ;
            }
            input[type=submit]{
                border-style: solid;
                border: 1px solid transparent;
            }
        </style>
    </head>
<body>

<section class="header">
    <h1>Task Manager</h1>
    <div style="display: flex; flex-flow: row nowrap; margin-right: 2rem;">
        <form>
            <input placeholder="Search..." id="SearchBar" name="search">
        </form>
        <form method="POST">
            <input type="submit" name="logout" value="Sign out" style="margin: 0; padding: 0.5rem 2rem;">
        </form>
    </div>
</section>
<div style="height: 1px; background-color: black; margin-bottom: 1rem;"></div>
<c:if test="${search}.trim().equals('')">
	<h3 style="padding: 0.3rem 3rem; margin: 0 3rem; font-size: large;">Showing search results for:" + ${search} +  </h3> <br>;
</c:if>

<c:choose>
	<c:when test="${taskSize}==0">
		<h3 style="padding: 0.3rem 3rem; margin: 0 3rem; font-size: large;"> No tasks found! You can create new tasks with the add button below!</h3> <br>
	</c:when>
	
	<c:otherwise>
		<c:forEach var="task" items="${tasks}" varStatus="id">
			<% Task task = (Task)pageContext.findAttribute("task");%>
			<c:choose>
				<c:when test="${task.date} == null">
					<section class = "section">
				        <div class = "Disp">
				            <h2 style="font-size: large;">${task.title}</h2>
				            <p style="font-size: small; color: rgba(0, 0, 0, 0.5);"></p>                
				        </div>
					    <form action="TaskManager" method = "POST" class="buttons">
					        <input type="submit" value="MODIFY" style="background-color: rgba(70, 190, 70, 0.8);" name="<%="Mod" + task.getId()%>">
					        <input type="submit" value="DELETE" style="background-color: rgba(190, 70, 70, 0.8);" name="<%="Del" + task.getId()%>"> 
					    </form>
					</section>
				</c:when>
				<c:otherwise>
					<section class = "section">
				        <div class = "Disp">
				            <h2 style="font-size: large;">${task.title}: to ${task.date}</h2>
				            <p style="font-size: small; color: rgba(0, 0, 0, 0.5);"></p>                
				        </div>
					    <form action="TaskManager" method = "POST" class="buttons">
					        <input type="submit" value="MODIFY" style="background-color: rgba(70, 190, 70, 0.8);" name="<%="Mod" + task.getId()%>">
					        <input type="submit" value="DELETE" style="background-color: rgba(190, 70, 70, 0.8);" name="<%="Del" + task.getId()%>"> 
					    </form>
					</section>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	</c:otherwise>
</c:choose> 	


<form action="TaskManager" method="POST" class="AddForm">
    <label for="Title">Title:</label>
    <input type="text" name = "Title" placeholder="Title of your note">
    <label for="Description">Description:</label>
    <textarea name = "Description" placeholder="Add a description" style="height: 7rem;"></textarea>
    <label for="Deadline">Deadline:</label>
    <input type="text" name = "Deadline" placeholder="Optional deadline">
    <input type="submit" value="ADD" name="Add">
</form>
</body>
</html>