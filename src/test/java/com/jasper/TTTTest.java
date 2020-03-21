package com.jasper;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TTTTest {

    @Test
    public void initializeBoard()    {
        TTT game = new TTT();
        game.initializeBoard();
        assertNotNull(game);
        assertEquals("-", game.getBoard()[0][0]);
        assertEquals( "-", game.getBoard()[2][2]);
    }

//    @Test
//    public void initPlayers(){
//        TTT game = new TTT();
//        game.initializeBoard();
//
//        Scanner mockScanner = mock(Scanner.class);
//        when(mockScanner.nextLine()).thenReturn("Pete");
//        when(mockScanner.nextLine()).thenReturn("John");
//        when(mockScanner.nextLine()).thenReturn("X");
//        game.initializePlayers();
//    }

    @Test
    public void testPlayer(){
        TTT game = new TTT();
        String playerName = "Pete";
        InputStream in = new ByteArrayInputStream(playerName.getBytes());
        System.setIn(in);
        assertEquals("Pete", game.createNewPlayer());
    }


    public void makePlayers(){
        TTT game = new TTT();
        game.initializeBoard();
        game.initializePlayers();

    }
}

