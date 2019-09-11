package pl.coderslab.dao;

import pl.coderslab.model.Excercise;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDao {

    private static final String SAVE_TO_DB = "INSERT INTO excercises(title, description) VALUES(?, ?)";
    private static final String UPDATE_EXCERCISE = "UPDATE excercises SET title = ?, description = ? WHERE id = ?";
    private static final String DELETE_EXCERCISE = "DELETE FROM excercises WHERE id = ?";
    private static final String GET_EXCERCISE_BY_ID = "SELECT * FROM excercises WHERE id = ?";
    private static final String GET_ALL_EXCERCISES = "SELECT * FROM excercises";

    public static void saveToDB(Connection conn, Excercise excercise) throws SQLException {
        if (excercise.getId() == 0) {
            PreparedStatement stmt = conn.prepareStatement(SAVE_TO_DB, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, excercise.getTitle());
            stmt.setString(2, excercise.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                excercise.setId(rs.getInt(1));
            }
        } else {
            PreparedStatement stmt = conn.prepareStatement(UPDATE_EXCERCISE);
            stmt.setString(1, excercise.getTitle());
            stmt.setString(2, excercise.getDescription());
            stmt.setInt(3, excercise.getId());
            stmt.executeUpdate();
        }
    }

    public static Excercise getExcerciseById(Connection conn, int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(GET_EXCERCISE_BY_ID);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String title = rs.getString("title");
            String description = rs.getString("description");

            Excercise excercise = new Excercise(title, description);
            excercise.setId(id);
            return excercise;
        }
        return null;
    }

    public static List<Excercise> getAllExcercises(Connection conn) throws SQLException {
        List<Excercise> excercises = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement(GET_ALL_EXCERCISES);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String title = rs.getString("title");
            String description = rs.getString("description");
            int id = rs.getInt("id");

            Excercise excercise = new Excercise(title, description);
            excercise.setId(id);
            excercises.add(excercise);
        }
        return excercises;
    }

    public static void deleteExcercise(Connection conn, Excercise excercise) throws SQLException {
        if (excercise.getId() != 0) {
            PreparedStatement stmt = conn.prepareStatement(DELETE_EXCERCISE);
            stmt.setInt(1, excercise.getId());
            stmt.executeUpdate();
            excercise.setId(0);
        }
    }

}
