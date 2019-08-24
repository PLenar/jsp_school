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

@WebServlet("/editGroup")
public class EditGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DbUtil.getConnection();
            String newNname = request.getParameter("newName");
            int id = Integer.parseInt(request.getParameter("groupId"));

            Group group = GroupDao.getGroupById(connection,id);
            String oldName = group.getName();
            group.setName(newNname);
            GroupDao.saveToDB(connection, group);

            response.getWriter().println("Edytowano nazwę grupy z: <b>" + oldName + "</b> na <b>" + group.getName() + "</b>");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DbUtil.getConnection();
            int id = Integer.parseInt(request.getParameter("id"));
            Group userGroup = GroupDao.getGroupById(connection, id);
            request.setAttribute("group", userGroup);

            request.getServletContext().getRequestDispatcher("/view/editGroup.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }


    }
}