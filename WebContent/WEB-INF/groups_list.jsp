<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Groups list</title>
</head>
<body>

<h2>Groups list</h2>

<table>
    <thead>
    <tr>
        <th>name</th>
        <th>id</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach begin="0" end="${fn:length(groups) - 1}" varStatus="loopStatus">
        <tr>
            <td>${groups[loopStatus.getIndex()].getName()}</td>
            <td><a href='<c:url value ="/users_list?id=${groups[loopStatus.getIndex()].getId()}"/>'>${groups[loopStatus.getIndex()].getId()}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
