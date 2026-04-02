<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.javarush.poroshina.quest.constants.AppConstants" %>

<html>

<head>
    <meta charset="UTF-8">
    <title><%= AppConstants.GAME_TITLE %></title>
</head>

<body>

<% if (session.getAttribute("userName") == null) { %>

<h2><%= AppConstants.START_HEADER %></h2>
<h3><%= AppConstants.START_TEXT %></h3>

<form action="<%= request.getContextPath() %>/GameServlet" method="post">
    <input type="text" name="userName" placeholder="<%= AppConstants.USER_NAME_PLACEHOLDER %>">
    <button type="submit"><%= AppConstants.START_GAME_TEXT %></button>
</form>

<% } %>

</body>

</html>

