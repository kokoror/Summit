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

		<div class="card mb-3 mx-auto" style="max-width: 660px;">
		  <div class="row g-0">
		    <div class="col-md-6">
		      <img src="https://www.istockphoto.com/photo/young-woman-sitting-by-window-wall-types-on-laptop-gm1245239346-363067796" class="img-fluid rounded-start" alt="...">
		    </div>
		    <div class="col-md-6">
		      <div class="card-body">
		        <h5 class="card-title"><c:out value="${user.getUsername()}"/></h5>
		        
				  <p class="card-text"><small class="text-muted"> City : <c:out value="${user.getCity()}" /> </small></p>
				  <p class="card-text"><small class="text-muted"> State :<c:out value="${user.getState()}" /></small></p>
				  <p class="card-text"><small class="text-muted"> Country :<c:out value="${user.getCountry()}" /> </small></p>
		      </div>
		    </div>
		  </div>
		</div>

        
        <h5>Reviews</h5>

        <c:forEach items="${reviews}" var="review" >
        	<%-- <p><c:out value="${review.getRating()}  --- ${review.getUser().getUsername()}" /> </p> --%>
        	<div class="card">
		  		<div class="card-body">
        			<p class="card-text"><c:out value="Rating: ${review.getRating()}" /> for <a href="traildetail?trailid=<c:out value="${review.getTrail().getTrailId()}"/>">${review.getTrail().getTrailName()}</a> </p>
        			<p class="card-text"><c:out value="${review.getContent()}  " /> </p>
        			<p class="card-text"><small class="text-muted">Created at <c:out value="${review.getCreated()}  " /></small> </p>
        		</div>
			</div>
        </c:forEach>
        
        

        
	</main>
	
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>

</html>