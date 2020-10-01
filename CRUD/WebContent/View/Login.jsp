<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.insper.CRUD.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
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
            body{
                font-family: 'Roboto', serif;
                font-size: larger;
            }
            input[type=button], input[type=submit], input[type=reset] {
                border: 2px black;
                border-style: solid;
                border-radius: 5px;
                padding: 0.8rem 2rem;
                margin: 4px 2px;
                cursor: pointer; 
            }

            input[type=button]:hover, input[type=submit]:hover, input[type=reset]:hover {
                border: 2px rgb(150, 150, 150);
                border-style: solid;
            }

            input[type=text], input[type=textarea], input[type=password]{
                padding: 0.5rem;
            }
            input[type=text]:focus ,input[type=textarea]:focus, input[type=password]:focus{
                background-color: lightblue;
            }
            .header {
                display: flex;
                flex-flow: row nowrap;
                justify-content: space-between;
                padding: 1rem 6rem;
                font-size: x-large;
            }
            .form{
                display: flex;
                flex-flow: column nowrap;
                width: 50%;
                margin: auto;
            }
        </style>
    </head>
<body>

<section class="header">
    <h1>Task Manager</h1>
</section>
<div style="height: 1px; background-color: black; margin-bottom: 1rem;"></div>

<form action="Login" method ="post" class="form">
    <label for="username">Username:</label>
    <input type="text" name="username" placeholder="Username:" required>
    <label for="password">Password:</label>
    <input type="password" name="password" placeholder="Password:" required>
    <div style="margin: auto;">
        <input type="submit" value="Sign in" name="login" style="background-color: seagreen;">
        <input type="submit" value="Sign up" name="register" style="background-color: turquoise;">
    </div>
    </form>
    <c:if test="${validUser}">
    	<h5 style="text-align: center; color:red">Invalid user</h5>
	</c:if>
    <c:if test="${validPassword}">
    	<h5 style="text-align: center; color:red">Invalid password</h5>
	</c:if>
</body>
</html>