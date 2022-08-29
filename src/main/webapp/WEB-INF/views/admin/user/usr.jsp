<%@ taglib prefix="Form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: warrior245
  Date: 8/13/22
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Form</title>
</head>
<body>
<%--@elvariable id="requestUserDto" type="com.animesh245.social_medium.dto.request.RequestUserDto"--%>
        <Form:form action="${pageContext.request.contextPath}/users/" modelAttribute="requestUserDto" method="post" enctype="multipart/form-data">
            <label>Full name</label>
            <Form:input path="fullName"/><br>

            <label>Email</label>
            <Form:input path="emailId"/><br>

            <label>Date of Birth</label>
            <form:input path="dateOfBirth" type="date"/><br>

            <label>Password</label>
            <Form:input path="password" type="password"/><br>

            <label>Location</label>
            <Form:select path="locationName">
                <Form:option value="Select Location"/>
                <jsp:useBean id="locationDtoList" scope="request" type="java.util.List"/>
                <Form:options items="${locationDtoList}"/>
            </Form:select><br>

            <label>Profile Photo</label>
            <input type="file" name="profileImage" accept="image/*" /><br>

            <Form:button type="submit">Submit</Form:button>
        </Form:form>
</body>
</html>
