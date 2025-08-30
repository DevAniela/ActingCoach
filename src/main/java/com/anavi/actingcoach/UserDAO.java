package com.anavi.actingcoach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO Users(name, email, password, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getRole());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserByName(String name) {
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
                        System.err.println("Role " + role + " not found.");
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // no user found
    }
}
