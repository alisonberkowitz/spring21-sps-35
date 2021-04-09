
function createQuestions(){
  console.log(document.getElementById("idNumQue").value)
  var numberQuestions = document.getElementById("idNumQue").value;
  var elementForm = document.createElement("form");
  elementForm.setAttribute("id","idForm");
  elementForm.setAttribute("method","POST");
  elementForm.setAttribute("action","/form-handler");
  
  var elementContainer = document.getElementById("idQuestions");
  
  for(let i = 0; i < numberQuestions; i++){
    var inputQuestion = document.createElement("input");
    inputQuestion.setAttribute("type", "text");
    inputQuestion.setAttribute("name", `question${i+1}`);
    var h2ElementQuestion = document.createElement("h2");
    h2ElementQuestion.appendChild(document.createTextNode(`Please write question number ${i+1}`))
    elementForm.appendChild(h2ElementQuestion);
    elementForm.appendChild(inputQuestion);
    for(let j = 0 ; j < 4; j++){
      var pElementAnswer = document.createElement("p");
      if(j == 0){
        pElementAnswer.appendChild(document.createTextNode(`Plese write the correct answer`));
      }else{
        pElementAnswer.appendChild(document.createTextNode(`Plese write an incorrect answer`));
      }
      elementForm.appendChild(pElementAnswer);
      var inputAnswer = document.createElement("input");
      inputAnswer.setAttribute("type", "text");
      inputAnswer.setAttribute("name", `answer${i+1}${j+1}`);
      elementForm.appendChild(inputAnswer);
    }
  }
  elementContainer.appendChild(elementForm);
  var button = document.createElement('input');
  button.setAttribute('type', 'submit');
  button.setAttribute("class", "botonSubmit");
  button.setAttribute("id", "idbotonSubmit");
  button.setAttribute("value", "Play");
  elementForm.appendChild(button);
  
    let quizzId;
    let min = 1;
    let max = 1000000;
    let quizzIdSet = new Set();
    quizzId = Math.floor(Math.random() * (max - min)) + min;
    quizzIdSet.add(quizzId);
    window.localStorage.setItem('quizzPinNumber_scriptTeacher', quizzId);


  /* button.onclick = () => {
    var inputs = document.getElementById("idForm").elements;
    for(let i = 0 ; i < 5; i++){
      console.log(inputs[i].value);
    }
  }   */
}