package com.jasper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlayerTest {

    @Test
    public void player2MarkO(){
        Player john = new Player("John","X",true);
        Player mary = new Player("Mary",john,false);
        assertEquals("O", mary.getMark());
    }

    @Test
    public void player2MarkX(){
        Player john = new Player("John","O",true);
        Player mary = new Player("Mary",john,false);
        assertEquals("X", mary.getMark());
    }
}
