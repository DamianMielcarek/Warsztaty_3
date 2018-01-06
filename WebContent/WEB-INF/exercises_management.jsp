<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Exercises management</title>
</head>
<body>

<h2>Exercises management</h2>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>title</th>
        <th>description</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach begin="0" end="${fn:length(exercises) - 1}" varStatus="loopStatus">
        <tr>
            <td>${exercises[loopStatus.index].getId()}</td>
            <td>${exercises[loopStatus.index].getTitle()}</td>
            <td><a href='<c:url value ="/exercise_details?id=${exercises[loopStatus.index].getId()}"/>'>details</a></td>
            <td><a href='<c:url value ="/exercises_edit?id=${exercises[loopStatus.index].getId()}"/>'>edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
