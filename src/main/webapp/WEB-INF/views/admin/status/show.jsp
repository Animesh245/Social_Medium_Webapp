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

<%--@elvariable id="resUserDto" type="com.animesh245.social_medium.dto.response.ResponseUserDto"--%>
  <%--@elvariable id="requestUserDto" type="com.animesh245.social_medium.dto.request.RequestUserDto"--%>
  <form:form action="${pageContext.request.contextPath}/users/${resUserDto.id}" method="post" modelAttribute="requestUserDto" enctype="multipart/form-data">
    <label>Id</label>
    <input value="${resUserDto.id}" readonly="true"/><br>

    <label>Full name</label>
    <form:input path="fullName" value="${resUserDto.fullName}"/> <br>


    <label>Email</label>
    <form:input path="emailId" value="${resUserDto.emailId}"/><br>

    <label>Date of birth</label>
    <form:input type="date" path="dateOfBirth" value="${resUserDto.dateOfBirth}"/><br>

    <label>Password</label>
    <form:input path="password" value="${resUserDto.password}"/><br>

    <label>Role</label>
    <input path="role" value="${resUserDto.role}" readonly="true"/><br>

    <label>Location</label>
    <form:select path="locationName">
      <form:option value="${resUserDto.locationName}"/>
      <jsp:useBean id="locationDtoList" scope="request" type="java.util.List"/>
      <form:options items="${locationDtoList}"/><br>
    </form:select><br>

    <img src="/images/${resUserDto.profileImagePath}" width="100" height="100"/><br>

    <label>Profile Photo</label>
<%--    'name'- is very important as Dto is expecting a file with that name--%>
    <input type="file" name="profileImage" accept="image/*"/><br>


    <form:button type="submit" style="background-color: greenyellow">Submit</form:button>
    <button type="button" onclick="location='/users/'">Back</button>
  </form:form>
</body>
</html>
