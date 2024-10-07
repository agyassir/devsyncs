package com.example.devs.Servlets;
import com.example.devs.Service.TaskService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "",loadOnStartup = 1)
public class defaultServlet extends HttpServlet{
    private String message;

    public void init() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpUnit");
            message = "dima kokab";
        } catch (Exception e) {
            // Handle the exception
            message = "Error connecting to the database: " + e.getMessage();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
