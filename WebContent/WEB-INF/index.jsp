<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="header.jsp" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>

<h1>The latest solutions</h1>

<table>
    <thead>
        <tr>
            <th>exercise_id</th>
            <th>users_id</th>
            <th>updated</th>
            <th>description</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach begin="0" end="${fn:length(solutions) - 1}" varStatus="loopStatus">
            <tr>
                <td>${solutions[loopStatus.getIndex()].getExercise_id()}</td>
                <td>${solutions[loopStatus.getIndex()].getUsers_id()}</td>
                <td>${solutions[loopStatus.getIndex()].getUpdated()}</td>
                <td>${solutions[loopStatus.getIndex()].getDescription()}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
<%@ include file="footer.jsp" %>
