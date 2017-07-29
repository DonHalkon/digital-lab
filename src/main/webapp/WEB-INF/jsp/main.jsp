<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<!-- <head> -->

	<!-- Access the bootstrap Css like this,
		Spring boot will handle the resource mapping automcatically -->
	<!-- <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" /> -->

	<!--
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
	<!-- <c:url value="/css/main.css" var="jstlCss" /> -->
	<!-- <link href="${jstlCss}" rel="stylesheet" /> -->

<!-- </head> -->
<body>
	<nav class="navbar navbar-inverse">
		<div class="container">
<!-- 			<div class="navbar-header">
				<a class="navbar-brand" href="#">Spring Boot</a>
			</div> -->
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/reagents/add-new-reagent">add new reagent</a></li>
					<li class="active"><a href="/locations/add-new-location">add new location</a></li>
					<li class="active"><a href="/compounds/add-new-compound">add new compound</a></li>
					<!-- <li><a href="#about">About</a></li> -->
				</ul>
			</div>
		</div>
	</nav>

<!-- 
	<div class="container">

		<div class="starter-template">
			<h1>Spring Boot Web JSP Example</h1>
			<h2>Message: ${message}</h2>
		</div>

	</div>

	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
 -->


 <table style="width:100%">
 <tr>
 <th>id</th>
 <th>compound</th>
 <th>location</th>
 <th>receiptDate</th>
 <th>storageLife</th>
 <th>comments</th>
 </tr>
  <c:forEach var="reagent" items="${reagents}">  
    <tr>
    <th><c:out value="${reagent.id}"/></th>
    <th><c:out value="${reagent.compound.id}"/></th>
    <th><c:out value="${reagent.location.id}"/></th>
    <th><c:out value="${reagent.receiptDate}"/></th>
    <th><c:out value="${reagent.storageLife}"/></th>
    <th><c:out value="${reagent.comments}"/></th>
    </tr>
  </c:forEach>
</table>
</body>

</html>