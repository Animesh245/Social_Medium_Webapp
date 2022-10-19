<jsp:useBean id="responseLocationDto" scope="request" type="com.animesh245.social_medium.dto.response.ResponseLocationDto"/>

<%--
  Created by IntelliJ IDEA.
  User: warrior245
  Date: 6/5/22
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css">
</head>
<body>

<%--<%@include file="../../navbar.jsp" %>--%>
<div class="container">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Location</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${responseLocationDto.id}</td>
            <td>${responseLocationDto.locationName}</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>

