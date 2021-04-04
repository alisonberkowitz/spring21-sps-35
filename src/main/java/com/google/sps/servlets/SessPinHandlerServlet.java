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

@WebServlet("/form-sessPin-handler")  
public class SessPinHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    long timestamp = System.currentTimeMillis();
    String sessPin = Jsoup.clean(request.getParameter("sessPinNumber"), Whitelist.none());

    // Print the value so you can see it in the server logs.
    System.out.println("The SessPin is: " + sessPin);

    // Write the value to the response so the user can see it.
    response.getWriter().println("The sessPin is: " + sessPin);

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

    KeyFactory keyFactory = datastore.newKeyFactory().setKind("SessPin");
    FullEntity pinEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("sessPin", sessPin)
            .set("timestamp", timestamp)
            .build();
    datastore.put(pinEntity);

  }
}