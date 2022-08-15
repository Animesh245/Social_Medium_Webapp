<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

  <%--@elvariable id="resUserDto" type="com.animesh245.social_medium.dto.response.ResUserDto"--%>
  <form:form modelAttribute="resUserDto" >
    <label>Id</label>
    <form:input path="id" placeholder="${resUserDto.id}" disabled="true"/> <br>

    <label>Firstname</label>
    <form:input path="firstName" placeholder="${resUserDto.firstName}"/> <br>

    <label>Lastname</label>
    <form:input path="lastName" placeholder="${resUserDto.lastName}"/> <br>

    <label>Email</label>
    <form:input path="emailId" placeholder="${resUserDto.emailId}"/><br>

    <label>Date of birth</label>
    <form:input path="dateOfBirth" placeholder="${resUserDto.dateOfBirth}"/><br>

    <label>Password</label>
    <form:input path="password" placeholder="${resUserDto.password}"/><br>

    <label>Role</label>
    <form:input path="role" placeholder="${resUserDto.role}"/><br>

    <label>Location</label>
    <form:select path="locationName">
      <form:option value="${resUserDto.locationName}"/>
      <jsp:useBean id="locationDtoList" scope="request" type="java.util.List"/>
      <form:options items="${locationDtoList}"/>
    </form:select>

    <label>Profile Photo</label>
    <form:input path="profileImagePath" placeholder="${resUserDto.profileImagePath}"/><br>

    <button type="submit" onclick="location='/users/${resUserDto.id}'" formmethod="post" style="background-color: greenyellow">Submit</button>
    <button type="button" onclick="location='/users/'" formmethod="get">Back</button>
  </form:form>
</body>
</html>
