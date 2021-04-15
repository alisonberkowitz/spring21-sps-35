package com.google.sps.servlets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@WebServlet("/form-playerName-handler")  
public class PlayerNameHandler extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    long timestamp = System.currentTimeMillis();
    String playerName = Jsoup.clean(request.getParameter("gameNickname-input"), Whitelist.none());

    // Print the value so you can see it in the server logs.
    System.out.println("The PlayerName is: " + playerName);

    // Write the value to the response so the user can see it.
    //response.getWriter().println("The PlayerName is: " + playerName);

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Player");
    FullEntity nameEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("playerName", playerName)
            .set("timestamp", timestamp)
            .build();
    datastore.put(nameEntity);
     response.sendRedirect("/student.html");
  }
}