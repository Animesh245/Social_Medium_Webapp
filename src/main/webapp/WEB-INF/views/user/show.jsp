<%--
  Created by IntelliJ IDEA.
  User: warrior245
  Date: 8/15/22
  Time: 5:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show</title>
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
    <tr>
      <td>${resUserDto.id}</td>
      <td>${resUserDto.firstName}</td>
      <td>${resUserDto.lastName}</td>
      <td>${resUserDto.emailId}</td>
      <td>${resUserDto.dateOfBirth}</td>
      <td>${resUserDto.password}</td>
      <td>${resUserDto.role}</td>
      <td>${resUserDto.locationName}</td>
      <td>${resUserDto.profileImagePath}</td>
    </tr>
    </tbody>
  </table>
</body>
</html>
