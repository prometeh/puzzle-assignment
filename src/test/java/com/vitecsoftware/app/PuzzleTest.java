package com.vitecsoftware.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PuzzleTest {
    @Test
    public void newRightPuzzle() {
        String[] data = { "20", "RUIR", "RUUI", "RIUI", "RIIU", "RRIU", "UUUR", "IIII", "IIIU", "UIIU", "URUU", "IIIR",
                "UUUU", "UIII", "UIUU", "IRIU", "UURR", "IURI", "UIRI", "IIRU", "URRU" };

        Puzzle testSubject = new Puzzle(data);

        assertTrue(testSubject.isSolvable());
        // assertEquals(testSubject.noOfSolutions, 1);
    }

    @Test
    public void newUnsolvablePuzzle() {
        String[] unsolvableData = { "20", "RUIR", "RUUI", "RIUI", "RIIU", "RIIU", "UUUR", "IIII", "IIIU", "UIIU",
                "URUU", "IIIR", "UUUU", "UIII", "UIUU", "IRIU", "UURR", "IURI", "UIRI", "IIRU", "URRU" };
        Puzzle testSubject = new Puzzle(unsolvableData);

        // assertArrayEquals(testSubject.getPieces(), pieces);
        assertTrue(!testSubject.isSolvable());
        // assertEquals(testSubject.noOfSolutions, 0);
    }
}
