let sessPin;
let min = 1;
let max = 1000000;

let sessPinSection_Loading = document.querySelector(".sessPinSection-Loading");
let sessPinSection_Pin = document.querySelector(".sessPinSection-Pin");
let hiddenPin = document.querySelector("#hiddenPin");
let hiddenQuizzId = document.querySelector("#hiddenQuizzId");
startGame();


function startGame() {
    createSessPin();
    getQuizzId();
    setTimeout("showSessPin()", 2500);
}

function createSessPin() {
    sessPin = Math.floor(Math.random() * (max - min)) + min;
}

function showSessPin() {
  sessPinSection_Pin.innerHTML = sessPin;
  sessPinSection_Loading.style.opacity = 0;
  hiddenPin.value = sessPin;
}

function getQuizzId(){
    let quizzId = JSON.parse(window.localStorage.getItem('quizzPinNumber_scriptTeacher'));
    console.log("quizzId: " + quizzId);
    hiddenQuizzId.value = quizzId;
    window.localStorage.removeItem('quizzPinNumber_scriptTeacher');
}


function loadSessPin() {

}