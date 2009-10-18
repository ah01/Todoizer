<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    
    if(user != null){ // someone is logged in
        response.sendRedirect("/list/");
    }
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="cz.horcica.todoizer.SecurityHelper"%><html>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Todoizer</title>
        
        <link rel="stylesheet" href="/static/style/main.css" type="text/css">
        
    </head>
    <body>
        
        <div id="frame">
        
            <h1>Todoizer</h1>
    
            <p>Jednoduchý todo list.</p>

            <p>Pro použití se musíte <a href="<%= SecurityHelper.getLoginLink() %>">přihlásit</a></p>
            
        </div>

    </body>
</html>