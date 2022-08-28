<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="Form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <!-- <div class="w3-col m4" style="margin-top: 4%;">
        <div class="w3-row-padding">
            <div class="w3-col m12" style="margin-left: 50%; width: 76%;"> -->
    <div class="w3-card w3-round w3-white" style="margin-top:50px">
        <div class="w3-container w3-padding" >
            <jsp:include page="../admin/status/create.jsp"/>
            <%--@elvariable id="requestStatusDto" type="com.animesh245.social_medium.dto.request.RequestStatusDto"--%>
<%--            <Form:form action="${pageContext.request.contextPath}/statuses/" modelAttribute="requestStatusDto" method="post" enctype="multipart/form-data">--%>
<%--                <h6 class="w3-opacity">How was your day</h6>--%>
<%--                <Form:input path="statusText"/><br>--%>

<%--                <label>Privacy</label>--%>
<%--                <Form:select path="privacy">--%>
<%--                    <Form:option value="Select Privacy"/>--%>
<%--                    <jsp:useBean id="privacyList" scope="request" type="java.util.List"/>--%>
<%--                    <Form:options items="${privacyList}"/>--%>
<%--                </Form:select>--%>

<%--                <br>--%>
<%--                <label>Location</label>--%>
<%--                <Form:select path="locationName">--%>
<%--                    <Form:option value="Select Location"/>--%>
<%--                    <jsp:useBean id="locationDtoList" scope="request" type="java.util.List"/>--%>
<%--                    <Form:options items="${locationDtoList}"/>--%>
<%--                </Form:select><br>--%>

<%--                <label>Attachments</label>--%>
<%--                <input type="file" name="fileList" accept="image/*" multiple="multiple"/><br>--%>

<%--                <Form:button type="submit"><i class="fa fa-pencil"></i> Post</Form:button>--%>
<%--            </Form:form>--%>

<%--            <button type="button" class="w3-button w3-green"><i class="fa fa-pencil"></i> &nbsp;Post</button>--%>
        </div>
    </div>


    <c:forEach items="${responseStatusDtoList}" var="status">
        <div class="w3-container w3-card w3-white w3-round "    style="margin-top: 3%;"><br>
            <img src="/images/${status.profileImagePath}" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:30px">
            <span class="w3-right w3-opacity">${status.createdDate}</span>
            <h4>${status.userName}</h4><br>
            <hr class="w3-clear">
            <p>${status.statusText}</p>
            <div class="w3-row-padding" style="margin:0 -16px">

                <c:forEach items="${status.attachmentPathList}" var="path">
                    <div class="w3-half">
                        <img src="/images/${path}" style="width:100%" alt="Northern Lights" class="w3-margin-bottom">
                    </div>
                </c:forEach>
            </div>
            <button type="button" class="w3-button w3-blue w3-margin-bottom"><i class="fa fa-thumbs-up"></i> &nbsp;Like</button>
            <button type="button" class="w3-button w3-red w3-margin-bottom"><i class="fa fa-comment"></i> &nbsp;Comment</button>
        </div>
    </c:forEach>


<%--                  <div class="w3-container w3-card w3-white w3-round "    style="margin-top: 3%;"><br>--%>
<%--                    <img src="./wp3092250.jpg" alt="Avatar" class="w3-left w3-circle w3-margin-right" style="width:30px">--%>
<%--                    <span class="w3-right w3-opacity">11 min</span>--%>
<%--                    <h4>John Doe</h4><br>--%>
<%--                    <hr class="w3-clear">--%>
<%--                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>--%>
<%--                      <div class="w3-row-padding" style="margin:0 -16px">--%>
<%--                        <div class="w3-half">--%>
<%--                          <img src="./download.jpeg" style="width:100%" alt="Northern Lights" class="w3-margin-bottom">--%>
<%--                        </div>--%>
<%--                        <div class="w3-half">--%>
<%--                          <img src="./download.jpeg" style="width:100%" alt="Nature" class="w3-margin-bottom">--%>
<%--                      </div>--%>
<%--                    </div>--%>
<%--                    <button type="button" class="w3-button w3-blue w3-margin-bottom"><i class="fa fa-thumbs-up"></i> &nbsp;Like</button> --%>
<%--                    <button type="button" class="w3-button w3-red w3-margin-bottom"><i class="fa fa-comment"></i> &nbsp;Comment</button> --%>
<%--              </div>--%>
            <!-- </div>
        </div>
        
    </div> -->
    
</body>
</html>