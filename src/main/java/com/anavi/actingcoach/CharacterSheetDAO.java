package com.anavi.actingcoach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CharacterSheetDAO {

    private final Connection conn;
    private final ActorDAO actorDAO;

    public CharacterSheetDAO(Connection conn) {
        this.conn = conn;
        this.actorDAO = new ActorDAO(conn);
    }

    public void addCharacterSheet(CharacterSheet sheet) throws SQLException {
        String insertCharacterSheetSql = """
                                         INSERT INTO CharacterSheets(actor_id, character_name, personality_traits, physical_traits, background, motivation, notes)
                                         VALUES(?, ?, ?, ?, ?, ?, ?)
                                         """;

        try (PreparedStatement characterSheetStmt = conn.prepareStatement(insertCharacterSheetSql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
            characterSheetStmt.setInt(1, sheet.getActor().getId());
            characterSheetStmt.setString(2, sheet.getCharacterName());
            characterSheetStmt.setString(3, String.join(", ", sheet.getPersonalityTraits()));
            characterSheetStmt.setString(4, String.join(", ", sheet.getPhysicalTraits()));
            characterSheetStmt.setString(5, sheet.getBackground());
            characterSheetStmt.setString(6, sheet.getMotivation());
            characterSheetStmt.setString(7, sheet.getNotes());

            int affectedRows = characterSheetStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating character sheet failed, no rows affected.");
            }

            try (ResultSet generatedKeys = characterSheetStmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    sheet.setId(generatedId);
                } else {
                    throw new SQLException("Creating character sheet failed, no ID obtained.");
                }
            }
        }
    }

    public CharacterSheet getCharacterSheetById(int id) throws SQLException {
        String sql = "SELECT * FROM CharacterSheets WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int actorId = rs.getInt("actor_id");
                    Actor actor = actorDAO.getActorById(actorId);
                    CharacterSheet sheet = new CharacterSheet(
                            actor,
                            rs.getString("character_name"),
                            Arrays.asList(rs.getString("personality_traits").split(",\\s*")),
                            Arrays.asList(rs.getString("physical_traits").split(",\\s*")),
                            rs.getString("background"),
                            rs.getString("motivation"),
                            rs.getString("notes")
                    );

                    sheet.setId(rs.getInt("id"));
                    return sheet;
                }
            }
        }
        return null;
    }

    public List<CharacterSheet> getCharacterSheetsByActorId(int actorId) throws SQLException {
        List<CharacterSheet> sheets = new ArrayList<>();
        String sql = "SELECT * FROM CharacterSheets WHERE actor_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, actorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Actor actor = actorDAO.getActorById(actorId);
                    CharacterSheet sheet = new CharacterSheet(
                            rs.getInt("id"),
                            actor,
                            rs.getString("character_name"),
                            Arrays.asList(rs.getString("personality_traits").split(",\\s*")),
                            Arrays.asList(rs.getString("physical_traits").split(",\\s*")),
                            rs.getString("background"),
                            rs.getString("motivation"),
                            rs.getString("notes")
                    );
                    sheets.add(sheet);
                }
            }
        }
        return sheets;
    }

    public void updateCharacterSheet(CharacterSheet sheet) throws SQLException {
        String sql = """
        UPDATE CharacterSheets
        SET character_name=?, personality_traits=?, physical_traits=?, background=?, motivation=?, notes=?
        WHERE id=?
    """;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sheet.getCharacterName());
            pstmt.setString(2, String.join(", ", sheet.getPersonalityTraits()));
            pstmt.setString(3, String.join(", ", sheet.getPhysicalTraits()));
            pstmt.setString(4, sheet.getBackground());
            pstmt.setString(5, sheet.getMotivation());
            pstmt.setString(6, sheet.getNotes());
            pstmt.setInt(7, sheet.getId());
            pstmt.executeUpdate();
        }
    }

    public void deleteCharacterSheet(int id) throws SQLException {
        String sql = "DELETE FROM CharacterSheets WHERE id=?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
