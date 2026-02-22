<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
    <h1>Profile</h1>
    <h2>Username: <%= request.getSession().getAttribute("username")%> </h2>
    <form action="logout" method="post">
        <input type="submit" value="exit">
    </form>
</body>
</html>
