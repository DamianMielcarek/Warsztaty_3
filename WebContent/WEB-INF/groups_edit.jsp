<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Groups edit</title>
</head>
<body>

<h2>Groups edit</h2>

<h3>Add new group</h3>
<form action='<c:url value = "/groups_management"/>' method="POST">
    <input type="hidden" name="pageName" value="add"/>
    <label>
        name:
        <input type="text" name="name">
    </label>
    <input type="submit">
</form>

<h3>Edit group</h3>
<form action='<c:url value = "/groups_management"/>' method="POST">
    <input type="hidden" name="pageName" value="edit"/>
    <input type="hidden" name="id"  value="${group.getId()}">
    <label>
        new group name:
        <input type="text" name="name">
    </label>
    <input type="submit">
</form>

</body>
</html>
