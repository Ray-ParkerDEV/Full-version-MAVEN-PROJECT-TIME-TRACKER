
// var countSecond = parseInt(${tracking.differenceTime/1000});
if (countSecond > 0 && countSecond < 9) {
    document.getElementById("seconds").innerHTML = "0" + countSecond;
} else if (countSecond == 0){
    document.getElementById("seconds").innerHTML = "00";
} else {
    document.getElementById("seconds").innerHTML = countSecond;
}


// var countSecond = 0;
// document.getElementById("seconds").innerHTML = "00";
var timeSeconds = setInterval(function () {
    myTimerSec()
}, 1000);

function myTimerSec() {
    if (countSecond > 59) {
        countSecond = -1;
    }
    if (countSecond < 9) {
        countSecond = countSecond + 1;
        document.getElementById("seconds").innerHTML = "0" + countSecond;
    } else {
        countSecond = countSecond + 1;
        document.getElementById("seconds").innerHTML = countSecond;
    }
}

// var countMinutes = 0;
document.getElementById("minutes").innerHTML = "00";
var timeMinutes = setInterval(function () {
    myTimerMin()
}, 1000 * 60);

function myTimerMin() {
    if (countMinutes == 59) {
        countMinutes = -1;
    }
    if (countMinutes < 9) {
        countMinutes = countMinutes + 1;
        document.getElementById("minutes").innerHTML = "0" + countMinutes;
    } else {
        countMinutes = countMinutes + 1;
        document.getElementById("minutes").innerHTML = countMinutes;
    }
}

// var countHours = 0;
document.getElementById("hours").innerHTML = "00";
var timeHours = setInterval(function () {
    myTimerHour()
}, 1000 * 60 * 60);

function myTimerHour() {
    if (countHours == 59) {
        countHours = -1;
    }
    if (countHours < 9) {
        countHours = countHours + 1;
        document.getElementById("hours").innerHTML = "0" + countHours;
    } else {
        countHours = countHours + 1;
        document.getElementById("hours").innerHTML = countHours;
    }
}