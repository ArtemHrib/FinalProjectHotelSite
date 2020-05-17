function timeToEnd(dateTime, paymentTime) {
	var endDate = (dateTime + paymentTime);
	var nowDate = Date.parse(new Date());
	var endTime = endDate - nowDate;
	var daysToEnd = parseInt(endTime / 86400000 + "", 10);
	document.write(daysToEnd + " day(s), ");

	endTime = endTime - (daysToEnd * 86400000);
	var hoursToEnd = parseInt(endTime / 3600000 + "", 10);
	hoursToEnd = hoursToEnd >= 0 ? hoursToEnd : 0;

	endTime = endTime - (hoursToEnd * 3600000);
	var minutesToEnd = parseInt(endTime / 60000 + "", 10);
	minutesToEnd = minutesToEnd >= 0 ? minutesToEnd : 0;

	endTime = endTime - (minutesToEnd * 60000);
	var secondToEnd = parseInt(endTime / 1000 + "", 10);
	secondToEnd = secondToEnd >= 0 ? secondToEnd : 0;

	document.write(hoursToEnd + " hour(s), " + minutesToEnd
			+ " minute(s),<br> " + secondToEnd + " second(s), ");
}
