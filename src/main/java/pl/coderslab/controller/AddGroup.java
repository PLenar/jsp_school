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

@WebServlet("/addGroup")
public class AddGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String groupName = request.getParameter("nameOfGroup");
        if(groupName != null){
            try {
                Connection connection = DbUtil.getConnection();
                Group group = new Group(groupName);
                GroupDao.saveToDB(connection, group);

                response.getWriter().println("<h3>Dodano grupę</h3>");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/view/addGroup.jsp").forward(request, response);
    }
}