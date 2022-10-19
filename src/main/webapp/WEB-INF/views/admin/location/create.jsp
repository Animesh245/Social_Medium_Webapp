<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: warrior245
  Date: 6/5/22
  Time: 10:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Location</title>
</head>
<body>
<%--<%@include file="../../navbar.jsp" %>--%>


<%--@elvariable id="requestLocationDto" type="com.animesh245.social_medium.dto.request.RequestLocationDto"--%>
<form:form action="${pageContext.request.contextPath}/locations/" method="post" modelAttribute="requestLocationDto">
    <br>
    <div class="form-floating mb-3">
    <form:input type="text" class="form-control" id="floatingInput" placeholder="Location" path="locationName"/>
    <label for="floatingInput">Location</label>
</div>
<input class="btn btn-outline-dark" type="submit" value="Submit">
</form:form>

</body>
</html>
