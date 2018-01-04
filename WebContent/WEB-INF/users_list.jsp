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
        <th>username</th>
        <th>id</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach begin="0" end="${fn:length(users) - 1}" varStatus="loopStatus">
        <tr>
            <td>${users[loopStatus.getIndex()].getUsername()}</td>
            <td><a href='<c:url value ="/user_details?id=${users[loopStatus.getIndex()].getId()}"/>'>${users[loopStatus.getIndex()].getId()}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
