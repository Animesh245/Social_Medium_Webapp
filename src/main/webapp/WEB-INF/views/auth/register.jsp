<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Signup</title>
</head>
<body>
    <div class="w3-container">
        <div id="id02" class="w3-container">
            <div class="w3-modal-content w3-card-6 w3-animate-zoom" style="max-width:20%;max-height: 80%;">
        
            <div class="w3-center"><br>
                <!-- <span onclick="document.getElementById('id02').style.display='none'" class="w3-button w3-text-black w3-large w3-display-topright" title="Close Modal"><i class="fa fa-close"></i></span> -->
                <!-- <img src="./wp3092250.jpg" alt="Avatar" style="width:30%" class="w3-circle w3-margin-top"> -->
                <label class=" w3-margin-top w3-text-black w3-large"><b>Register</b></label>
            </div>

            <%--@elvariable id="reqUserDto" type="com.animesh245.social_medium.dto.request.RequestUserDto"--%>
            <form:form class="w3-container w3-small" action="${pageContext.request.contextPath}/users/" modelAttribute="reqUserDto" method="post" enctype="multipart/form-data">
                <div class="w3-section w3-small">

                <label class=" w3-margin-top w3-text-black"><b>Name</b></label>
                <form:input path="fullName" class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter Name"  required=""/>

                <label class=" w3-margin-top w3-text-black"><b>Email ID</b></label>
                <form:input path="emailId" class="w3-input w3-border w3-margin-bottom" type="email" placeholder="Enter Email"  required=""/>



                <label class=" w3-margin-top w3-text-black"><b>Date of Birth</b></label>
                <form:input path="dateOfBirth" class="w3-input w3-border w3-margin-bottom" type="date" placeholder="Enter DOB"  required=""/>

                <label class=" w3-margin-top w3-text-black"><b>Location</b></label>
                    <form:select path="locationName" class="w3-input w3-border w3-margin-bottom">
                        <form:option value="Select Location"/>
                        <jsp:useBean id="locationDtoList" scope="request" type="java.util.List"/>
                        <form:options items="${locationDtoList}"/>
                    </form:select><br>

                <label class=" w3-margin-top w3-text-black"><b>Profile Photo</b></label>
                <input  class="w3-input  w3-margin-bottom" type="file" name="profileImage" accept="image/*"  required=""/>

                <label class=" w3-margin-top w3-text-black"><b>Password</b></label>
                <form:input path="password" id="passsword" class="w3-input w3-border w3-margin-bottom" type="password" placeholder="Enter Password" name="psw" required=""/>
                <input onclick="showPassword()" class="w3-check w3-margin-top" type="checkbox"><label class="w3-text-black w3-margin"> Show Password</label>
                <button class="w3-button w3-block w3-green w3-section w3-padding" type="submit">Register</button>
                </div>
            </form:form>
            
            <div class="w3-container w3-border-top w3-padding-16 w3-light-grey w3-small">
                <button id="shift" onclick="location.href = '/auth/login';" type="button" class="w3-button w3-gray">Login</button>
                <span class="w3-right w3-padding w3-hide-small">Forgot <a href="#">password?</a></span>
            </div>
            </div>
        </div>
    </div>
</body>

<script>
          function showPassword() 
          {
          let x = document.getElementById("passsword");

          if (x.type === "password") 
          {
              x.type = "text";
          } else 
          {
              x.type = "password";
          }
      }
</script>
</html>