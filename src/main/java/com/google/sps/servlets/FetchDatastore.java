package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.OrderBy;
import com.google.gson.Gson;
import com.google.sps.data.Question;
import com.google.sps.data.Quiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet responsible for listing tasks. */
@WebServlet("/list-users")
public class FetchDatastore extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    Query<Entity> query =
        Query.newEntityQueryBuilder().setKind("Question").setOrderBy(OrderBy.desc("timestamp")).build();
    QueryResults<Entity> results = datastore.run(query);

    List<Question> questions = new ArrayList<>();
    List<Quiz> quizzes = new ArrayList<>();

    while (results.hasNext()) {
      Entity entity = results.next();

      long id = entity.getKey().getId();
      long quizId = entity.getKey().getId();      
      String question = entity.getString("question");
      String answerOne = entity.getString("answerOne");
      String answerTwo = entity.getString("answerTwo");
      String answerThree = entity.getString("answerThree");
      String correctAnswer = entity.getString("correctAnswer");
      long timestamp = entity.getLong("timestamp");

      Question oneQuestion = new Question(id, question, answerOne, answerTwo, answerThree, correctAnswer, timestamp);
      questions.add(oneQuestion);

      Quiz oneQuiz = new Quiz(quizId, oneQuestion, timestamp);
      quizzes.add(oneQuiz);
    }

    Gson gson = new Gson();

    response.setContentType("application/json;");
    response.getWriter().println(gson.toJson(questions));
    response.getWriter().println(gson.toJson(quizzes));
  }
}
