package com.example.devs.Servlets;

import com.example.devs.Entity.Enums.Status;
import com.example.devs.Entity.Task;
import com.example.devs.Repository.TaskRepository;
import com.example.devs.Service.TaskService;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("DevSyncPU");
    TaskRepository userRepository = new TaskRepository(emf);
    TaskService userService = new TaskService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            createTask(request, response);
        }else if ("edit".equals(action)) {
            updateTask(request, response);
        }
    }

    private void createTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String desc = request.getParameter("desc");
        String dueDateStr = request.getParameter("dueDate");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        Task newUser = new Task(title,desc, Status.EN_COURS,dueDate,startDate,endDate,false);
        userService.createTask(newUser);
        response.sendRedirect(request.getContextPath() + "/users?action=list");
    }

    private void updateTask(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long taskId = Long.parseLong(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String statusStr = request.getParameter("status");
        String dueDateStr = request.getParameter("dueDate");
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        boolean isCompleted = Boolean.parseBoolean(request.getParameter("isCompleted"));

        // Convert date strings to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dueDate = LocalDate.parse(dueDateStr, formatter);
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        // Convert status string to Status enum
        Status status = Status.valueOf(statusStr);

        // Create updated Task object
        Task updatedTask = null;
        updatedTask.setTitle(title);
        updatedTask.setDescription(description);
        updatedTask.setStatus(status);
        updatedTask.setDueDate(dueDate);
        updatedTask.setStartDate(startDate);
        updatedTask.setEndDate(endDate);
        updatedTask.setCompleted(isCompleted);

        // Call the service to update the task
        userService.updateTask(updatedTask,taskId);
    }
}
