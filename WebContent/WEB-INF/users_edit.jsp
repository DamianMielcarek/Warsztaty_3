<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users edit</title>
</head>
<body>

<h2>Users edit</h2>

<h3>Add new user</h3>
<form action='<c:url value = "/users_management"/>' method="POST">
    <input type="hidden" name="pageName" value="add"/>
    <label>
        username:
        <input type="text" name="username">
    </label>
    <label>
        email:
        <input type="email" name="email">
    </label>
    <label>
        password:
        <input type="password" name="password">
    </label>
    <label>
        user_group_id:
        <input type="number" name="user_group_id">
    </label>
    <input type="submit">
</form>

<h3>Edit user</h3>
<form action='<c:url value = "/users_management"/>' method="POST">
    <input type="hidden" name="pageName" value="edit"/>
    <input type="hidden" name="id"  value="${user.getId()}">
    <label>
        new username:
        <input type="text" name="username">
    </label>
    <label>
        new email:
        <input type="email" name="email">
    </label>
    <label>
        new password:
        <input type="password" name="password">
    </label>
    <label>
        new user_group_id:
        <input type="number" name="user_group_id">
    </label>
    <input type="submit">
</form>

</body>
</html>
