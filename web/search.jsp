<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/7/15
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="jquery-1.8.3.min .js"></script>
    <script type="text/javascript"src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
weclome ${username}<br>
 <button onclick="addnewstudent()">添加新学生</button> <button onclick={$("input[type='checkbox']:checked").each(function(){deletestudent($(this).val());})}>批量删除</button>

<table border="1px">
    <tr>
        <th>sid</th>
        <th>sname</th>
        <th>sclass</th>
        <th>ssex</th>
        <th>sage</th>
        <th> choice</th>
    </tr>
<c:forEach items="${students}"  var="student">

        <tr>
            <td>${student.sid}<input type="checkbox" value="${student.sid}" > </td>
            <td>${student.sname}</td>
            <td>${student.sclass}</td>
            <td>${student.ssex}</td>
            <td>${student.sage}</td>
            <td><button id="${student.sid}" onclick="deletestudent(${student.sid})">delete</button>
            <button id="al ${student.sid}" onclick="alterstudent(${student.sid})">alter</button> </td>
        </tr>

</c:forEach>
</table>
</body>
<script>
    function deletestudent(sid){
        $("#"+sid).parent().parent().remove();
        window.location.href="/testjsp2/taglibsever?method=deletestudent&sid="+sid;
    };
    function alterstudent(sid)
    {
        window.location.href="/testjsp2/taglibsever?method=alterstudentstart&sid="+sid;
    }
    function addnewstudent()
    {
        window.location.href="/testjsp2/taglibsever?method=addnewstudentstart";
    }
</script>
</html>
