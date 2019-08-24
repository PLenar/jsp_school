package pl.coderslab.controller;

import pl.coderslab.codingschool.dao.GroupDao;
import pl.coderslab.codingschool.dao.UserDao;
import pl.coderslab.database.DbUtil;
import pl.coderslab.model.Group;
import pl.coderslab.model.MainPageSolution;
import pl.coderslab.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/userPage")
public class UserPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DbUtil.getConn();
            int id = Integer.parseInt(request.getParameter("id"));
            List<User> users = UserDao.loadAllByGroupId(connection, id);
            UserGroup group = GroupDao.getGroupById(connection, id);

            request.setAttribute("group", group);
            request.setAttribute("users", users);
            request.getServletContext().getRequestDispatcher("/view/users.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
