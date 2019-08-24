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

@WebServlet("/deleteGroup")
public class DeleteGroup extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            Connection connection = DbUtil.getConn();
            int id = Integer.parseInt(request.getParameter("groupId"));
            UserGroup userGroup = GroupDao.getGroupById(connection, id);
            String groupName = userGroup.getName();
            request.setAttribute("groupName", groupName);

            GroupDao.deleteGroup(connection, userGroup);
            request.getServletContext().getRequestDispatcher("/view/deleteGroup.jsp").forward(request,response);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Connection connection = DbUtil.getConn();
            int id = Integer.parseInt(request.getParameter("id"));
            UserGroup group = GroupDao.getGroupById(connection, id);
            request.setAttribute("group", group);
            String groupName = group.getName();
            request.setAttribute("groupName", groupName);
            //przekazuję osobno nazwę grupy, ponieważ potem gdy usuwam grupę chce nadal wyświetląć jej nazwę w pliku
            //deleteGroup.jsp, a dodatkowo (po usunięciu) napis "usunięto"

        } catch(NumberFormatException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getServletContext().getRequestDispatcher("/view/deleteGroup.jsp").forward(request,response);
    }
}