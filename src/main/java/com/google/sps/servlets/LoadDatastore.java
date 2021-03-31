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

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/form-handler")
public class LoadDatastore extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    long timestamp = System.currentTimeMillis();
    String questionValue = Jsoup.clean(request.getParameter("question-input"), Whitelist.none());
    String answerOneValue = Jsoup.clean(request.getParameter("answerOne-input"), Whitelist.none());
    String answerTwoValue = Jsoup.clean(request.getParameter("answerTwo-input"), Whitelist.none());
    String answerThreeValue = Jsoup.clean(request.getParameter("answerThree-input"), Whitelist.none());
    String correctAnswerValue = Jsoup.clean(request.getParameter("correctAnswer-input"), Whitelist.none());

    // Print the value so you can see it in the server logs.
    System.out.println("Question: " + questionValue);
    System.out.println("Answer 1: " + answerOneValue);
    System.out.println("Answer 2: " + answerTwoValue);
    System.out.println("Answer 3: " + answerThreeValue);
    System.out.println("Correct Answer: " + correctAnswerValue);

    // Write the value to the response so the user can see it.
    response.getWriter().println("Question: " + questionValue);
    response.getWriter().println("Answer 1: " + answerOneValue);
    response.getWriter().println("Answer 2: " + answerTwoValue);
    response.getWriter().println("Answer 3: " + answerThreeValue);
    response.getWriter().println("Correct Answer: " + correctAnswerValue);

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    KeyFactory keyFactory = datastore.newKeyFactory().setKind("User");
    FullEntity contactInfoEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("question", questionValue)
            .set("answerOne", answerOneValue)
            .set("answerTwo", answerTwoValue)
            .set("answerThree", answerThreeValue)
            .set("correctAnswer", correctAnswerValue)
            .set("timestamp", timestamp)
            .build();
    datastore.put(contactInfoEntity);
    response.sendRedirect("/index.html");
  }
}
