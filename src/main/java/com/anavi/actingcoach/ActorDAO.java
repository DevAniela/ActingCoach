package com.anavi.actingcoach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.StringJoiner;

public class ActorDAO {

    private final Connection conn;

    public ActorDAO(Connection conn) {
        this.conn = conn;
    }

    void addCharacterSheet(CharacterSheet sheet, int actorId) {
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

        try (Connection conn = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, actorId);
            pstmt.setString(2, sheet.getCharacterName());
            pstmt.setString(3, String.join(", ", sheet.getPersonalityTraits()));
            pstmt.setString(4, String.join(", ", sheet.getPhysicalTraits()));
            pstmt.setString(5, sheet.getBackground());
            pstmt.setString(6, sheet.getMotivation());
            pstmt.setString(7, sheet.getNotes());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String listToString(java.util.List<String> list) {
        StringJoiner joiner = new StringJoiner(", ");
        for (String item : list) {
            joiner.add(item);
        }
        return joiner.toString();
    }

}
