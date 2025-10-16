package com.anavi.actingcoach;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SessionDAO {

    private final Connection conn;

    public SessionDAO(Connection conn) {
        this.conn = conn;
    }

    public void addSession(Session session) throws SQLException {
        String sql = """
                     INSERT INTO Sessions(date_time, instructor_id, actor_id, other_actors, general_feedback, evaluation, is_group_session, canceled, fee)
                     VALUES (?,?,?,?,?,?,?,?,?)
                     """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, session.getDateTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            pstmt.setInt(2, session.getInstructor() != null ? session.getInstructor().getId() : -1);
            pstmt.setInt(3, session.getActor() != null ? session.getActor().getId() : -1);
            pstmt.setString(4, session.getOtherActors() != null ? String.join(", ", session.getOtherActors()) : "");
            pstmt.setString(5, session.getGeneralFeedback());
            pstmt.setString(6, null); // evaluation handled by EvaluationDAO (store id there)
            pstmt.setInt(7, session.isGroupSession() ? 1 : 0);
            pstmt.setInt(8, session.isCanceled() ? 1 : 0);
            pstmt.setDouble(9, session.getFee());

            int affected = pstmt.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Creating session failed, no rows affected.");
            }

            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    session.setId(keys.getInt(1));
                } else {
                    throw new SQLException("Creating session failed, no ID obtained.");
                }
            }
        }
    }

    public Session getSessionById(int id) throws SQLException {
        String sql = "SELECT * FROM Sessions WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    LocalDateTime dt = LocalDateTime.parse(rs.getString("date_time"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                    int instructorId = rs.getInt("instructor_id");
                    int actorId = rs.getInt("actor_id");
                    String otherActorsCsv = rs.getString("other_actors");
                    String generalFeedback = rs.getString("general_feedback");
                    boolean isGroup = rs.getInt("is_group_session") == 1;
                    boolean canceled = rs.getInt("canceled") == 1;
                    double fee = rs.getDouble("fee");

                    Instructor instructor = new Instructor();
                    instructor.setId(instructorId);

                    Actor actor = new Actor();
                    actor.setId(actorId);

                    Session session = new Session(id, dt, instructor, actor);
                    if (otherActorsCsv != null && !otherActorsCsv.isBlank()) {
                        List<String> others = Arrays.asList(otherActorsCsv.split(",\\s*"));
                        session.setOtherActors(new ArrayList<>(others));
                    }
                    session.setGeneralFeedback(generalFeedback);
                    session.setGroupSession(isGroup);
                    session.setCanceled(canceled);
                    session.calculateFee();

                    return session;
                }
            }
        }
        return null;
    }

    public List<Session> getSessionByActorId(int actorId) throws SQLException {
        // TO DO
    }

    public void updateSession(Session session) throws SQLException {
        // TO DO
    }

    public void deleteSession(int id) throws SQLException {
        // TO DO
    }
}
