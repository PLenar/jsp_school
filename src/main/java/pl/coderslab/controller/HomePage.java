package pl.coderslab.controller;


import pl.coderslab.dao.SolutionDao;
import pl.coderslab.database.DbUtil;
import pl.coderslab.model.MainPageSolution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
public class HomePage extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int numberOfSolutions = Integer.parseInt(getServletContext().getInitParameter("number_of_solutions"));
        try{
            Connection conn = DbUtil.getConnection();
            List<MainPageSolution> solutions = SolutionDao.getLastSolutions(conn, numberOfSolutions);


            request.setAttribute("solutions", solutions);
            getServletContext().getRequestDispatcher("/jsp/index.jsp")
                    .forward(request, response);

        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}