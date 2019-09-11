package pl.coderslab.dao;

import pl.coderslab.model.UserGroup;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {

    private static final String GET_GROUP_BY_ID = "SELECT name FROM user_groups WHERE id = ?";
    private static final String GET_ALL_GROUPS = "SELECT * FROM user_groups";
    private static final String DELETE_GROUP = "DELETE FROM user_groups WHERE id = ?";
    private static final String DELETE_GROUP1 = "DELETE FROM user_groups WHERE name = ?";
    private static final String SAVE_TO_DB = "INSERT INTO user_groups(name) VALUES (?)";
    private static final String UPDATE_GROUP = "UPDATE user_groups SET name = ? WHERE id = ?";

    public static void saveToDB(Connection conn, UserGroup userGroup) throws SQLException {
        if(userGroup.getId() == 0){
            PreparedStatement stmt = conn.prepareStatement(SAVE_TO_DB, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, userGroup.getName());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                userGroup.setId(rs.getInt(1));
            }
        }else{
            PreparedStatement stmt = conn.prepareStatement(UPDATE_GROUP);
            stmt.setString(1, userGroup.getName());
            stmt.setInt(2, userGroup.getId());
            stmt.executeUpdate();
        }
    }

    public static UserGroup getGroupById(Connection conn, int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement(GET_GROUP_BY_ID);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            String groupName = rs.getString("name");

            UserGroup userGroup = new UserGroup(groupName);
            userGroup.setId(id);
            return userGroup;
        }
        return null;
    }

    public static List<UserGroup> getAllGroups(Connection conn) throws SQLException {
        List<UserGroup> groups = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement(GET_ALL_GROUPS);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            String groupName = rs.getString("name");
            int groupId = rs.getInt("id");

            UserGroup userGroup = new UserGroup(groupName);
            userGroup.setId(groupId);
            groups.add(userGroup);
        }
        return groups;
    }

    public static void deleteGroup(Connection conn, UserGroup userGroup) throws SQLException{
        PreparedStatement stmt = conn.prepareStatement(DELETE_GROUP1);
        stmt.setString(1, userGroup.getName());
        stmt.executeUpdate();
        userGroup.setId(0);
    }
}

