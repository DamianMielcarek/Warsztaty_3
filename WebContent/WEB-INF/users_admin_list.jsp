<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Users list</title>
</head>
<body>

<h2>Users list</h2>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>username</th>
        <th>email</th>
        <th>password</th>
        <th>user_group_id</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach begin="0" end="${fn:length(users) - 1}" varStatus="loopStatus">
        <tr>
            <td>${users[loopStatus.index].getId()}</td>
            <td>${users[loopStatus.index].getUsername()}</td>
            <td>${users[loopStatus.index].getEmail()}</td>
            <td>${users[loopStatus.index].getPassword()}</td>
            <td>${users[loopStatus.index].getUser_group_id()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
