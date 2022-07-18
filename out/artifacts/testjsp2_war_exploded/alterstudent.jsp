<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/7/15
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="jquery-1.8.3.min .js"></script>
</head>
<body>
alter<br>
<table border="1px">
    <tr>
        <th>sid</th>
        <th>sname</th>
        <th>sclass</th>
        <th>ssex</th>
        <th>sage</th>
    </tr>
    <form action="<c:url value="/taglibsever?method=alterstudent"/>" method="post">
    <c:forEach items="${students}"  var="student">

        <tr>
            <td><input type="text" value="${student.sid}" hidden name="sid">${student.sid}</td>
            <td><input type="text" value="${student.sname}" name="sname"></td>
            <td><input type="text" value="${student.sclass}"name="sclass"> </td>
            <td><input type="text" value="${student.ssex}" name="ssex"></td>
            <td><input type="text" value=" ${student.sage}" name="sage"></td>
            <td><input type="submit"></td>
        </tr>

    </c:forEach>
</table>

</body>
</html>
