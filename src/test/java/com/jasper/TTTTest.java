package com.jasper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TTTTest
{

    @Before
    public void setup(){

    }



    @Test
    public void initializeBoard()    {
        TTT game = new TTT();
        game.initializeBoard();
        assertNotNull(game);
        assertEquals("-", game.getBoard()[0][0]);
        assertEquals( "-", game.getBoard()[2][2]);
    }

    @Test
    public void isWinner(){
        TTT game = new TTT();
        game.initializeBoard();
        Player john = new Player("John","O",true);
        Player mary = new Player("Mary",john,false);
        john.setOtherPlayer(mary);

    }
}
