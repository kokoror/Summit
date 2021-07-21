<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a Trail</title>
</head>
<body>
	<h1>Create Trail</h1>
	<form action="trailcreate" method="post">
		<p>
			<label for="trailId">TrailId</label>
			<input id="trailId" name="trailId" value="">
		</p>
		<p>
			<label for="trailName">TrailName</label>
			<input id="trailName" name="trailName" value="">
		</p>
		<p>
			<label for="area">Area</label>
			<input id="area" name="area" value="">
		</p>
		<p>
			<label for="city">City</label>
			<input id="city" name="city" value="">
		</p>
		<p>
			<label for="state">State</label>
			<input id="state" name="state" value="">
		</p>
		<p>
			<label for="country">Country</label>
			<input id="country" name="country" value="">
		</p>
		<p>
			<label for="latitude">Latitude</label>
			<input id="latitude" name="latitude" value="">
		</p>
		<p>
			<label for="longitude">Longitude</label>
			<input id="longitude" name="longitude" value="">
		</p>
		<p>
			<label for="elevationGain">ElevationGain</label>
			<input id="elevationGain" name="elevationGain" value="">
		</p>
		<p>
			<label for="level">Level</label>
			<input id="level" name="level" value="">
		</p>
		<p>
			<label for="type">Type</label>
			<input id="type" name="type" value="">
		</p>
		<p>
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>