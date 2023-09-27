package com.vitecsoftware.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PuzzleTest {
    @Test
    public void newRightPuzzle() {
        String[] data = { "20", "RUIR", "RUUI", "RIUI", "RIIU", "RRIU", "UUUR", "IIII", "IIIU", "UIIU", "URUU", "IIIR",
                "UUUU", "UIII", "UIUU", "IRIU", "UURR", "IURI", "UIRI", "IIRU", "URRU" };

        Puzzle testSubject = new Puzzle(data);

        assertTrue(testSubject.isSolvable());
        assertEquals(testSubject.getSolutionsNumber(), 1);
    }


    @Test
    public void newRightPuzzleMultiSolution() {
        String[] data = { "20",
            "RUIR", "RUUI", "RIUI", "RIIU", "RRIU",
            "UIUR", "IIIU", "IIIU", "UIIU", "URUU",
            "IIIR", "UUUU", "UIII", "UIIU", "IRIU",
            "UURR", "IURI", "UIRI", "UIRU", "URRU" };

        Puzzle testSubject = new Puzzle(data);

        assertTrue(testSubject.isSolvable());
        assertEquals(testSubject.getSolutionsNumber(), 4);
    }

    @Test
    public void newUnsolvablePuzzle() {
        String[] unsolvableData = { "20", "RUIR", "RUUI", "RIUI", "RIIU", "RIIU", "UUUR", "IIII", "IIIU", "UIIU",
                "URUU", "IIIR", "UUUU", "UIII", "UIUU", "IRIU", "UURR", "IURI", "UIRI", "IIRU", "URRU" };
        Puzzle testSubject = new Puzzle(unsolvableData);

        assertTrue(!testSubject.isSolvable());
        assertEquals(testSubject.getSolutionsNumber(), 0);
    }
}
