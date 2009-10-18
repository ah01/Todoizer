<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="cz.horcica.todoizer.SecurityHelper" %>
<%@ page import="cz.horcica.todoizer.data.Task" %>

<jsp:useBean id="tasks" class="cz.horcica.todoizer.logic.TaskRepository" scope="page" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Todoizer</title>
        
        <link rel="stylesheet" href="/static/style/main.css" type="text/css">
        
    </head>
    <body>
        
        <div id="frame">
        
	        
	        <div style="text-align:right">
	            [<a href="<%= SecurityHelper.getLogoutLink() %>">Odhlásit</a>]
	        </div>
	
	        <h1>Todoizer</h1>
	        
	        <hr>
	        
	        <form action="save" method="get">
	            <label>Nový úkol:</label>
	            <input type="text" name="name">
	            <input type="submit" title="OK">
	        </form>
	        
	        <hr>
	        
	        <% for(Task task : tasks.getTasks()){ %>
                <div>
                    <input type="checkbox" id="task<%= task.getId() %>"></iput>
                    <label for="task<%= task.getId() %>"><%= task.getName() %></label>
                </div>
	        <% } %>
        
        </div>
        
    </body>
</html>

<% tasks.close(); %>
 