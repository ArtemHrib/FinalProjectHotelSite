<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>account-user</title>
<link rel="stylesheet" type="text/css" href="style/mainRooms.css">
<link rel="stylesheet" type="text/css" href="style/loginMainRooms.css">
<link rel="stylesheet" type="text/css" href="style/tabs-manager.css">
<script src="js/timer.js"></script>
</head>
<body>
	<div class="mainAccount">
		<div class="refreshBut">
			<a href="controller?command=account"><img
				src="images/refresh.png" alt="main logo"></a>
		</div>
		<div class="roleEmblem">
			<h1>User</h1>
			<h3>${userName}</h3>
		</div>
		<div class="mainLogo">
			<a href="http://localhost:8080/FinalProject-Eclipse-MySQL-Hrybeniuk/">
				<img src="images/mainLogo.jpg" alt="main logo">
			</a>
		</div>
		<div class="mainMenu">
			<a
				href="http://localhost:8080/FinalProject-Eclipse-MySQL-Hrybeniuk/welcome.jsp">Home</a>
			<a href="controller?command=rooms_path&rooms_sort_type=id">Rooms</a>
			<a href="#contact">Contact</a>
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

		<div class="profileMenuDiv">
			<div class="tabs">
				<input type="radio" name="inset" value="" id="tab_1" checked>
				<label for="tab_1">Statements</label> <input type="radio"
					name="inset" value="" id="tab_2"> <label for="tab_2">Booking</label>
				<input type="radio" name="inset" value="" id="tab_3"> <label
					for="tab_3">Bookings
					query(${sessionScope.BookingQuerryNumber })</label> <input type="radio"
					name="inset" value="" id="tab_4"> <label for="tab_4">Settings</label>

				<div id="txt_1">
					<table class="statTable">
						<tr>
							<th>Id</th>
							<th>Name</th>
							<th>Places</th>
							<th>Check in</th>
							<th>Check out</th>
							<th>status</th>
						</tr>
						<c:forEach var="statement" items="${statementList}">
							<tr>
								<td>${statement.id}</td>
								<td>${statement.user.fullName}</td>
								<td>${statement.placesNum }</td>
								<td>${statement.residenceStart}</td>
								<td>${statement.residenceEnd}</td>
								<c:if test="${statement.status eq 0}">
									<td>Waiting for manager</td>
								</c:if>
								<c:if test="${statement.status eq 1}">
									<td>Accepted</td>
								</c:if>
								<c:if test="${statement.status eq 2}">
									<td>No available rooms</td>
								</c:if>
							</tr>
						</c:forEach>
					</table>
				</div>
				<div id="txt_2">
					<table class="statTable">
						<tr>
							<th>Id</th>
							<th>Room</th>
							<th>User</th>
							<th>Accommodation<br>fee
							</th>
							<th>Check in</th>
							<th>Check out</th>
							<th>Time for payment</th>
							<th>Status</th>
						</tr>
						<c:forEach var="booking" items="${sessionScope.bookingList }">
							<c:if test="${ booking.status ne 2 and booking.special_status ne 1}">
								<tr>
									<td>${booking.id}</td>
									<td>${booking.room.id }</td>
									<td>${booking.user.fullName}</td>
									<td>${booking.accomodationFee}</td>
									<td>${booking.bookingStart}</td>
									<td>${booking.bookingEnd}</td>
									<td><c:if test="${ booking.status eq 0}">
											<script>timeToEnd(${booking.bookingDate.time},${initParam['paymentTime']})</script>
										</c:if></td>
									<td><c:if test="${ booking.status eq 0}">
											<form id="pay${booking.id}" action="controller" method="post">
												<input type="hidden" name="command" value="booking-user" />
												<input type="hidden" name="action" value="pay" /> <input
													type="hidden" name="bookingId" value="${booking.id}" /> <input
													type="submit" value="pay" name="pay">
											</form>
											<br>
											<form id="cansel${booking.id}" action="controller"
												method="post">
												<input type="hidden" name="command" value="booking-user" />
												<input type="hidden" name="action" value="cansel" /> <input
													type="hidden" name="bookingId" value="${booking.id}" /> <input
													type="submit" value="cansel" name="cansel">
											</form>
										</c:if> <c:if test="${ booking.status eq 1}">
									Paid
									</c:if></td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
				<div id="txt_3">
					<table class="statTable">
						<tr>
							<th>Id</th>
							<th>User</th>
							<th>Room</th>
							<th>Room type</th>
							<th>Price</th>
							<th>Places number</th>
							<th>Check in</th>
							<th>Check out</th>
							<th></th>
						</tr>
						<c:forEach var="bookingQuerry"
							items="${sessionScope.bookingQuerryList }">
							<c:if test="${bookingQuerry.status eq 0}">
								<tr>
									<td>${bookingQuerry.id}</td>
									<td>${bookingQuerry.statement.user.fullName}</td>
									<td>${bookingQuerry.room.id}</td>
									<td>${bookingQuerry.room.type}</td>
									<td>${((bookingQuerry.statement.residenceEnd.time - bookingQuerry.statement.residenceStart.time)/initParam['dayMil'])*bookingQuerry.room.price}</td>
									<td>${bookingQuerry.room.placesNum }</td>
									<td>${bookingQuerry.statement.residenceStart}</td>
									<td>${bookingQuerry.statement.residenceEnd}</td>
									<td><form action="controller" method="post">
											<input type="hidden" name="command"
												value="booking-query-user-acception" /> <input
												type="hidden" name="bookingQuerryId"
												value="${bookingQuerry.id}" /> <input type="submit"
												value="Book">
										</form></td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
				<div id="txt_4">
					<form id="settingsFormName" action="controller" method="post"></form>
					<input form="settingsFormName" type="hidden" name="command"
						value="settings" />

					<form id="settingsFormPass" action="controller" method="post"></form>
					<input form="settingsFormPass" type="hidden" name="command"
						value="settings" />

					<form id="settingsFormPhone" action="controller" method="post"></form>
					<input form="settingsFormPhone" type="hidden" name="command"
						value="settings" />

					<form id="settingsFormExit" action="controller" method="post"></form>
					<input form="settingsFormExit" type="hidden" name="command"
						value="settings" />
					<table class="statTable">
						<tr>
							<th>Change name</th>
							<td><input form="settingsFormName" type="text"
								placeholder="Enter new full name" name="fullName" required></td>
							<td></td>
							<td><input form="settingsFormName" type="submit"
								name="submit" value="Change Full name"></td>
						</tr>
						<tr>
							<th>Change password</th>
							<td><input form="settingsFormPass" type="password"
								placeholder="Old password" name="oldPassword" required></td>
							<td><input form="settingsFormPass" type="password"
								placeholder="New password" name="newPassword" required></td>
							<td><input form="settingsFormPass" type="submit"
								name="submit" value="Change password"></td>
						</tr>
						<tr>
							<th>Change phone</th>
							<td><input form="settingsFormPhone" type="tel"
								placeholder="Enter new phone" name="phone" required></td>
							<td></td>
							<td><input form="settingsFormPhone" type="submit"
								name="submit" value="Change phone"></td>
						</tr>
						<tr>
							<td><input form="settingsFormExit" type="submit"
								name="submit" value="Log out"></td>
						</tr>
					</table>
				</div>

			</div>

		</div>

	</div>
</body>
</html>