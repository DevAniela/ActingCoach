package com.anavi.actingcoach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    public List<CharacterSheet> getCharacterSheetsByActorId(int actorId) {
        List<CharacterSheet> sheets = new ArrayList<>();

        String sql = "SELECT character_name, personality_traits, physical_traits, background, motivation, notes FROM CharacterSheets WHERE actor_id = ?";

        try (Connection con = DatabaseManager.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, actorId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                CharacterSheet sheet = new CharacterSheet();
                sheet.setCharacterName(rs.getString("character_name"));
                sheet.setPersonalityTraits(Arrays.asList(rs.getString("personality_traits").split(",\\s*")));
                sheet.setPhysicalTraits(Arrays.asList(rs.getString("physical_traits").split(",\\s*")));
                sheet.setBackground(rs.getString("background"));
                sheet.setMotivation(rs.getString("motivation"));
                sheet.setNotes(rs.getString("notes"));

                sheets.add(sheet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sheets;
    }

    private String listToString(java.util.List<String> list) {
        StringJoiner joiner = new StringJoiner(", ");
        for (String item : list) {
            joiner.add(item);
        }
        return joiner.toString();
    }

}
