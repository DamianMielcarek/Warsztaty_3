package model;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Solution {

    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private int id;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime updated = LocalDateTime.now();
    private String description;
    private int exercise_id;
    private int users_id;

    public Solution() {}

    public Solution(int exercise_id, int users_id) {
        this.exercise_id = exercise_id;
        this.users_id = users_id;
    }

    public int getId() {
        return id;
    }

    public Solution setId(int id) {
        this.id = id;
        return this;
    }

    public String getCreated() {
        String created = this.created.format(FORMATTER);
        return created;
    }

    public Solution setCreated(String str) {
        String[] split = str.split("\\.");
        String dateTime = split[0];
        LocalDateTime created = LocalDateTime.parse(dateTime, FORMATTER);
        this.created = created;
        return this;
    }

    public String getUpdated() {
        String updated = this.updated.format(FORMATTER);
        return updated;
    }

    public Solution setUpdated(String str) {
        String[] split = str.split("\\.");
        String dateTime = split[0];
        LocalDateTime updated = LocalDateTime.parse(dateTime, FORMATTER);
        this.updated = updated;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Solution setDescription(String description) {
        this.description = description;
        return this;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public Solution setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
        return this;
    }

    public int getUsers_id() {
        return users_id;
    }

    public Solution setUsers_id(int user_id) {
        this.users_id = user_id;
        return this;
    }

    static public Solution loadSolutionById(int id) throws SQLException {
        Connection conn = DbUtil.getConn();
        String sql = "SELECT * FROM Solution WHERE id = ?;";
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.setId(resultSet.getInt("id"));
            loadedSolution.setCreated(resultSet.getString("created"));
            loadedSolution.setUpdated(resultSet.getString("updated"));
            loadedSolution.setDescription(resultSet.getString("description"));
            loadedSolution.setExercise_id(resultSet.getInt("exercise_id"));
            loadedSolution.setUsers_id(resultSet.getInt("users_id"));
            return loadedSolution;
        }
        return null;
    }

    static public Solution[] loadAllByExerciseId(int exercise_id) throws SQLException {
        Connection conn = DbUtil.getConn();
        ArrayList<Solution> solutionsList = new ArrayList<>();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Solution WHERE exercise_id = ? ORDER BY updated DESC;");
        preparedStatement.setInt(1, exercise_id);
        ResultSet solution = preparedStatement.executeQuery();

        while (solution.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.setId(solution.getInt("id"));
            loadedSolution.setCreated(solution.getString("created"));
            loadedSolution.setUpdated(solution.getString("updated"));
            loadedSolution.setDescription(solution.getString("description"));
            loadedSolution.setExercise_id(exercise_id);
            loadedSolution.setUsers_id(solution.getInt("users_id"));

            solutionsList.add(loadedSolution);
        }

        Solution[] solutionsArray = new Solution[solutionsList.size()];
        solutionsArray = solutionsList.toArray(solutionsArray);

        return solutionsArray;
    }

    static public Solution[] loadAllByUsersId(int users_id) throws SQLException {
        Connection conn = DbUtil.getConn();
        ArrayList<Solution> solutionsList = new ArrayList<>();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Solution WHERE users_id = ? ORDER BY updated DESC;");
        preparedStatement.setInt(1, users_id);
        ResultSet solution = preparedStatement.executeQuery();

        while (solution.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.setId(solution.getInt("id"));
            loadedSolution.setCreated(solution.getString("created"));
            loadedSolution.setUpdated(solution.getString("updated"));
            loadedSolution.setDescription(solution.getString("description"));
            loadedSolution.setExercise_id(solution.getInt("exercise_id"));
            loadedSolution.setUsers_id(users_id);

            solutionsList.add(loadedSolution);
        }

        Solution[] solutionsArray = new Solution[solutionsList.size()];
        solutionsArray = solutionsList.toArray(solutionsArray);

        return solutionsArray;
    }

    static public Solution[] loadAll(int limit) throws SQLException {
        Connection conn = DbUtil.getConn();
        ArrayList<Solution> solutionsList = new ArrayList<>();

        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Solution ORDER BY updated DESC LIMIT ?;");
        preparedStatement.setInt(1, limit);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.setId(resultSet.getInt("id"));
            loadedSolution.setCreated(resultSet.getString("created"));
            loadedSolution.setUpdated(resultSet.getString("updated"));
            loadedSolution.setDescription(resultSet.getString("description"));
            loadedSolution.setExercise_id(resultSet.getInt("exercise_id"));
            loadedSolution.setUsers_id(resultSet.getInt("users_id"));

            solutionsList.add(loadedSolution);
        }

        Solution[] solutionsArray = new Solution[solutionsList.size()];
        solutionsArray = solutionsList.toArray(solutionsArray);

        return solutionsArray;
    }

    static public Solution[] loadAll() throws SQLException {
        Connection conn = DbUtil.getConn();
        ArrayList<Solution> solutionsList = new ArrayList<>();

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Solution;");

        while (resultSet.next()) {
            Solution loadedSolution = new Solution();
            loadedSolution.setId(resultSet.getInt("id"));
            loadedSolution.setCreated(resultSet.getString("created"));
            loadedSolution.setUpdated(resultSet.getString("updated"));
            loadedSolution.setDescription(resultSet.getString("description"));
            loadedSolution.setExercise_id(resultSet.getInt("exercise_id"));
            loadedSolution.setUsers_id(resultSet.getInt("users_id"));

            solutionsList.add(loadedSolution);
        }

        Solution[] solutionsArray = new Solution[solutionsList.size()];
        solutionsArray = solutionsList.toArray(solutionsArray);

        return solutionsArray;
    }

    public Solution saveSolutionToDB() throws SQLException, NullPointerException {
        Connection conn = DbUtil.getConn();
        String sql = "UPDATE Solution SET updated = ?, description = ? WHERE exercise_id = ? AND users_id = ? AND description IS NULL;";

        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, getUpdated());
        preparedStatement.setString(2, getDescription());
        preparedStatement.setInt(3, getExercise_id());
        preparedStatement.setInt(4, getUsers_id());

        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            System.err.println("Nothing changed in database. Solution for this user and entered exercise has been added previously.");
        } else {
            System.out.println("Thank You. Your solution has been successfully saved to database.");
        }

        return this;
    }

    public Solution saveToDB() throws SQLException, NullPointerException {
        Connection conn = DbUtil.getConn();
        if (getId() == 0) {
            String sql = "INSERT INTO Solution (created, exercise_id, users_id) VALUES (?, ?, ?);";
            String[] generatedColumns = {"ID"};

            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, getCreated());
            preparedStatement.setInt(2, getExercise_id());
            preparedStatement.setInt(3, getUsers_id());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                setId(rs.getInt(1));
            }
        } else {
            String sql = "UPDATE Solution SET created = ?, updated = ?, description = ?, exercise_id = ?, users_id = ? WHERE id = ?;";

            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, getCreated());
            preparedStatement.setString(2, getUpdated());
            preparedStatement.setString(3, getDescription());
            preparedStatement.setInt(4, getExercise_id());
            preparedStatement.setInt(5, getUsers_id());
            preparedStatement.setInt(6, getId());

            preparedStatement.executeUpdate();
        }

        return this;
    }

    public void delete() throws SQLException, NullPointerException {
        Connection conn = DbUtil.getConn();
        if (getId() != 0) {
            String sql = "DELETE FROM Solution WHERE id = ?;";
            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, getId());
            preparedStatement.executeUpdate();
            setId(0);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId()).append(" ")
                .append(getCreated()).append(" ")
                .append(getUpdated()).append(" ")
                .append(getDescription()).append(" ")
                .append(getExercise_id()).append(" ")
                .append(getUsers_id());
        return sb.toString();
    }

}
