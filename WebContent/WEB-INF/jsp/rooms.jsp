<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spl" uri="../custom.tld"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
<head>
<title>Rooms</title>
<link rel="stylesheet" type="text/css" href="style/mainRooms.css">
<link rel="stylesheet" type="text/css" href="style/loginMainRooms.css">
<link rel="stylesheet" type="text/css" href="style/roomsBlock.css">
<script src="js/checkPass.js"></script>
</head>
<body>

	<div class="mainRooms">

		<div class="mainLogo">
			<a href="http://localhost:8080/FinalProject-Eclipse-MySQL-Hrybeniuk/">
				<img src="images/mainLogo.jpg" alt="main logo">
			</a>
		</div>
		<div class="mainMenu">
			<a
				href="http://localhost:8080/FinalProject-Eclipse-MySQL-Hrybeniuk/welcome.jsp">Home</a>
			<a href="#news">Rooms</a> <a href="#contact">Contact</a>
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


	</div>

	<div class="roomsBlock">
		<table class="roomsTable">

			<tr>
				<th></th>
				<th>number</th>
				<th>type</th>
				<th>places<br>number
				</th>
				<th>price</th>
				<th>status</th>
				<th></th>
			</tr>
			<tbody>
				<c:forEach var="room" items="${roomsList}">
					<tr>
						<td><img class="roomPic" src="${room.picturePath}" /></td>
						<td>${room.id}</td>
						<td>${room.type}</td>
						<td>${room.placesNum}</td>
						<td>${room.price}</td>
						<td>${room.status}</td>
						<td><c:if
								test="${room.status ne 'Inaccessible' and sessionScope.role ne 'Manager' and not empty userId}">
								<form id="select-room${room.id}" action="controller"
									method="post"></form>
								<input form="select-room${room.id}" type="hidden" name="roomId"
									value="${room.id}" />
								<input form="select-room${room.id}" type="hidden" name="command"
									value="select-room" /> Check in:<br>
								<input id="CheckInInput" form="select-room${room.id}"
									type="date" name="checkInRoom"
									value="<spl:Date value="nowDate" parameter = "0"/>"
									min="<spl:Date value="minBookingDaysMil" parameter = "259200000"/>"
									max="<spl:Date value="maxBookingDaysMil" parameter = "2592000000"/>">
								<br> Check out:<br>
								<input id="CheckOutInput" form="select-room${room.id}"
									type="date" name="checkOutRoom"
									value="<spl:Date value="nowDate" parameter = "0"/>"
									min="<spl:Date value="minBookingDaysMil" parameter = "259200000" />"
									max="<spl:Date value="maxBookingDaysMil" parameter = "2592000000"/>">
								<br>
								<input form="select-room${room.id}"
									type="submit" value="Book">
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="sorterDiv">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="rooms_path" /> <label>Sort
				rooms by:</label><br> <select class="sortTypeSelection"
				name="rooms_sort_type">
				<option value="id">number</option>
				<option value="price">price</option>
				<option value="places_number">places number</option>
				<option value="type">type</option>
				<option value="status">status</option>
			</select> <input type="submit" value="sort">
		</form>
	</div>
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
						placeholder="Enter Full name" name="fullName" required> <label
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
</body>
</html>