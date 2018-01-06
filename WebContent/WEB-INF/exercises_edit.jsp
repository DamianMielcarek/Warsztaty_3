<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Exercises edit</title>
</head>
<body>

<h2>Exercises edit</h2>

<h3>Add new exercise</h3>
<form action='<c:url value = "/exercises_management"/>' method="POST">
    <input type="hidden" name="pageName" value="add"/>
    <label>
        title:
        <input type="text" name="title">
    </label>
    <label>
        description:
        <input type="text" name="description">
    </label>
    <input type="submit">
</form>

<h3>Edit exercise</h3>
<form action='<c:url value = "/exercises_management"/>' method="POST">
    <input type="hidden" name="pageName" value="edit"/>
    <input type="hidden" name="id"  value="${exercise.getId()}">
    <label>
        new title:
        <input type="text" name="title">
    </label>
    <label>
        new description:
        <input type="text" name="description">
    </label>
    <input type="submit">
</form>

</body>
</html>
