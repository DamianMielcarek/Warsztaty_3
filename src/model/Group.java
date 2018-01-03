package model;

import java.sql.*;
import java.util.ArrayList;

public class Group {

    private int id;
    private String name;

    public Group() {}

    public Group(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    private Group setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    static public Group loadGroupById(int id) throws SQLException {
        Connection conn = DbUtil.getConn();
        String sql = "SELECT * FROM User_group WHERE id = ?;";
        PreparedStatement preparedStatement;
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Group loadedGroup = new Group();
            loadedGroup.setId(resultSet.getInt("id"));
            loadedGroup.setName(resultSet.getString("name"));
            return loadedGroup;
        }
        return null;
    }

    static public Group[] loadAll() throws SQLException {
        Connection conn = DbUtil.getConn();
        ArrayList<Group> groupsList = new ArrayList<>();

        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM User_group;");

        while (resultSet.next()) {
            Group loadedGroup = new Group();
            loadedGroup.setId(resultSet.getInt("id"));
            loadedGroup.setName(resultSet.getString("name"));

            groupsList.add(loadedGroup);
        }

        Group[] groupsArray = new Group[groupsList.size()];
        groupsArray = groupsList.toArray(groupsArray);

        return groupsArray;
    }

    public Group saveToDB() throws SQLException, NullPointerException {
        Connection conn = DbUtil.getConn();
        if ( getId() == 0 ) {
            String sql = "INSERT INTO User_group (name) VALUES (?);";
            String[] generatedColumns = {"ID"};

            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql, generatedColumns);
            preparedStatement.setString(1, getName());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                setId(rs.getInt(1));
            }
        } else {
            String sql = "UPDATE User_group SET name = ? WHERE id = ?;";

            PreparedStatement preparedStatement;
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, getName());
            preparedStatement.setInt(2, getId());

            preparedStatement.executeUpdate();
        }
        return this;
    }

    public void delete() throws SQLException, NullPointerException {
        Connection conn = DbUtil.getConn();
        if (getId() != 0) {
            String sql = "DELETE FROM User_group WHERE id = ?;";
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
                .append(getName());
        return sb.toString();
    }

}
