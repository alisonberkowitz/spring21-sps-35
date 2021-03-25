
function createQuestions(){
  console.log(document.getElementById("idNumQue").value)
  var numberQuestions = document.getElementById("idNumQue").value;
  var elementForm = document.createElement("form");
  elementForm.setAttribute("id","idForm");
  var elementContainer = document.getElementById("idQuestions");
  
  for(let i = 0; i < numberQuestions; i++){
    var inputQuestion = document.createElement("INPUT");
    inputQuestion.setAttribute("type", "text");
    inputQuestion.setAttribute("name", "question");
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
      inputAnswer.setAttribute("name", "answer");
      elementForm.appendChild(inputAnswer);
    }
  }
  elementContainer.appendChild(elementForm);
  var button = document.createElement('input');
  button.setAttribute('type', 'submit');
  elementContainer.appendChild(button);
  button.onclick = () => {
    var inputs = document.getElementById("idForm").elements;
    for(let i = 0 ; i < 5; i++){
      console.log(inputs[i].value);

    }
  }
  
  
  
}