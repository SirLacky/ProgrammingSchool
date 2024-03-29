package com.github.sirlacky.dao;

import com.github.sirlacky.model.Exercise;
import com.github.sirlacky.model.Solution;
import com.github.sirlacky.service.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;

public class ExerciseDao {

    // CRUD (Create Read Update Delete)

    private static final String CREATE_EXERCISE_QUERY = "INSERT INTO exercise(title, description) VALUES (?, ?)";
    private static final String READ_EXERCISE_QUERY = "SELECT * FROM exercise where id = ?";
    private static final String UPDATE_EXERCISE_QUERY = "UPDATE exercise SET title = ?, description = ?";
    private static final String DELETE_EXERCISE_QUERY = "DELETE FROM exercise WHERE id = ?";
    private static final String FIND_ALL_EXERCISE_QUERY = "SELECT * FROM exercise";

    // Coresponding Methods

    public Exercise create(Exercise exercise) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_EXERCISE_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                exercise.setId(resultSet.getLong(1));
            }
            return exercise;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Exercise readById(int exerciseId) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_EXERCISE_QUERY);
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getLong("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(Exercise exercise) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_EXERCISE_QUERY);
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int exerciseId) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_EXERCISE_QUERY);
            statement.setInt(1, exerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Exercise> findAll() {
        try (Connection conn = ConnectionManager.getConnection()) {
            ArrayList<Exercise> exercisesList = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISE_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getLong("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                exercisesList.add(exercise);
            }
            return exercisesList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
