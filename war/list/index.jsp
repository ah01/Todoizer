<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>

<%@ page import="cz.horcica.todoizer.SecurityHelper" %>
<%@ page import="cz.horcica.todoizer.data.Task" %>

<jsp:useBean id="tasks" class="cz.horcica.todoizer.web.TasksListBean" scope="page">
    <jsp:setProperty name="tasks" property="*" />
</jsp:useBean>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Todoizer</title>
        
        <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
        <link rel="stylesheet" href="/static/style/main.css" type="text/css">
        
    </head>
    <body>
        
        <div id="frame">
        
	        
	        <div id="login">
	            <strong><%= SecurityHelper.getUserName() %></strong> | <a href="/help.html">Nápoděda</a> | <a href="<%= SecurityHelper.getLogoutLink() %>">Odhlásit</a>
	        </div>
            
            <div id="header">
	           <h1>Todoizer<sup>&beta;</sup></h1>
	           <em>jenoduchý todo list</em>
            </div>
	        
	        <div id="filter">
	           <form method="get">
	               <label>Filtr:</label>
	               <input type="text" size="10" name="filter" value="${tasks.filter}"></input>
	               <input type="submit" value="ok"></input>
	           </form>
	        </div>
	        
	        <div id="new-task">
		        <form action="save" method="post">
		            <label for="name">Nový úkol:</label>
		            <input type="text" name="name" id="name">
		            <label for="labels">, štítky:</label>
		            <input type="text" name="labels" id="labels">
		            <input type="submit" value="Vložit">
		        </form>
	        </div>
	        
	        <%-- List of tasks --%>
	               
	        <div id="tasks">
            
	            <% if(tasks.isFiltered()){ %>
	               <div class="filtered">
	                   Je použit filter pro štítek „<strong><%= tasks.getFilter() %></strong>“ – <a href=".">zrušit filtr</a>
	               </div>
	            <% } %>
		        
		        <% if(tasks.isEmpty()){ %>
		           <div class="info">
		               <% if(tasks.isFiltered()){ %>
		                  Žádný úkol neobsahuje štítek „<strong><%= tasks.getFilter() %></strong>“.
		               <% }else{ %>
		                  Nemáte žádné úkoly.
		               <% } %>
	               </div>
		        <% }else{ %>
		           <% for(Task task : tasks.getTasks()){ %>
		                <div class="task">
		                    <input type="checkbox" id="task<%= task.getId() %>"></iput>
		                    <% if(task.getLabels() != null) { %>
	                            <span class="labels">
		                           <% for(String label : task.getLabels()){ if(label.equals(tasks.getFilter())) continue; %>
		                           <a href="?filter=<%= label %>"><%= label %></a>
		                           <% } %>
		                       </span>
	                        <% } %>
			                <label for="task<%= task.getId() %>">
			                    <%= task.getName() %>
		                    </label>
		                    <span class="tools">
		                        <a href="/list/delete?id=<%= task.getId() %>" titl="smazat">&times;</a>
		                    </span>
		                </div>
			        <% } %>
                <% } %>
	           
	        </div>
        
        </div

    </body>
</html>

<% tasks.close(); %>
 