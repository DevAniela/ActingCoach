package com.anavi.actingcoach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ActorDAO {

    private final Connection conn;

    public ActorDAO(Connection conn) {
        this.conn = conn;
    }

    public void addActor(Actor actor) throws SQLException {
        String insertUserSql = "INSERT INTO Users(name, email, password, role) VALUES (?, ?, ?, ?)";
        String insertActorSql = "INSERT INTO Actors(id, points_earned) VALUES(?, ?)";

        boolean previousAutoCommit = conn.getAutoCommit();

        try {
            conn.setAutoCommit(false);
            try (PreparedStatement userStmt = conn.prepareStatement(insertUserSql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
                userStmt.setString(1, actor.getName());
                userStmt.setString(2, actor.getEmail());
                userStmt.setString(3, actor.getPassword());
                userStmt.setString(4, User.ROLE_ACTOR);

                int affectedRows = userStmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = userStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        actor.setId(generatedId);
                    } else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }
            }
            try (PreparedStatement actorStmt = conn.prepareStatement(insertActorSql)) {
                actorStmt.setInt(1, actor.getId());
                actorStmt.setInt(2, actor.getPointsEarned());

                actorStmt.executeUpdate();
            }
            conn.commit();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                ex.addSuppressed(rollbackEx);
            }
            throw ex;
        } finally {
            conn.setAutoCommit(previousAutoCommit);
        }
    }

    public Actor getActorById(int id) throws SQLException {
        String sql = """
                     SELECT u.id, u.name, u.email, u.password, u.role, a.points_earned 
                     FROM Users u 
                     JOIN Actors a ON u.id = a.id 
                     WHERE u.id = ?
                     """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Actor(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getInt("points_earned")
                    );
                }
            }
        }
        return null;
    }

    public List<Actor> getAllActors() throws SQLException {
        String sql = """
                     SELECT u.id, u.name, u.email, u.password, u.role, a.points_earned
                     FROM Users u
                     JOIN Actors a ON u.id = a.id
                     """;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            List<Actor> actors = new ArrayList<>();
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Actor actor = new Actor(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getInt("points_earned")
                    );
                    actors.add(actor);
                }
            }
            return actors;
        }
    }

    public void updateActor(Actor actor) throws SQLException {
        // to do
    }

    public void deleteActor(int id) throws SQLException {
        // to do
    }
}
