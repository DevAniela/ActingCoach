package com.anavi.actingcoach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        // to do
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
        // to do
    }

    public void updateCharacterSheet(CharacterSheet sheet) throws SQLException {
        // to do
    }

    public void deleteCharacterSheet(int id) throws SQLException {
        // to do
    }
}
