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

@WebServlet("/form-handler")
public class PinHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    long timestamp = System.currentTimeMillis();
    String pin = Jsoup.clean(request.getParameter("pinNumber"), Whitelist.none());

    // Print the value so you can see it in the server logs.
    System.out.println("You submitted: " + pin);

    // Write the value to the response so the user can see it.
    response.getWriter().println("You submitted: " + pin);

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Pin");
    FullEntity pinEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", pin)
            .set("timestamp", timestamp)
            .build();
    datastore.put(pinEntity);
    response.sendRedirect("/homePage.html");
  }
}