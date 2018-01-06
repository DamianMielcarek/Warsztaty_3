<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin panel</title>
</head>
<body>

<p><a href='<c:url value ="/exercises_list"/>'>exercises</a></p>
<p><a href='<c:url value ="/groups_list"/>'>users groups</a></p>
<p><a href='<c:url value ="/users_admin_list"/>'>users</a></p>
<p><a href='<c:url value ="/groups_management"/>'>groups_management</a></p>

</body>
</html>
