// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

let questionNum = 1;
var userChoice = 0;
var correctAnswer = 3;
var totalQuestions = 5;
var pin = 234567;
//var pinText = "";
var score = 0;
var userName = "Test";

function loadUsers() {
  fetch('/list-users').then(response => response.text()).then((questions) => {
    const userListElement = document.getElementById('users-list');
    questions.forEach((question) => {
      userListElement.appendChild(createUserElement(question));
    })
  });
}

/** Creates an element that represents a user */
function createUserElement(question) {
  const questionElement = document.createElement('li');
  questionElement.className = 'question';

  const nameElement = document.createElement('span');
  nameElement.innerText = question.name;    
  
  questionElement.appendChild(nameElement);
  return questionElement;
}

async function retrieveData() {
     
  console.log("AAAAA");
  const timeLog = await fetch('/list-users');
  console.log("Time log object: " + timeLog);
  
  console.log("Inner text");
  const innerText = await timeLog.question;
  console.log(innerText);
  
}

function loadValues () {
  //pin = localStorage.getItem('quizzPinNumber_scriptTeacher');
  document.getElementById("pinValue").innerHTML = "Pin: " + pin;
  score = localStorage.getItem('score');
  document.getElementById("score").innerHTML = "Score: " + score;
}

function loadStudent() {
    var delay = 3000;

    setTimeout(() => {  
      
      window.location.href = "student.html";
      
    }, delay);
}

async function revealAnswer() {
    userChoice = localStorage.getItem('userChoice');
    console.log(userChoice);
    setTimeout(() => {  
      
      if (userChoice == correctAnswer) {
          score = score + 700;
          localStorage.setItem('score', score);
          window.location.href = "studentright.html";
      } else {
          window.location.href = "studentwrong.html";
      }

    }, 2000);
}

function nextQuestion(answerChosen) {
    setTimeout(() => {  
      
    userChoice = answerChosen;
    
    switch(answerChosen) {
        case 1:
          console.log("You chose red");
          break;
        case 2:
          console.log("You chose blue");
          break;
        case 3:
          console.log("You chose yellow");
          break;
        case 4:
          console.log("You chose green");
          break;
        default:
          break;
    }
    //window.location.href = "student.html";
    localStorage.setItem('userChoice', userChoice);
    //localStorage.setItem('questionNum', ++questionNum);
    //document.getElementById('questionNum').innerHTML = questionNum + " of " + totalQuestions;
    window.location.href = "studentloading.html";

    }, 500)
}
