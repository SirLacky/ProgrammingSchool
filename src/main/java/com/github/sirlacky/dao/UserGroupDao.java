package com.github.sirlacky.dao;

import com.github.sirlacky.model.UserGroup;
import com.github.sirlacky.service.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;

public class UserGroupDao {

    // CRUD (Create Read Update Delete)

    private static final String CREATE_USERGROUP_QUERY = "INSERT INTO user_group(name) VALUES (?)";
    private static final String READ_USERGROUP_QUERY = "SELECT * FROM user_group where id = ?";
    private static final String UPDATE_USERGROUP_QUERY = "UPDATE user_group SET name = ?";
    private static final String DELETE_USERGROUP_QUERY = "DELETE FROM user_group WHERE id = ?";
    private static final String FIND_ALL_USERGROUP_QUERY = "SELECT * FROM user_group";

    // Coresponding Methods

    public UserGroup create(UserGroup userGroup) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_USERGROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userGroup.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                userGroup.setId(resultSet.getLong(1));
            }
            return userGroup;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserGroup readById(int userGroupId) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USERGROUP_QUERY);
            statement.setInt(1, userGroupId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.setId(resultSet.getLong("id"));
                userGroup.setName(resultSet.getString("name"));
                return userGroup;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(UserGroup userGroup) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USERGROUP_QUERY);
            statement.setString(1, userGroup.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int userGroupId) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USERGROUP_QUERY);
            statement.setInt(1, userGroupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<UserGroup> findAll() {
        try (Connection conn = ConnectionManager.getConnection()) {
            ArrayList<UserGroup> userGroups = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USERGROUP_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.setId(resultSet.getLong("id"));
                userGroup.setName(resultSet.getString("name"));
                userGroups.add(userGroup);
            }
            return userGroups;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
