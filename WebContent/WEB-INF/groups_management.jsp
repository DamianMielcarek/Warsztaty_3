<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Groups management</title>
</head>
<body>

<h2>Groups management</h2>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach begin="0" end="${fn:length(groups) - 1}" varStatus="loopStatus">
        <tr>
            <td>${groups[loopStatus.index].getId()}</td>
            <td>${groups[loopStatus.index].getName()}</td>
            <td><a href='<c:url value ="/groups_edit?id=${groups[loopStatus.index].getId()}"/>'>edit</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
