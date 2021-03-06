package edu.usfca.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



class SongTest {
    Library library;
    Song song1, song2;
    Album album1, album2;
    Artist artist1, artist2, newArtist;
    Connection connection = null;
    Statement statement;
    SQLite sqLite;

    @BeforeEach
    void setUp() {
        sqLite = new SQLite();
        library = new Library();
        song1 = new Song(101, "happy birthday");
        song2 = new Song(102, "good day");
        artist1 = new Artist(201, "adele");
        artist2 = new Artist(202, "lim");
        artist1.setAudioDB_ID("111493");
        artist2.setAudioDB_ID("11112");
        album1 = new Album(301, "little prince");
        album2 = new Album(302, "little princess");
        song1.setPerformer(artist1);
        song2.setPerformer(artist2);
        song1.setAlbum(album1);
        song2.setAlbum(album2);
        artist1.addSong(song1);
        artist1.addSong(song2);
        artist1.addAlbum(album1);
        artist2.addAlbum(album2);
        album1.setArtist(artist1);
        album2.setArtist(artist2);
        album1.addSong(song1);
        album2.addSong(song2);
        library.addSong(song1);
        library.addSong(song2);
        library.addArtist(artist1);
        library.addArtist(artist2);
        library.addAlbum(album1);
        library.addAlbum(album2);
    }

    @Test
    void setArtistForSong() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            sqLite.createSQLTables(statement);
            sqLite.initiateLibrary(statement);
            song1.setArtistForSong(library);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }

    @Test
    void insertAlbumIntoSong() {
    }
}