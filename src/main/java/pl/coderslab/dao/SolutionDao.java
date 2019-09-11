package pl.coderslab.dao;

import pl.coderslab.model.MainPageSolution;
import pl.coderslab.model.Solution;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SolutionDao{

    private static final String GET_LAST_SOLUTIONS =
            "SELECT user_id, solutions.id AS id, excercise_id, excercises.title AS title, users.username AS author, solutions.created AS created " +
                    "FROM solutions " +
                    "INNER JOIN excercises ON solutions.excercise_id = excercises.id " +
                    "INNER JOIN users ON solutions.user_id = users.id " +
                    "ORDER BY solutions.created " +
                    "DESC LIMIT ?";
    private static final String GET_MAINPAGESOLUTIONS_BY_USER_ID = "SELECT user_id, solutions.id AS id, excercise_id, excercises.title AS title, users.username AS author, solutions.created AS created " +
            "FROM solutions " +
            "INNER JOIN excercises ON solutions.excercise_id = excercises.id " +
            "INNER JOIN users ON solutions.user_id = users.id " +
            "WHERE users.id = ? " +
            "ORDER BY solutions.created";
    private static final String GET_SOLUTION_BY_ID = "SELECT * FROM solutions WHERE id = ?";
    private static final String DELETE_SOLUTION = "DELETE FROM solutions WHERE excercise_id = ?";
    private static final String SAVE_TO_DB = "INSERT INTO solutions(created,updated,description,excercise_id,user_id) VALUES(?,?,?,?,?)";
    private static final String UPDATE_SOLUTION = "UPDATE solutions SET created=?, updated=?,description=?,excercise_id=?,user_id=? WHERE id=?";
    private static final String GET_ALL_SOLUTIONS_BY_USER_ID = "SELECT created, updated, description, excercise_id, user_id, id FROM solutions WHERE user_id = ?";
    private static final String GET_ALL_SOLUTIONS_BY_EXCERCISE_ID = "SELECT created, updated, description, excercise_id, user_id, id FROM solutions WHERE excercise_id = ?";

    public static void saveToDB(Connection conn, Solution solution) throws SQLException{
        if(solution.getId() == 0){
            PreparedStatement statement = conn.prepareStatement(SAVE_TO_DB, Statement.RETURN_GENERATED_KEYS);
            RepetitiveFunction.setSolutionParametersToDB(statement,solution); //my function
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                solution.setId(rs.getInt(1));
            }
        }else{
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION);
            RepetitiveFunction.setSolutionParametersToDB(statement, solution); //my function
            statement.executeUpdate();
        }
    }


    public static List<MainPageSolution> getLastSolutions(Connection conn, int limitOfSolutions) throws SQLException {
        PreparedStatement statement = conn.prepareStatement(GET_LAST_SOLUTIONS);
        statement.setInt(1, limitOfSolutions);
        ResultSet rs = statement.executeQuery();
        ArrayList<MainPageSolution> result = new ArrayList<>();

        while (rs.next()) {
            result.add(RepetitiveFunction.getMainPageSolutFromDB(rs)); // my function
        }
        return result;
    }

    public static List<MainPageSolution> getLastMainPageSolutionsByUserId(Connection conn, int userId) throws  SQLException{
        PreparedStatement statement = conn.prepareStatement(GET_MAINPAGESOLUTIONS_BY_USER_ID);
        statement.setInt(1, userId);
        ResultSet rs = statement.executeQuery();
        List<MainPageSolution> result = new ArrayList<>();

        while (rs.next()) {
            result.add(RepetitiveFunction.getMainPageSolutFromDB(rs)); // my function
        }
        return result;
    }

    //8
    public static Solution getSolutionById (Connection conn, int id) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement(GET_SOLUTION_BY_ID);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            int excerciseId = rs.getInt("excercise_id");
            int userId = rs.getInt("user_id");
            String description = rs.getString("description");
            Solution solution = new Solution(excerciseId, userId);
            solution.setDescription(description);
            solution.setId(id);
            return solution;
        }
        return null;
    }

    //9, 10, 11, 12
    public static void deleteSolutionByExcerciseID(Connection conn, Solution solution) throws SQLException {
        if(solution.getExcerciseId() != 0){
            PreparedStatement stmt = conn.prepareStatement(DELETE_SOLUTION);
            stmt.setInt(1, solution.getExcerciseId());
            stmt.executeUpdate();
            solution.setExcerciseId(0);
        }
    }

    public static List<Solution> loadAllByUserId(Connection conn, int user_id) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(GET_ALL_SOLUTIONS_BY_USER_ID);
        statement.setInt(1, user_id);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            solutions.add(RepetitiveFunction.getSolutionParametersFromDB(rs));
        }
        return solutions;
    }

    public static List<Solution> loadAllByExcerciseId(Connection conn, int excercise_id) throws SQLException {
        List<Solution> solutions = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(GET_ALL_SOLUTIONS_BY_EXCERCISE_ID);
        statement.setInt(1, excercise_id);
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            solutions.add(RepetitiveFunction.getSolutionParametersFromDB(rs));
        }
        Collections.sort(solutions);

        return  solutions;
    }
}