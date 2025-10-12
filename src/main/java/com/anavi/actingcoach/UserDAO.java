package com.anavi.actingcoach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO Users(name, email, password, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (var generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
    }

    public User getUserByName(String name) throws SQLException {
        String sql = "SELECT * FROM Users WHERE name = ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);

            try (var rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String role = rs.getString("role");

                    if (role.equalsIgnoreCase(User.ROLE_ACTOR)) {
                        return new Actor(id, name, email, password, role);
                    } else if (role.equalsIgnoreCase(User.ROLE_INSTRUCTOR)) {
                        return new Instructor(id, name, email, password, role);
                    } else {
                        throw new SQLException("Unknown role: " + role);
                    }
                }
            }
        }
        return null; // no user found
    }

    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM Users WHERE email = ?";
        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            try (var rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String password = rs.getString("password");
                    String role = rs.getString("role");

                    if (role.equalsIgnoreCase(User.ROLE_ACTOR)) {
                        return new Actor(id, name, email, password, role);
                    } else if (role.equalsIgnoreCase(User.ROLE_INSTRUCTOR)) {
                        return new Instructor(id, name, email, password, role);
                    } else {
                        return null;
                    }
                }
            }
        }
        return null;
    }
}
