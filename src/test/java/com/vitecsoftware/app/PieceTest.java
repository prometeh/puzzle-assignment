package com.vitecsoftware.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Some basic tests for puzzle pieces
 */
public class PieceTest {
    @Test
    public void newRightPiece() {
        String name = "RIUR";
        Piece testSubject = new Piece(name);

        assertEquals(testSubject.getName(), name);
        assertEquals(testSubject.getTop(), EdgeType.STRAIGHT);
        assertEquals(testSubject.getRight(), EdgeType.INWARD);
        assertEquals(testSubject.getDown(), EdgeType.OUTWARD);
        assertEquals(testSubject.getLeft(), EdgeType.STRAIGHT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void newShortLengthPiece() {
        String name = "UU";

        new Piece(name);

    }

    @Test(expected = IllegalArgumentException.class)
    public void newWrongPiece() {
        String name = "UUQR";

        new Piece(name);

    }

}
