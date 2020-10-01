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
</section>
<div style="height: 1px; background-color: black; margin-bottom: 1rem;"></div>

<% Task tsk = (Task)request.getAttribute("tsk"); %>

<form action="TaskManager" method="POST" class="AddForm">
    <label for="Title">Title:</label>
    <input type="text" name = "Title" placeholder="Title of your note" value="<%= tsk.getTitle()%>">
    <label for="Description">Description:</label>
    <textarea name = "Description" placeholder="Add a description" style="height: 7rem;"><%= tsk.getTitle()%></textarea>
    <label for="Deadline">Deadline:</label>
    <input type="text" name = "Deadline" placeholder="Optional deadline" value="<%= tsk.getTitle()%>">
    <input type="submit" value="ADD" name="Add">
</form>





</body>
</html>