package com.anavi.actingcoach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DB_URL = "jdbc:sqlite:actingcoach.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {

        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS Users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                email TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL,
                role TEXT NOT NULL
            );
        """;
        
        String createActorsTable = """
            CREATE TABLE IF NOT EXISTS Actors (
                id INTEGER PRIMARY KEY,
                points_earned INTEGER DEFAULT 0,
                FOREIGN KEY (id) REFERENCES Users(id)
            );
        """;

        String createCharacterSheetsTable = """
            CREATE TABLE IF NOT EXISTS CharacterSheets (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    actor_id INTEGER,
                    character_name TEXT,
                    personality_traits TEXT,
                    physical_traits TEXT,
                    background TEXT, 
                    motivation TEXT,
                    FOREIGN KEY (actor_id) REFERENCES Users(id)
            )
        """;

        String createSessionsTable = """
            CREATE TABLE IF NOT EXISTS Sessions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    date_time TEXT,
                    instructor_id INTEGER,
                    actor_id INTEGER,
                    other_actors TEXT,
                    general_feedback TEXT,
                    evaluation TEXT,
                    is_group_session INTEGER,
                    canceled INTEGER,
                    fee REAL,
                    FOREIGN KEY (actor_id) REFERENCES Users(id)),
                    FOREIGN KEY (instructor_id) REFERENCES Users(id))
            )                                                      
        """;
        
        String createJournalEntriesTable = """
            CREATE TABLE IF NOT EXISTS JournalEntries (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    actor_id INTEGER,
                    date_time TEXT,
                    title TEXT,
                    content TEXT,
                    FOREIGN KEY (actor_id) REFERENCES Users(id)
            )
        """;
        
        String createImprovisationsTable = """
            CREATE TABLE IF NOT EXISTS Improvisations (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    actor_id INTEGER,
                    prompt_start TEXT,
                    prompt_end TEXT,
                    start_date_time TEXT,
                    end_date_time TEXT,
                    FOREIGN KEY (actor_id) REFERENCES Users(id)
            )
        """;
        
        String createInvoicesTable = """
            CREATE TABLE IF NOT EXISTS Invoices (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    actor_id INTEGER,
                    session_id INTEGER,
                    amount REAL,
                    is_paid INTEGER,
                    FOREIGN KEY (actor_id) REFERENCES Users(id),
                    FOREIGN KEY (session_id) REFERENCES Sessions(id)
            )
        """;        
        
        String createDialogueLinesTable = """
            CREATE TABLE IF NOT EXISTS DialogueLines(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                promptText TEXT                
            );                                                                                
        """;

        String createExercisesTable = """
            CREATE TABLE IF NOT EXISTS Exercises(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                exerciseText TEXT
            );                                                                                
        """;

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {

            stmt.execute(createUsersTable);
            stmt.execute(createActorsTable);
            stmt.execute(createCharacterSheetsTable);
            stmt.execute(createSessionsTable);
            stmt.execute(createJournalEntriesTable);
            stmt.execute(createImprovisationsTable);
            stmt.execute(createInvoicesTable);
            stmt.execute(createDialogueLinesTable);
            stmt.execute(createExercisesTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
