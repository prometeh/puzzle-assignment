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

    // @Test
    // public void newUnsolvablePuzzle() {
    // String[] data = { "20", "RUIR", "RUUI", "RIUI", "RIIU", "RRIU", "UUUR",
    // "IIII", "IIIU", "UIIU", "URUU", "IIIR",
    // "UUUU", "UIII", "UIUU", "IRIU", "UURR", "IURI", "UIRI", "IIRU", "URUU" };
    // Piece[] pieces = { new Piece("RUIR"), new Piece("RUUI"), new Piece("RIUI"),
    // new Piece("RIIU"), new Piece("RRIU"), new Piece("UUUR"), new Piece("IIII"),
    // new Piece("IIIU"), new Piece("UIIU"), new Piece("URUU"),
    // new Piece("IIIR"), new Piece("UUUU"), new Piece("UIII"), new Piece("UIUU"),
    // new Piece("IRIU"), new Piece("UURR"), new Piece("IURI"), new Piece("UIRI"),
    // new Piece("IIRU"), new Piece("URUU") };
    // Puzzle testSubject = new Puzzle(data);

    // //assertArrayEquals(testSubject.getPieces(), pieces);
    // assertTrue(!testSubject.isSolvable());
    // //assertEquals(testSubject.noOfSolutions, 0);
    // }
}
