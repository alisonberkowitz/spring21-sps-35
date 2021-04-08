let sessPinSection_Pin = document.querySelector(".sessPinSection-Pin");

function loadSessPin(quizzId) {
    sessPin= SessPin.query(SessPin.quizzId == quizzId).get();
    sessPinSection_Pin.innerHTML = sessPin;
}