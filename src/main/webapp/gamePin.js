let pin;
let min = 1;
let max = 1000000;
let sessionsSet = new Set();
startGame();
// showPin
let pinStr = document.querySelector(".pinSection-Pin");
let opacityPin = document.querySelector(".pinSection-Pin");

function startGame() {
  createPin();
  setTimeout(showPin, 3500);
  //showPin();
  setTimeout("showPin()", 4000);
}

function createPin() {
  pin = Math.floor(Math.random() * (max - min)) + min;
  sessionsSet.add(pin);
}

function showPin() {
  opacityPin.style.opacity = 1;
  pinStr.innerHTML = pin;
}
