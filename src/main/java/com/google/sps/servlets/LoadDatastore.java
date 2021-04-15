package com.google.sps.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import com.google.sps.data.Question;
import com.google.sps.data.Quiz;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/form-handler")
public class LoadDatastore extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    

    for(String name: request.getParameterMap().keySet()){
        String key = name;
        String value = Jsoup.clean(request.getParameter(name), Whitelist.none());
        System.out.println(key + " " + value);
    }
    
    // Get the value entered in the form.
    long timestamp = System.currentTimeMillis();
    int questionId = 123456789;
    int quizId = 123;

    List<String> questionValueString = new ArrayList<>();
    List<String> answerValueString = new ArrayList<>();

    List<List<String>> questionAndAnswers = new ArrayList<List<String>>();

    // Add for loop make declaration on top (outside for loop)
    List<Question> questionComponents = new ArrayList<>();
    List<Quiz> quizComponents = new ArrayList<>();

    System.out.println("hello line 49");
    for(int i = 0; i < 20; i++){
        questionValueString.add( Jsoup.clean(request.getParameter("question" + i + 1), Whitelist.none()));
        for(int j = 0; j < 4; j++){

            answerValueString.add( Jsoup.clean(request.getParameter("answer" + j + 1), Whitelist.none()));
            System.out.println("Hello line 55");
        }
    }

    for(int i = 0; i < questionValueString.size(); i++){
        questionComponents.add(new Question(questionId, questionValueString.get(i), answerValueString.get(4*i+0), answerValueString.get(4*i+1),answerValueString.get(4*i+2), answerValueString.get(4*i+3), timestamp)); 
    }

    quizComponents.add(new Quiz(quizId, questionComponents, timestamp));

    //String questionValueString = Jsoup.clean(request.getParameter("question0"), Whitelist.none());
    //String correctAnswerValueString = Jsoup.clean(request.getParameter("answer0"), Whitelist.none());
    //String answerTwoValueString = Jsoup.clean(request.getParameter("answer1"), Whitelist.none());
    //String answerThreeValueString = Jsoup.clean(request.getParameter("answer2"), Whitelist.none());
    //String answerOneValueString = Jsoup.clean(request.getParameter("answer3"), Whitelist.none());
      

    // Print the value so you can see it in the server logs.
    System.out.println("Hello line 73");

    IntStream.range(0, questionValueString.size())
        .forEach(question -> System.out.println("Question " + question + questionValueString.get(question)));
    
    IntStream.range(0, questionValueString.size())
        .forEach(answer -> System.out.println("Answer " + answer + questionValueString.get(answer)));
        
    /*System.out.println("Answer 1: " + answerValueString.get(1));
    System.out.println("Answer 2: " + answerValueString.get(2));
    System.out.println("Answer 3: " + answerValueString.get(3));
    System.out.println("Correct Answer: " + answerValueString.get(0));
    System.out.println("Question components: " + questionComponents);

    System.out.println("Quiz components: " + quizComponents);*/

    // Write the value to the response so the user can see it.
    response.getWriter().println("Question: " + questionValueString.get(0));
    response.getWriter().println("Answer 1: " + answerValueString.get(1));
    response.getWriter().println("Answer 2: " + answerValueString.get(2));
    response.getWriter().println("Answer 3: " + answerValueString.get(3));
    response.getWriter().println("Correct Answer: " + answerValueString.get(0));
    response.getWriter().println("Question components: " + questionComponents);

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    KeyFactory questionkeyFactory = datastore.newKeyFactory().setKind("Question");
    FullEntity questionEntity =
        Entity.newBuilder(questionkeyFactory.newKey())
            .set("question", questionValueString.get(0))
            .set("answerOne", answerValueString.get(1))
            .set("answerTwo", answerValueString.get(2))
            .set("answerThree", answerValueString.get(3))
            .set("correctAnswer", answerValueString.get(0))
            .set("timestamp", timestamp)
            .build();
    datastore.put(questionEntity);
    response.sendRedirect("/index.html");

    /*KeyFactory quizkeyFactory = datastore.newKeyFactory().setKind("Quiz");
    FullEntity quizEntity =
        Entity.newBuilder(quizkeyFactory.newKey())
            .set("quiz", quizId)
            .set("answerOne", answerOneValueString)
            .set("answerTwo", answerTwoValueString)
            .set("answerThree", answerThreeValueString)
            .set("correctAnswer", correctAnswerValueString)
            .set("timestamp", timestamp)
            .build();
    datastore.put(quizEntity);
    response.sendRedirect("/index.html");*/
  }
}
