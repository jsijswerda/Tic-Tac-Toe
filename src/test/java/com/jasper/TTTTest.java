package com.jasper;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TTTTest
{

    @Test
    public void initializeBoard()    {
        TTT game = new TTT();
        game.initializeBoard();
        assertNotNull(game);
        assertEquals( "-", game.getBoard()[0][0] );
        assertEquals( "-", game.getBoard()[2][2] );
    }

    @Test
    public void initializePlayers(){
        TTT game = new TTT();
        game.initializeBoard();
       // game.initializePlayers();

    }
}
