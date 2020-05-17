<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="style/carousel.css">
<link rel="stylesheet" type="text/css" href="style/error-page.css">
<title>Information page</title>
<script src="js/carousel.js"></script>
</head>
<body onload="triger();">

		<div class="errorDiv">
			<p>${requestScope.ErrorMessage }</p>
			<input class="errorHomeBut" type="button" value="Home" onclick="parent.location='welcome.jsp'" />	
		</div>

	<div class="slideshow-container">
	<c:forEach varStatus="x" begin="1"
			end="${initParam['picNumCarousel']}">

			<div class="mySlides fade">
				<img src="images/carouselPic${x.count}.jpg" style="width: 100%">

			</div>
			<div class="carouselText">
				<p class="unselectable">Welcome to the Continental !</p>
			</div>
		</c:forEach>
		
	</div>
	
</body>
</html>