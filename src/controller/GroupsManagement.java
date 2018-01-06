package controller;

import model.Group;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/groups_management")
public class GroupsManagement extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");

            if(request.getParameter("pageName").equals("add")) {
                // Add new group
                new Group(name).saveToDB();
            } else if(request.getParameter("pageName").equals("edit")) {
                // Edit group
                int id = Integer.parseInt(request.getParameter("id"));
                Group.loadGroupById(id).setName(name).saveToDB();
            }

            doGet(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Group[] groups = Group.loadAll();
            request.setAttribute("groups", groups);
            getServletContext().getRequestDispatcher("/WEB-INF/groups_management.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
