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

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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

        String sql = "SELECT id, character_name, personality_traits, physical_traits, background, motivation, notes FROM CharacterSheets WHERE actor_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, actorId);
            ResultSet rs = pstmt.executeQuery();

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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sheets;
    }

    public void updateCharacterSheet(CharacterSheet sheet) {
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

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Character sheet updated succesfully.");
            } else {
                System.out.println("No CharacterSheet found with id " + sheet.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCharacterSheet(int id) {
        String sql = "DELETE FROM CharacterSheets WHERE id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Character sheet deleted successfully.");
            } else {
                System.out.println("No character sheet found with id " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
