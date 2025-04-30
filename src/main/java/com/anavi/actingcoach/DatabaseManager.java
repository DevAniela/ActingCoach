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
                username TEXT NOT NULL UNIQUE,
                password TEXT NOT NULL,
                role TEXT NOT NULL
    );
""";

        String createSessionsTable = """
            CREATE TABLE IF NOT EXISTS Sessions(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                actor_id INTEGER NOT NULL,
                instructor_id INTEGER NOT NULL,
                dateTime TEXT NOT NULL,
                canceled INTEGER DEFAULT 0,
                feedback TEXT,
                FOREIGN KEY(actor_id) REFERENCES Users(id),
                FOREIGN KEY(instructor_id) REFERENCES Users(id)
    );
""";
        String createEvaluationsTable = """
            CREATE TABLE IF NOT EXISTS Evaluations(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                session_id INTEGER NOT NULL,
                expressiveness INTEGER NOT NULL,
                diction INTEGER NOT NULL,
                emotion INTEGER NOT NULL,
                notes TEXT,
                FOREIGN KEY(session_id) REFERENCES Sessions(id)
    );                                                                                
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
            stmt.execute(createSessionsTable);
            stmt.execute(createEvaluationsTable);
            stmt.execute(createDialogueLinesTable);
            stmt.execute(createExercisesTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
