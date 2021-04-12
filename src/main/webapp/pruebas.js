let button = document.querySelector(".quizzButton");

  
    let quizzId;
    let min = 1;
    let max = 1000000;
    let quizzIdSet = new Set();
    createquizzId();

    function createquizzId(){
        console.log("Si entra");
        
        quizzId = Math.floor(Math.random() * (max - min)) + min;
        quizzIdSet.add(quizzId);
        localStorage.setItem('quizzPinNumber_scriptTeacher', quizzId);
        console.log("quizzId: " + quizzId );
        
    }    
    
     button.onclick = () => {
        window.location.href = "prepareToJoin.html";
    } 

