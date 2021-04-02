let sessPin;
let min = 1;
let max = 1000000;
let sessionsSet = new Set();
startGame();
// showSessPin
//let sessPinSection_Loading = document.querySelector(".sessPinSection-Loading");
let sessPinSection_Pin = document.querySelector(".sessPinSection-Pin");
let hiddenPin = document.querySelector("#hiddenPin");


function startGame() {
  createSessPin();
  //showSessPin();
  setTimeout("showSessPin()", 3500);
}

function createSessPin() {
    sessPin = Math.floor(Math.random() * (max - min)) + min;
    sessionsSet.add(sessPin);
}

function showSessPin() {
  sessPinSection_Pin.style.opacity = 1;
  sessPinSection_Pin.innerHTML = sessPin;
  hiddenPin.value = sessPin;
}



function loadSessPin() {

}