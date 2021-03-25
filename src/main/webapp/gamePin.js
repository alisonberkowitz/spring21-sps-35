let pin;
let min = 1;
let max = 1000000;
let sessionsSet = new Set();

// showPin
let pinStr = document.querySelector(".pinSection-Pin");

function createPin() {
  pin = Math.floor(Math.random() * (max - min)) + min;
  sessionsSet.add(pin);
  //showPin();
  setTimeout(showPin, 3500);
}

function showPin() {
  pinStr.innerHTML = pin;
}

createPin();
