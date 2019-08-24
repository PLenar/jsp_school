package pl.coderslab.controller;

import pl.coderslab.codingschool.dao.GroupDao;
import pl.coderslab.database.DbUtil;
import pl.coderslab.model.Group;
import pl.coderslab.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/excerciseSolution")
public class ExcerciseSolution extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Connection conn = DbUtil.getConnection();

            Solution solution = SolutionDao.getSolutionById(conn, id);

            request.setAttribute("solution",solution);
            getServletContext().getRequestDispatcher("/view/solutionDetails.jsp")
                    .forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
