<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<title>account-manager</title>
<link rel="stylesheet" type="text/css" href="style/mainRooms.css">
<link rel="stylesheet" type="text/css" href="style/loginMainRooms.css">
<link rel="stylesheet" type="text/css" href="style/tabs-manager.css">
<script src="js/timer.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script>
google.charts.load('current', {packages: ['corechart', 'bar']});
google.charts.setOnLoadCallback('drawMultSeries');
</script>

</head>
<body onload="drawMultSeries();">

	<div class="mainAccount">
		<div class="refreshBut">
			<a href="controller?command=account"><img
				src="images/refresh.png" alt="main logo"></a>
		</div>
		<div class="roleEmblem">
			<h1>Manager</h1>
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
					for="tab_3">Settings</label> <input type="radio" name="inset"
					value="" id="tab_4"> <label for="tab_4">Statistics</label>
					<input type="radio" name="inset"
					value="" id="tab_5"> <label for="tab_5">Statistics2</label>

				<div id="txt_1">
					<table class="statTable">
						<tr>
							<th>Select</th>
							<th>Statement<br>id
							</th>
							<th>user<br>name
							</th>
							<th>phone</th>
							<th>room<br>type
							</th>
							<th>places</th>
							<th>dates</th>
						</tr>
						<c:forEach var="stat" items="${sessionScope.statementsList}">
							<c:if test="${stat.status eq 0}">
								<tr>
									<td rowspan="2"><c:if
											test="${sessionScope.statementId eq stat.id}">
											<form id="statement-rooms-choose${stat.id}"
												action="controller" method="post"></form>

											<select name="roomChooseStat"
												form="statement-rooms-choose${stat.id}">
												<c:forEach var="room" items="${sessionScope.niceRoomList}">
													<option value="${room.id}">room number:
														"${room.id}", price: "${room.price}"</option>
												</c:forEach>
												<c:if test="${empty sessionScope.niceRoomList}">
													<option value="0">No rooms available</option>
												</c:if>
											</select>
											<input form="statement-rooms-choose${stat.id}" type="hidden"
												name="statement-id" value="${stat.id}" />
											<input form="statement-rooms-choose${stat.id}" type="hidden"
												name="command" value="send-book-request" />
											<input form="statement-rooms-choose${stat.id}" type="submit"
												value="send">
										</c:if> <c:if test="${sessionScope.statementId ne stat.id}">
											<a
												href="controller?command=get-rooms&statement-id-get=${stat.id}">Select
												rooms</a>
										</c:if></td>
									<td rowspan="2">"${stat.id}"</td>
									<td rowspan="2">"${stat.user.fullName}"</td>
									<td rowspan="2">"${stat.user.phoneNumber}"</td>
									<td rowspan="2">"${stat.roomType}"</td>
									<td rowspan="2">"${stat.placesNum}"</td>
									<td class="statTableDate">"${stat.residenceStart}"</td>
								</tr>
								<tr>
									<td class="statTableDate">"${stat.residenceEnd}"</td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
				<div id="txt_2">
					<table class="statTable">
						<tr>
							<th>Room</th>
							<th>User name</th>
							<th>User phone</th>
							<th>Accommodation<br>fee
							</th>
							<th>Booking start</th>
							<th>Booking end</th>
							<th>Time for payment</th>
						</tr>

						<c:forEach var="booking" items="${sessionScope.bookingList}">
							<tr>
								<td>${booking.room.id }</td>
								<td>${booking.user.fullName}</td>
								<td>${booking.user.phoneNumber}</td>
								<td>${((booking.bookingEnd.time - booking.bookingStart.time)/initParam['dayMil'])*booking.accomodationFee}</td>
								<td>${booking.bookingStart }</td>
								<td>${booking.bookingEnd }</td>
								<td><c:if test="${booking.status eq 0}">
										<p id="bookingDate">
											<script>timeToEnd(${booking.bookingDate.time},${initParam['paymentTime']})</script>
										</p>
									</c:if>
									<p id="paidBooking">
										<c:if test="${booking.status eq 1}">
							PAID
							</c:if>
									</p></td>
							</tr>
						</c:forEach>

					</table>
				</div>
				<div id="txt_3">
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
				<div id="txt_4">

					<script>
					
function drawMultSeries() {
      var data = google.visualization.arrayToDataTable([
    	  
    	  ['Room', 'Booked'],
    	  <c:forEach var="room" items="${sessionScope.roomStatistics}">
        ['${room.key.id}', ${room.value}],
        
        </c:forEach>
      ]);

      var options = {
        title: 'Number of rooms booking',
        chartArea: {width: '70%',},
        height: 400,
        hAxis: {
          title: 'Bookings number',
          minValue: 0
        },
        vAxis: {
          title: 'Room №'
        }
      };
      var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
      chart.draw(data, options);
      
    }
  </script>
					<div id="chart_div"></div>
				</div >
				
				<div id="txt_5">
				<script>
					
function drawMultSeries() {
      var data = google.visualization.arrayToDataTable([
    	  
    	  ['Room', 'Money'],
    	  <c:forEach var="room" items="${sessionScope.roomStatistics2}">
        ['${room.key.id}', ${room.value}],
        
        </c:forEach>
      ]);

      var options = {
        title: 'Rooms earned money',
        chartArea: {width: '70%',},
        height: 400,
        hAxis: {
          title: 'Money',
          minValue: 0
        },
        vAxis: {
          title: 'Room №'
        }
      };
      var chart = new google.visualization.BarChart(document.getElementById('chart_div2'));
      chart.draw(data, options);
      
    }
  </script>
  <div id="chart_div2"></div>
				</div>
				
			</div>

		</div>

	</div>

</body>
</html>