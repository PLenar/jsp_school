package pl.coderslab.dao;

import pl.coderslab.model.MainPageSolution;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class RepetitiveFunction {

    public static Solution getSolutionParametersFromDB(ResultSet rs) throws SQLException {
        int idOfSolution = rs.getInt("id");
        int excerciseId = rs.getInt("excercise_id");
        int userId = rs.getInt("user_id");
        Date createdDateOfSolution = rs.getDate("created");
//        Date updateddDateOfSolution = rs.getDate("updated");
        String descriptionOfSolution = rs.getString("description");

        //5 builder
        Solution solution = new Solution(excerciseId,userId);

        solution.setCreated(createdDateOfSolution);
//        solution.setUpdated(updateddDateOfSolution);
        solution.setDescription(descriptionOfSolution);
        solution.setId(idOfSolution);

        return solution;
    }

    public static void setSolutionParametersToDB(PreparedStatement statement, Solution solution) throws SQLException{
        statement.setDate(1, (java.sql.Date) solution.getCreated());
        statement.setDate(2, (java.sql.Date) solution.getUpdated());
        statement.setString(3, solution.getDescription());
        statement.setInt(4, solution.getExcerciseId());
        statement.setInt(5, solution.getUserId());
    }

    public static MainPageSolution getMainPageSolutFromDB(ResultSet rs) throws SQLException {
        int idOfSolution = rs.getInt("id");
        int excerciseId = rs.getInt("excercise_id");
        int userId = rs.getInt("user_id");
        Date createdDateOfSolution = rs.getDate("created");
        String solutionTitle = rs.getString("title");
        String solutionAuthor = rs.getString("author");
        //5 builder
        MainPageSolution mpsolution = new MainPageSolution(solutionTitle, solutionAuthor, excerciseId, userId);
        mpsolution.setCreated(createdDateOfSolution);
        mpsolution.setId(idOfSolution);

        return mpsolution;
    }

    public static User getUserParamFromDB(ResultSet resultSet) throws SQLException {
        User loadedUser = new User();
        loadedUser.setId(resultSet.getInt("id"));
        loadedUser.setUsername(resultSet.getString("username"));
        loadedUser.setPassword(resultSet.getString("password"));
        loadedUser.setEmail(resultSet.getString("email"));
        loadedUser.setGroupId(resultSet.getInt("user_group_id"));

        return loadedUser;
    }

    public static void setUserParamtoDB(PreparedStatement statement, User user) throws SQLException {
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getEmail());
        statement.setString(3, user.getPassword());
        statement.setInt(4, user.getGroupId());
        statement.setInt(5, user.getId());
    }

}