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
    <th>StatusText</th>
    <th>CreatedDate</th>
    <th>Location</th>
    <th>Privacy</th>
    <th>User</th>
    <th>AttachmentPath</th>
    <th>LikedByUser</th>
    <th>Actions</th>
  </tr>
</thead>
  <tbody>
  <jsp:useBean id="responseStatusDtoList" scope="request" type="java.util.List"/>
  <c:forEach items="${responseStatusDtoList}" var="status">
    <tr>
      <td>${status.id}</td>
      <td>${status.statusText}</td>
      <td>${status.createdDate}</td>
      <td>${status.locationName}</td>
      <td>${status.privacy}</td>
      <td>${status.userName}</td>
      <td>${status.attachmentPathList}</td>
      <td>${status.likedByUser}</td>
      <td><section>
        <button type="button" onclick="location='/statuses/${status.id}'" style="background-color: yellow">Update</button>
        <button type="button" onclick="location='/statuses/deactivate/${status.id}'" style="background-color: darkred">Delete</button>
      </section>
      </td>
    </tr>
  </c:forEach>
  </tbody>


</table>

</body>
</html>
