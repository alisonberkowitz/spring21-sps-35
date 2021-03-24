let pin;
let min = 1;
let max = 1000000;
let sessionsSet = new Set();

// showPin
let pinStr = document.querySelector(".pinStr");

function createPin() {
  pin = Math.floor(Math.random() * (max - min)) + min;
  sessionsSet.add(pin);
  //showPin();
  setTimeout(showPin, 4000);
}

function showPin() {
  pinStr.innerHTML = pin;
}

createPin();
