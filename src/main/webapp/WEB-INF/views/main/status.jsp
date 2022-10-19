<%@ taglib prefix="Form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="section" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: warrior245
  Date: 8/13/22
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%--@elvariable id="requestStatusDto" type="com.animesh245.social_medium.dto.request.RequestStatusDto"--%>
<Form:form action="${pageContext.request.contextPath}/statuses/" modelAttribute="requestStatusDto" method="post" enctype="multipart/form-data">
    <label>Write something</label>
    <Form:input path="statusText"/><br>

    <label>Privacy</label>
    <Form:select path="privacy">
        <Form:option value="Select Privacy"/>
        <jsp:useBean id="privacyList" scope="request" type="java.util.List"/>
        <Form:options items="${privacyList}"/>
    </Form:select>

    <br>
    <label>Location</label>
    <Form:select path="locationName">
        <Form:option value="Select Location"/>
        <jsp:useBean id="locationDtoList" scope="request" type="java.util.List"/>
        <Form:options items="${locationDtoList}"/>
    </Form:select><br>

    <label>Attachments</label>
    <input type="file" name="fileList" accept="image/*" multiple="multiple"/><br>

    <Form:button type="submit">Submit</Form:button>
</Form:form>
</body>
</html>