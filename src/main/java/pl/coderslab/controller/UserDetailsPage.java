package pl.coderslab.controller;

import pl.coderslab.codingschool.dao.GroupDao;
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

@WebServlet("/userDetailsPage")
public class UserDetailsPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DbUtil.getConnection();
            int userId = Integer.parseInt(request.getParameter("id"));
            User user = UserDao.getUserById(connection,userId);
            List<MainPageSolution> mainPageSolutions = SolutionDao.getLastMainPageSolutionsByUserId(connection,userId);

            request.setAttribute("user", user);
            request.setAttribute("mainPageSolutions",mainPageSolutions);
            request.getServletContext().getRequestDispatcher("/view/userDetails.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}