<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.6.1.min.js" integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ=" crossorigin="anonymous"></script>
<%--    <script> --%>
<%--        $(function(){--%>
<%--          $("#MainSection").load('mainSection.jsp');--%>
<%--        });--%>
<%--    </script>--%>
    <title>Main page</title>


</head>

<!-- style="width:14%; height: max-content;left:12%;top: 36px; margin: 2%;position: fixed;" -->

<body>
  <div class="w3-container w3-row" style="display:flex;justify-content: center;">

    <div id="profile" class="w3-card w3-col m2 w3-bar-block w3-round w3-white" style=" height: max-content;left:12%;top: 36px; margin: 2%;position: sticky;margin-top:50px">
        <div class="w3-container w3-center">
            <img class="w3-circle" src="/images/${responseUserDto.profileImagePath}" alt="Alps" style=" width: 30%;border-radius: 50%;margin-top: 8%;">
          <p>${responseUserDto.fullName}</p>
          <hr>
          <p class="w3-center"><i class="fa fa-pencil fa-fw w3-margin-right w3-text-light-blue"></i>Actor, Hollywood</p>

          <p class="w3-center"><i class="fa fa-home fa-fw w3-margin-right w3-text-orange"></i>${responseUserDto.locationName}</p>

          <p class="w3-center"><i class="fa fa-birthday-cake fa-fw w3-margin-right w3-text-pink"></i>${responseUserDto.dateOfBirth}</p>
     </div>
        <hr>
        
        <a href="./profile.jsp" class="w3-bar-item w3-button w3-center"><i class="fa fa-fw fa-user w3-margin-right w3-text-blue w3-large"></i>Profile</a>
                <hr>
        <a href="#" class="w3-bar-item w3-button w3-center">
            <i class="fa fa-fw fa-image w3-margin-right w3-text-orange w3-large"></i>Photos</a> 
            <hr>
        <a href="#" class="w3-bar-item w3-button w3-center"><i class="fa fa-fw w3-margin-right w3-margin-bottom fa-users w3-text-blue-gray"></i>Groups</a>
    </div>

    

<%--        <div id="MainSection" class="w3-col m4"></div>--%>
<%--        <div id="MainSection" class="w3-col m4"></div>--%>
      <div class="w3-col m4">
          <jsp:include page="mainSection.jsp" />
      </div>



    <div class="w3-col m2 w3-card w3-light-gray " style="width:12%; height: fit-content;position:sticky; top: 36px; margin: 2%;margin-top:50px" >

        <div class="w3-container w3-center ">
          <h5>Friend Request</h5>
          <img src="./wp3092250.jpg" alt="Avatar" style="width:40%;"><h5>John Doe</h5>
    
          <div class="w3-row w3-opacity">
            <div class="w3-half">
            <button class="w3-button w3-block w3-green w3-section"><i class="fa fa-check"></i></button>
            </div>
            <div class="w3-half">
            <button class="w3-button w3-block w3-red w3-section" ><i class="fa fa-remove"></i></button>
          </div>
          </div>
        </div>
    
      </div> 

    <!-- <div class="w3-sidebar w3-bar-block w3-light-gray" style="width:20%; height: fit-content;right:0;top: 36px;margin: 2%;">
        
        <a href="#" class="w3-bar-item w3-button">Link 1</a>
        <a href="#" class="w3-bar-item w3-button">Link 2</a>
        <a href="#" class="w3-bar-item w3-button">Link 3</a>
    </div> -->
    </div>
</body>
</html>