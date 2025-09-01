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

    public void addCharacterSheet(CharacterSheet sheet, int actorId) throws SQLException {
        String sql = """
            INSERT INTO CharacterSheets (
                     actor_id, 
                     character_name, 
                     personality_traits, 
                     physical_traits, 
                     background, 
                     motivation, 
                     notes
                     ) VALUES (?, ?, ?, ?, ?, ?, ?);
        """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, actorId);
            pstmt.setString(2, sheet.getCharacterName());
            pstmt.setString(3, String.join(", ", sheet.getPersonalityTraits()));
            pstmt.setString(4, String.join(", ", sheet.getPhysicalTraits()));
            pstmt.setString(5, sheet.getBackground());
            pstmt.setString(6, sheet.getMotivation());
            pstmt.setString(7, sheet.getNotes());

            pstmt.executeUpdate();
        }
    }

    public List<CharacterSheet> getCharacterSheetsByActorId(int actorId) throws SQLException {
        List<CharacterSheet> sheets = new ArrayList<>();

        String sql = "SELECT id, character_name, personality_traits, physical_traits, background, motivation, notes FROM CharacterSheets WHERE actor_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, actorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    CharacterSheet sheet = new CharacterSheet();
                    sheet.setId(rs.getInt("id"));
                    sheet.setCharacterName(rs.getString("character_name"));
                    sheet.setPersonalityTraits(Arrays.asList(rs.getString("personality_traits").split(",\\s*")));
                    sheet.setPhysicalTraits(Arrays.asList(rs.getString("physical_traits").split(",\\s*")));
                    sheet.setBackground(rs.getString("background"));
                    sheet.setMotivation(rs.getString("motivation"));
                    sheet.setNotes(rs.getString("notes"));

                    sheets.add(sheet);
                }
            }
        }
        return sheets;
    }

    public void updateCharacterSheet(CharacterSheet sheet) throws SQLException {
        String sql = """
            UPDATE CharacterSheets SET
                character_name = ?,
                personality_traits = ?,
                physical_traits = ?,
                background = ?,
                motivation = ?,
                notes = ?
            WHERE id = ?
        """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sheet.getCharacterName());
            pstmt.setString(2, String.join(", ", sheet.getPersonalityTraits()));
            pstmt.setString(3, String.join(", ", sheet.getPhysicalTraits()));
            pstmt.setString(4, sheet.getBackground());
            pstmt.setString(5, sheet.getMotivation());
            pstmt.setString(6, sheet.getNotes());
            pstmt.setInt(7, sheet.getId());
        }
    }

    public void deleteCharacterSheet(int id) throws SQLException {
        String sql = "DELETE FROM CharacterSheets WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
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
}
