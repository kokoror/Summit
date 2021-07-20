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
	          <a class="nav-link" href="users">Users</a>
	          <a class="nav-link" href="trailcreate">New Trail</a>
	        </div>
	      </div>
	    </div>
    </nav>
	
	
	<main class="container mt-5">
		<h1>${messages.title}</h1>
		<form action="traildelete?trailid=${trail.getTrailId()}" method="post">
			<p>
				<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
					<p>TrailName: <c:out value="${trail.getTrailName()}" /></p>
					<p>Are you sure you want to delete this trail?</p>
				</div>
			</p>
			<p>
				<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
					<input type="submit">
				</span>
			</p>
		</form>
		<br/><br/>
        
	</main>
	
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>

</html>