package model;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class User {

    private int id = 0;
    private String username;
    private String email;
    private String password;
    private int user_group_id;

    public User() {}

    public User(String username, String email, String password, int user_group_id) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
        setUser_group_id(user_group_id);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUser_group_id() {
        return user_group_id;
    }

    private User setId(int id) {
        this.id = id;
        return this;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = BCrypt.hashpw( password, BCrypt.gensalt() );
        return this;
    }

    public User setUser_group_id(int user_group_id) {
        this.user_group_id = user_group_id;
        return this;
    }

    static public User loadUserById(int id) throws SQLException {
        Connection conn = DbUtil.getConn();
        String sql = "SELECT * FROM Users WHERE id = ?;";
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User loadedUser = new User();
            loadedUser.setId(id);
            loadedUser.setUsername(resultSet.getString("username"));
            loadedUser.setEmail(resultSet.getString("email"));
            loadedUser.setPassword(resultSet.getString("password"));
            loadedUser.setUser_group_id(resultSet.getInt("user_group_id"));
            return loadedUser;
        }
        return null;
    }

    static public User[] loadAllByGroupId(int user_group_id) throws SQLException {
        Connection conn = DbUtil.getConn();
        ArrayList<User> usersList = new ArrayList<>();
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Users WHERE user_group_id = ?;");
        preparedStatement.setInt(1, user_group_id);
        ResultSet user = preparedStatement.executeQuery();

        while (user.next()) {
            User loadedUser = new User();
            loadedUser.setId(user.getInt("id"));
            loadedUser.setUsername(user.getString("username"));
            loadedUser.setEmail(user.getString("email"));
            loadedUser.setPassword(user.getString("password"));
            loadedUser.setUser_group_id(user_group_id);

            usersList.add(loadedUser);
        }

        User[] usersArray = new User[usersList.size()];
        usersArray = usersList.toArray(usersArray);

        return usersArray;
    }

    static public User[] loadAll() throws SQLException {
        Connection conn = DbUtil.getConn();
        ArrayList<User> usersList = new ArrayList<>();

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Users;");

        while ( resultSet.next() ) {
            User loadedUser = new User();
            loadedUser.setId(resultSet.getInt("id"));
            loadedUser.setUsername(resultSet.getString("username"));
            loadedUser.setEmail(resultSet.getString("email"));
            loadedUser.setPassword(resultSet.getString("password"));
            loadedUser.setUser_group_id(resultSet.getInt("user_group_id"));

            usersList.add(loadedUser);
        }

        User[] usersArray = new User[usersList.size()];
        usersArray = usersList.toArray(usersArray);

        return usersArray;
    }

    public User saveToDB() throws SQLException, NullPointerException {
        Connection conn = DbUtil.getConn();
        if (getId() == 0) {
            String sql = "INSERT INTO Users (username, email, password, user_group_id) VALUES (?, ?, ?, ?);";
            String[] generatedColumns = {"ID"};

            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, getUsername());
            preparedStatement.setString(2, getEmail());
            preparedStatement.setString(3, getPassword());
            preparedStatement.setInt(4, getUser_group_id());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                setId(rs.getInt(1));
            }
        } else {
            String sql = "UPDATE Users SET username = ?, email = ?, password = ?, user_group_id = ? WHERE id = ?;";

            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, getUsername());
            preparedStatement.setString(2, getEmail());
            preparedStatement.setString(3, getPassword());
            preparedStatement.setInt(4, getUser_group_id());
            preparedStatement.setInt(5, getId());

            preparedStatement.executeUpdate();
        }
        return this;
    }

    public void delete() throws SQLException, NullPointerException {
        Connection conn = DbUtil.getConn();
        if (getId() != 0) {
            String sql = "DELETE FROM Users WHERE id = ?;";
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
                .append(getUsername()).append(" ")
                .append(getEmail()).append(" ")
                .append(getPassword()).append(" ")
                .append(getUser_group_id());
        return sb.toString();
    }

}
