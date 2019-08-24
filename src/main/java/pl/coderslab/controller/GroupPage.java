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

@WebServlet("/groupPage")
public class GroupPage extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DbUtil.getConnection();
            List<Group> userGroups = GroupDao.getAllGroups(connection);
            request.setAttribute("userGroups",userGroups);

            request.getServletContext().getRequestDispatcher("/view/groups.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}