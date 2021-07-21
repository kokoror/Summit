<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>Trails</title>
</head>
<body class="d-flex flex-column vh-100">

<nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"> Summit <i class="fas fa-mountain"></i> </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link" aria-current="page" href="trails">Home</a>
                <a class="nav-link" href="displayusers">Users</a>
                <a class="nav-link" href="trailcreate">New Trail</a>
            </div>
        </div>
    </div>
</nav>


<main class="container mt-5">
    <h1>Search User by username</h1>

    <form name="UserDisplay" action="${pageContext.request.contextPath}/displayusers" method="post">
        <input type="text" id="uname" name="uname" />
        <input type="submit" name="GetUser" value="Search User" />
    </form>

    <br>
    <br>

    <c:choose>
        <c:when test="${messages.disableSubmit=='true'}">
            <table border="1">
                <tr>
                    <th>Username</th>
                    <th>City</th>
                    <th>State</th>
                    <th>Country</th>
                    <th>Reviews</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>

                    <tr>

                        <td><c:out value="${user.getUsername()}" /></td>
                        <td><c:out value="${user.getCity()}" /></td>
                        <td><c:out value="${user.getState()}" /></td>
                        <td><c:out value="${user.getCountry()}" /></td>

                        <td><a href="userreviews?username=<c:out value="${user.getUsername()}"/>">Reviews</a></td>
                        <td><a href="userupdate?username=<c:out value="${user.getUsername()}"/>">Update</a></td>
                        <td><a href="userdelete?username=<c:out value="${user.getUsername()}"/>">Delete</a></td>
                    </tr>
            </table>
        </c:when>
        <c:when test="${messages.disableSubmit=='false'}">
            <div><h3>${messages.title}</h3></div>
        </c:when>
        <c:otherwise>

        </c:otherwise>
    </c:choose>

    <br>
    <br>

    <form name="CreateUser" action="UserCreate.jsp" >
        <input type="submit" name="GetUser" value="Create User" />
    </form>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>

</html>