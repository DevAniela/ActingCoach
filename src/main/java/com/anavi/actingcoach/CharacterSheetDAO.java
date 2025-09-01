package com.anavi.actingcoach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class CharacterSheetDAO {

    private final ActorDAO actorDAO;

    public CharacterSheetDAO(Connection conn) {
        this.actorDAO = new ActorDAO(conn);
    }

    public CharacterSheet getCharacterSheetById(int id) throws SQLException {
        String sql = "SELECT * FROM CharacterSheets WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

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
}
