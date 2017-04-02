function clock() {
var date = new Date()
var year = date.getYear()
var month = date.getMonth()
var day = date.getDate()
var hour = date.getHours()
var minute = date.getMinutes()
var second = date.getSeconds()
var months = new Array("", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC")
var monthname = months[month]
if (hour > 12) {hour = hour - 12}
if (minute < 10) {minute = "0" + minute}
if (second < 10) {second = "0" + second}
document.title = monthname + " " + day + ", " + year + " - " + hour + ":" + minute + ":" + second
setTimeout(clock, 1000)
}

var myVar=setInterval(function () {myTimer()}, 1000);
var counter = 0;
function myTimer() {
    var date = new Date();
    document.getElementById("demo").innerHTML = date.toISOString();
}

$(".dropdown-menu li a").click(function(){

	  $(this).parents(".btn-group").find('.selection').text($(this).text());
	  $(this).parents(".btn-group").find('.selection').val($(this).text());

	});