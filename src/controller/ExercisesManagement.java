package controller;

import model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/exercises_management")
public class ExercisesManagement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String title = request.getParameter("title");
            String description = request.getParameter("description");

            if(request.getParameter("pageName").equals("add")) {
                // Add new exercise
                new Exercise(title, description).saveToDB();
            } else if(request.getParameter("pageName").equals("edit")) {
                // Edit exercise
                int id = Integer.parseInt(request.getParameter("id"));
                Exercise.loadExerciseById(id).setTitle(title).setDescription(description).saveToDB();
            }

            doGet(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Exercise[] exercises = Exercise.loadAll();
            request.setAttribute("exercises", exercises);
            getServletContext().getRequestDispatcher("/WEB-INF/exercises_management.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
