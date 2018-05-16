<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/count" method="post" >
    <input  type="file" name="file" />
    <input type="submit" value="GetCount" />
</form>
<spring:url value="/show" var="downloadURL"/>
<a href="${downloadURL}">Show</a>

</body>
</html>
