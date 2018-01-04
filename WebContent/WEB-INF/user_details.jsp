<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>User details</title>
</head>
<body>

<h2>User details</h2>

<p>username: ${user.getUsername()}</p>
<p>email: ${user.getEmail()}</p>

<h3>Added exercises solutions</h3>

<table>
    <thead>
    <tr>
        <th>exercise_id</th>
        <th>updated</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach begin="0" end="${fn:length(solutions) - 1}" varStatus="loopStatus">
        <tr>
            <td>${solutions[loopStatus.getIndex()].getExercise_id()}</td>
            <td>${solutions[loopStatus.getIndex()].getUpdated()}</td>
            <td><a href='<c:url value ="/solution_details?id=${solutions[loopStatus.getIndex()].getId()}"/>'>details</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
