<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/7/16
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script type="text/javascript" src="jquery-1.8.3.min .js"></script>
    <title>Title</title>
</head>
<body>
addstudent<br>
<form method="post" action="/testjsp2/taglibsever?method=addnewstudent">
<table border="1px">
    <tr>
        <th>sid</th>
        <th>sname</th>
        <th>sclass</th>
        <th>ssex</th>
        <th>sage</th>
    </tr>


        <tr>
            <td><input type="text"  name="sid" id="sid"></td>
            <td><input type="text"  name="sname"></td>
            <td><input type="text"  name="sclass"></td>
            <td><input type="text"  name="ssex"></td>
            <td><input type="text"  name="sage"></td>
        </tr>

</table>

<button onclick="addstudent()">提交</button>
</form>
</body>
<script>

    function addstudent()
    {
        let sid=$("#sid").val();
        let sids=${sids};
        for( let i in sids)
        {

            if(sid==i||sid=="")
            {
                alert("id已被占用");
                return;
            }
        }
        $(this).submit();
    }


</script>
</html>
