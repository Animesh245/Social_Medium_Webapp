<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Social Medium</title>
    <!-- Latest compiled and minified CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<!-- Latest compiled JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script> 
    $(function(){
      <%--$("#navbar").load(<jsp:include page="navbar.jsp"/>);--%>
    });
</script> 
<script> 
    $(function(){
      <%--$("#sidenav").load(<jsp:include page="sidenav.jsp"/>);--%>
    });
</script>

</head>
<body>
<jsp:include page="navbar.jsp"/>
<jsp:include page="sidenav.jsp"/>
<%--<jsp:include page="mainSection.jsp"/>--%>
<%--    <div id="navbar"></div>--%>
<%--    <div id="sidenav"></div>  --%>
</body>
</html>