package controller;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/users_management")
public class UsersManagement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            int user_group_id = Integer.parseInt(request.getParameter("user_group_id"));

            if(request.getParameter("pageName").equals("add")) {
                // Add new user
                new User(username, email, password, user_group_id).saveToDB();
            } else if(request.getParameter("pageName").equals("edit")) {
                // Edit user
                int id = Integer.parseInt(request.getParameter("id"));
                User.loadUserById(id)
                        .setUsername(username)
                        .setEmail(email)
                        .setPassword(password)
                        .setUser_group_id(user_group_id)
                        .saveToDB();
            }

            doGet(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User[] users = User.loadAll();
            request.setAttribute("users", users);
            getServletContext().getRequestDispatcher("/WEB-INF/users_management.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
