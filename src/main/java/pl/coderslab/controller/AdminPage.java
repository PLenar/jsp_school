package pl.coderslab.controller;

import pl.coderslab.codingschool.dao.GroupDao;
import pl.coderslab.database.DbUtil;
import pl.coderslab.model.Group;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin")
public class AdminPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DbUtil.getConnection();
            List<Group> groups = GroupDao.getAllGroups(connection);
            request.setAttribute("groups", groups);
            request.getServletContext().getRequestDispatcher("/view/admin.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}