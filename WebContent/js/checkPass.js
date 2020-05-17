function checkSubmit() {
    if(document.getElementById('psw').value ==
            document.getElementById('psw-repeat').value) {
    	document.getElementById('psw').style.color='green';
    	document.getElementById('psw-repeat').style.color='green';
    } else {
    	document.getElementById('psw').style.color='red';
    	document.getElementById('psw-repeat').style.color='red';
    	event.preventDefault();
    }
}

function checkDateSubmit() {
    if(Date.parse(document.getElementById('CheckInInput').value) <
    		Date.parse(document.getElementById('CheckOutInput').value)) {
    	document.getElementById('CheckInInput').style.color='green';
    	document.getElementById('CheckOutInput').style.color='green';
    } else {
    	document.getElementById('CheckInInput').style.color='red';
    	document.getElementById('CheckOutInput').style.color='red';
    	event.preventDefault();
    }
}