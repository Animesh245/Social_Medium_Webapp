<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: warrior245
  Date: 8/14/22
  Time: 12:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>List</title>
</head>
<body>

<table border="1">

<thead>
  <tr>
    <th>Id</th>
    <th>Firstname</th>
    <th>Lastname</th>
    <th>Email</th>
    <th>Date of Birth</th>
    <th>Password</th>
    <th>Role</th>
    <th>Location</th>
    <th>Attachment Path</th>
  </tr>
</thead>
  <tbody>
  <jsp:useBean id="resUserDtoList" scope="request" type="java.util.List"/>
  <c:forEach items="${resUserDtoList}" var="user">
    <tr>
      <td>${user.id}</td>
      <td>${user.firstName}</td>
      <td>${user.lastName}</td>
      <td>${user.emailId}</td>
      <td>${user.dateOfBirth}</td>
      <td>${user.password}</td>
      <td>${user.role}</td>
      <td>${user.locationName}</td>
      <td>${user.profileImagePath}</td>
    </tr>
  </c:forEach>
  </tbody>


</table>

</body>
</html>
