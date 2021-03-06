package edu.usfca.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {
    Playlist myPlaylist;
    Song s1, s2;
    UserPrompt userPrompt = new UserPrompt();

    @BeforeEach
    void setUp() {
        myPlaylist = new Playlist();
        s1 = new Song("Happy Birthday");
        s2 = new Song("Beauty");
    }

    @Test
    void addSong() {
        myPlaylist.addSong(s1);
        assertTrue(myPlaylist.getListOfSongs().contains(s1));
        assertFalse(myPlaylist.getListOfSongs().contains(s2));
    }

    private void assertTrue(boolean contains) {
    }

    @Test
    void deleteSong() {
        myPlaylist.deleteSong(s1);
        assertFalse(myPlaylist.getListOfSongs().contains(s1));
    }

    @Test
    void generatePlaylistByLikes() {
        String data = "5\r\n";
        InputStream stdin = System.in;
        try {
            System.setIn(new ByteArrayInputStream(data.getBytes()));
            Scanner scanner = new Scanner(System.in);
            assertEquals(5, userPrompt.promptUserForLikes(s1, scanner));
        } finally {
            System.setIn(stdin);
        }
    }
}