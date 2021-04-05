package com.google.sps.servlets;

import java.io.IOException;

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

    // Get the value entered in the form.
    long timestamp = System.currentTimeMillis();
    int questionId = 123456789;
    int quizId = 123;     
    String questionValueString = Jsoup.clean(request.getParameter("question-input"), Whitelist.none());
    String answerOneValueString = Jsoup.clean(request.getParameter("answerOne-input"), Whitelist.none());
    String answerTwoValueString = Jsoup.clean(request.getParameter("answerTwo-input"), Whitelist.none());
    String answerThreeValueString = Jsoup.clean(request.getParameter("answerThree-input"), Whitelist.none());
    String correctAnswerValueString = Jsoup.clean(request.getParameter("correctAnswer-input"), Whitelist.none());

    Question questionComponents = new Question(questionId, questionValueString, answerOneValueString, answerTwoValueString, answerThreeValueString, correctAnswerValueString, timestamp);
    Quiz quizComponents = new Quiz(quizId, questionComponents, timestamp);

    // Print the value so you can see it in the server logs.
    System.out.println("Question: " + questionValueString);
    System.out.println("Answer 1: " + answerOneValueString);
    System.out.println("Answer 2: " + answerTwoValueString);
    System.out.println("Answer 3: " + answerThreeValueString);
    System.out.println("Correct Answer: " + correctAnswerValueString);
    System.out.println("Question components: " + questionComponents);

    System.out.println("Quiz components: " + quizComponents);    

    // Write the value to the response so the user can see it.
    response.getWriter().println("Question: " + questionValueString);
    response.getWriter().println("Answer 1: " + answerOneValueString);
    response.getWriter().println("Answer 2: " + answerTwoValueString);
    response.getWriter().println("Answer 3: " + answerThreeValueString);
    response.getWriter().println("Correct Answer: " + correctAnswerValueString);
    response.getWriter().println("Question components: " + questionComponents);

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    KeyFactory questionkeyFactory = datastore.newKeyFactory().setKind("Question");
    FullEntity questionEntity =
        Entity.newBuilder(questionkeyFactory.newKey())
            .set("question", questionValueString)
            .set("answerOne", answerOneValueString)
            .set("answerTwo", answerTwoValueString)
            .set("answerThree", answerThreeValueString)
            .set("correctAnswer", correctAnswerValueString)
            .set("timestamp", timestamp)
            .build();
    datastore.put(questionEntity);
    response.sendRedirect("/index.html");

    KeyFactory quizkeyFactory = datastore.newKeyFactory().setKind("Quiz");
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
    response.sendRedirect("/index.html");
  }
}
