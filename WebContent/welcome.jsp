<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spl" uri="WEB-INF/custom.tld"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<title>MyHotel</title>
<link rel="stylesheet" type="text/css" href="style/carousel.css">
<link rel="stylesheet" type="text/css" href="style/main.css">
<link rel="stylesheet" type="text/css" href="style/loginMain.css">
<script src="js/carousel.js"></script>
<script src="js/checkPass.js"></script>
</head>
<body onload="triger();">

	<div class="slideshow-container">
		<div class="mainLogo">
			<a href="http://localhost:8080/FinalProject-Eclipse-MySQL-Hrybeniuk/">
				<img src="images/mainLogo.jpg" alt="main logo">
			</a>
		</div>
		<div class="mainMenu">

			<a
				href="http://localhost:8080/FinalProject-Eclipse-MySQL-Hrybeniuk/welcome.jsp">Home</a>
			<a href="controller?command=rooms_path&rooms_sort_type=id">Rooms</a>
			<c:if test="${empty sessionScope.userId}">
				<a class="unselectable"
					onclick="document.getElementById('modalDivId').style.display='block'">Sign
					up</a>
				<a class="unselectable"
					onclick="document.getElementById('modalDivId2').style.display='block'">Log
					in</a>
			</c:if>
			<c:if test="${not empty sessionScope.userId}">
				<a href="controller?command=account">My account</a>
			</c:if>
		</div>
		<c:forEach varStatus="x" begin="1"
			end="${initParam['picNumCarousel']}">

			<div class="mySlides fade">
				<img src="images/carouselPic${x.count}.jpg" style="width: 100%">

			</div>
			<div class="carouselText">
				<p class="unselectable">Welcome to the Continental !</p>
			</div>
		</c:forEach>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="create-statement" />
			<div class="bookingMenuDown">
				<div class="bookingMenuDownLabelDiv">
					<label>Booking:</label>
				</div>
				<div class="checkInDiv">
					<label>Check in:</label><br> <input id="CheckInInput"
						type="date" name="checkInDate"
						value="<spl:Date value="nowDate" parameter = "0"/>"
						min="<spl:Date value="minBookingDaysMil" parameter = "259200000"/>"
						max="<spl:Date value="minBookingDaysMil" parameter = "2592000000"/>" >
				</div>
				<div class="checkOutDiv">
					<label>Check out:</label><br> <input id="CheckOutInput"
						type="date" name="checkOutDate"
						value="<spl:Date value="nowDate" parameter = "0"/>"
						min="<spl:Date value="minBookingDaysMil" parameter = "259200000"/>"
						max="<spl:Date value="minBookingDaysMil" parameter = "2592000000"/>" >
				</div>
				<div class="bookingTypeRoomDiv">
					<label>Type of room:</label><br> <select name="roomType">
						<option selected value="standart">standart</option>
						<option value="apartment">apartment</option>
						<option value="studio">studio</option>
						<option value="family_room">family room</option>
						<option value="family_studio">family studio</option>
						<option value="delux">delux</option>
						<option value="suite">suite</option>
						<option value="royal_suite">royal suite</option>
					</select>
				</div>
				<div class="bookingPlaceNumDiv">
					<label>Number of places:</label><br> <input type="number"
						name="bookingPlaceNum" value="1" min="1" max="5">
				</div>
				<c:if
					test="${not empty sessionScope.userId and sessionScope.role eq 'Client'}">
					<div class="submitBookingDiv">
						<input onclick="checkDateSubmit()" type="submit" value="Book">
					</div>
				</c:if>
				<c:if
					test="${empty sessionScope.userId or sessionScope.role ne 'Client'}">
					<div class="submitBookingDiv">
						<button id="submitBookingDisabled"
							onclick="document.getElementById('modalDivId2').style.display='block'">Book</button>
					</div>
				</c:if>
			</div>
		</form>


		<div id="modalDivId" class="modal">
			<span
				onclick="document.getElementById('modalDivId').style.display='none'"
				class="close" title="Close Modal">&times;</span>
			<form class="modal-content" action="controller" method="post">
				<input type="hidden" name="command" value="sign-up" />
				<div class="container">
					<h3>Sign up</h3>
					<p>Please fill in this form to create an account.</p>
					<hr>
					<label for="Full name"><b>Full name</b></label> <input type="text"
						placeholder="Enter Full name" name="fullName"  required> <label
						for="email"><b>Email</b></label> <input type="text"
						placeholder="Enter Email" name="email"
						pattern="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
						required> <label for="phone"><b>Phone</b></label> <input
						type="tel" placeholder="Enter Phone" name="phone"
						pattern="^\s*(?:\+?(\d{1,3}))?([-. (]*(\d{3})[-. )]*)?((\d{3})[-. ]*(\d{2,4})(?:[-.x ]*(\d{1,5}))?)\s*$">
					<label for="psw"><b>Password</b></label> <input id="psw"
						type="password" placeholder="Enter Password" name="psw"
						pattern="^\w{6,28}$" required> <label for="psw-repeat"><b>Repeat
							Password</b></label> <input id="psw-repeat" type="password"
						placeholder="Repeat Password" name="psw-repeat"
						pattern="^\w{6,28}$" required><span id='message'></span>

					<div class="clearfix">
						<button type="button"
							onclick="document.getElementById('modalDivId').style.display='none'"
							class="cancelbtn">Cancel</button>
						<button onclick="checkSubmit()" id="signButSub" type="submit"
							class="signupbtn">Sign Up</button>
					</div>
				</div>
			</form>
		</div>

		<div id="modalDivId2" class="modal">
			<span
				onclick="document.getElementById('modalDivId2').style.display='none'"
				class="close" title="Close Modal">&times;</span>
			<form class="modal-content" action="controller" method="post">
				<input type="hidden" name="command" value="login" />
				<div class="container">
					<h1>Login</h1>
					<p>Please fill in this form to login.</p>
					<hr>
					<label for="email"><b>Email</b></label> <input type="text"
						placeholder="Enter Email" name="email"
						pattern="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
						required> <label for="psw"><b>Password</b></label> <input
						type="password" placeholder="Enter Password" name="psw"
						pattern="^\w{6,28}$" required>

					<div class="clearfix">
						<button type="button"
							onclick="document.getElementById('modalDivId2').style.display='none'"
							class="cancelbtn">Cancel</button>
						<button type="submit" class="signupbtn">Login</button>
					</div>
				</div>
			</form>
		</div>


	</div>
	<br>
</body>
</html>