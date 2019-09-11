package pl.coderslab.dao;

import pl.coderslab.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String SAVE_TO_DB = "INSERT INTO users(username, email, password, user_group_id) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE users SET username = ?, email = ?, password = ?, user_group_id = ? WHERE id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";
    private static final String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String GET_ALL_USERS = "SELECT * FROM users";
    private static final String GET_ALL_USERS_BY_GROUP_ID = "SELECT * FROM users WHERE user_group_id = ?";

    public static void saveToDB(Connection conn, User user) throws SQLException {
        if(user.getId() == 0){
            PreparedStatement stmt = conn.prepareStatement(SAVE_TO_DB, Statement.RETURN_GENERATED_KEYS);
            RepetitiveFunction.setUserParamtoDB(stmt, user);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        }else{
            PreparedStatement stmt = conn.prepareStatement(UPDATE_USER);
            RepetitiveFunction.setUserParamtoDB(stmt,user);
            stmt.executeUpdate();
        }

    }


    public static User getUserById(Connection connection, int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(GET_USER_BY_ID);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            int groupId = rs.getInt("user_group_id");
            int userId = rs.getInt("id");
            User user = new User(username, email, password, groupId);
            user.setId(userId);
            return user;
        }
        return null;
    }

    public static void delete(Connection conn, User user) throws SQLException {
        if (user.getId() != 0) {
            PreparedStatement preparedStatement = conn.prepareStatement(DELETE_USER);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
            user.setId(0);
        }
    }

    public static List<User> loadAllUsers(Connection conn) throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(GET_ALL_USERS);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            users.add(RepetitiveFunction.getUserParamFromDB(resultSet)); //my function
        }
        return users;
    }

    public static List<User> loadAllByGroupId(Connection conn, int groupId) throws SQLException{
        List<User> users = new ArrayList<>();
        PreparedStatement statement = conn.prepareStatement(GET_ALL_USERS_BY_GROUP_ID);
        statement.setInt(1, groupId);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            users.add(RepetitiveFunction.getUserParamFromDB(resultSet)); //my function
        }
        return users;
    }

}
