
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
      <div class="w3-top">
            <div class="w3-bar w3-border w3-large w3-indigo" >
                <a href="/" class="w3-bar-item w3-button" style="margin-right: auto;"><i class="fa fa-home"></i></a>
    
                <a href="#" class="w3-bar-item w3-button" style="margin-left: 45%"><i class="fa fa-search"></i></a>
    
                <a href="/users/${responseUserDto.id}" class="w3-bar-item w3-button w3-margin-right"><i class="fa fa-user"></i></a>
                <a href="#" class="w3-bar-item w3-button w3-margin-right"><i class="fa fa-globe"></i></a>

                <sec:authorize access="!isAuthenticated()">
                    <%--                            <a class="btn btn-secondary" href="/auth/login">Login</a>--%>
                    <a href="/auth/login" class="w3-button w3-bar-item w3-blue w3-right" ><i class="fa fa-sign-in"></i></a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <!--                Username showing on homepage-->
                    <%--                    <sec:authentication property="username"/>--%>
<%--                    current session info: ${user.email}--%>
                    <a href="/auth/logout" class="w3-button w3-bar-item w3-red w3-large w3-right"><i class="fa fa-sign-in"></i></a>
                    <%--                            <a class="btn btn-danger" href="/auth/logout">Logout</a>--%>
                </sec:authorize>
            </div>
      </div>
</body>
</html>