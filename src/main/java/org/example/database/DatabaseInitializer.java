package org.example.database;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initializeDatabase() {
        String createAudioTable = """
            CREATE TABLE IF NOT EXISTS Audio (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                format TEXT,
                path TEXT
            );
        """;

        String createTrackTable = """
            CREATE TABLE IF NOT EXISTS Track (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                audio_id INTEGER,
                start_time INTEGER,
                end_time INTEGER,
                FOREIGN KEY (audio_id) REFERENCES Audio(id)
            );
        """;

        String createProjectTable = """
            CREATE TABLE IF NOT EXISTS Project (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT,
                created_at DATETIME DEFAULT CURRENT_TIMESTAMP
            );
        """;

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createAudioTable);
            statement.execute(createTrackTable);
            statement.execute(createProjectTable);
            System.out.println("Tables initialized!");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize tables", e);
        }
    }
}