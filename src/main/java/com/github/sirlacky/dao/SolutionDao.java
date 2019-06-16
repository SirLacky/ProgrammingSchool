package com.github.sirlacky.dao;

import com.github.sirlacky.model.Solution;
import com.github.sirlacky.service.ConnectionManager;
import java.sql.*;
import java.util.ArrayList;

public class SolutionDao {


    // CRUD (Create Read Update Delete)

    private static final String CREATE_SOLUTION_QUERY = "INSERT INTO solution(created, updated, description, exercise_id, users_id) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_SOLUTION_QUERY = "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY = "UPDATE solution SET updated = ?, description = ?, exercise_id = ?, users_id = ? where id = ?";
    private static final String DELETE_SOLUTION_QUERY = "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLUTION_QUERY = "SELECT * FROM solution";
    private static final String FIND_ALL_SOLUTIONS_BY_USER_ID_QUERY = "SELECT solution.id, created, updated, description FROM solution JOIN users ON users.id=solution.users_id WHERE users.id=?";
    private static final String FIND_ALL_SOLUTIONS_AND_SORT_BY_CREATED_QUERY = "SELECT solution.id, created, updated, solution.description FROM solution JOIN exercise ON exercise.id=solution.exercise_id WHERE exercise.id=? ORDER BY created DESC";

    // Coresponding Methods

    public Solution sortSolutionsByCreated (int excerciseId){
        try (Connection conn = ConnectionManager.getConnection()){
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_AND_SORT_BY_CREATED_QUERY);
            statement.setInt(1,excerciseId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Solution solution = new Solution();
                solution.setId(resultSet.getLong("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                return solution;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Solution readByUserId (int userId){
        try (Connection conn = ConnectionManager.getConnection()){
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_BY_USER_ID_QUERY);
            statement.setInt(1,userId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Solution solution = new Solution();
                solution.setId(resultSet.getLong("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                return solution;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Solution create(Solution solution) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(CREATE_SOLUTION_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, solution.getCreated());
            statement.setDate(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setLong(4, solution.getExerciseId());
            statement.setLong(5, solution.getUsersId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                solution.setId(resultSet.getLong(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution readById(int solutionId) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getLong("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExerciseId(resultSet.getLong("exercise_id"));
                solution.setUsersId(resultSet.getLong("users_id"));
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(Solution solution) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_SOLUTION_QUERY);
            statement.setDate(1, solution.getUpdated());
            statement.setString(2, solution.getDescription());
            statement.setLong(3, solution.getExerciseId());
            statement.setLong(4, solution.getUsersId());
            statement.setLong(5, solution.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(int solutionId) {
        try (Connection conn = ConnectionManager.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_SOLUTION_QUERY);
            statement.setInt(1, solutionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Solution> findAll() {
        try (Connection conn = ConnectionManager.getConnection()) {
            ArrayList<Solution> solutionsList = new ArrayList<>();
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTION_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                solution.setId(resultSet.getLong("id"));
                solution.setCreated(resultSet.getDate("created"));
                solution.setUpdated(resultSet.getDate("updated"));
                solution.setDescription(resultSet.getString("description"));
                solution.setExerciseId(resultSet.getLong("exercise_id"));
                solution.setUsersId(resultSet.getLong("users_id"));
                solutionsList.add(solution);
            }
            return solutionsList;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
