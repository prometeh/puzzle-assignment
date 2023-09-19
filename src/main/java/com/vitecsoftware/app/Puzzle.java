package com.vitecsoftware.app;

import java.util.ArrayList;

public class Puzzle {
    private String[] data;
    private ArrayList<ArrayList<Piece>> solutionBoard = new ArrayList<>();

    public Puzzle(String[] data) {
        setData(data);

    }

    private void setData(String[] data) {
        this.data = data;
    }

    public ArrayList<Piece> getPieces() {
        int totalPieces = Integer.parseInt(data[0]);
        ArrayList<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < totalPieces; i++) {
            pieces.add(new Piece(data[i + 1]));
        }

        return pieces;
    }

    // TODO: needs a heavy refactoring
    public void trySolvePuzzle(ArrayList<ArrayList<Piece>> solutionBoard, ArrayList<Piece> pieces) {
        for (Piece piece : pieces) {
            // first we try to put the first puzzle
            if (solutionBoard.isEmpty() || getLastPieceInThisRow(solutionBoard).getRight() == EdgeType.STRAIGHT) {
                solutionBoard.add(new ArrayList<>());

                if (piece.getLeft() == EdgeType.STRAIGHT
                        && (piece.getTop().value + getAbovePieceDownEdgeTypeValue(solutionBoard) == 0)) {
                    solutionBoard.get(solutionBoard.size() - 1).add(piece);
                }

            }
            else if (!solutionBoard.isEmpty()
                    && (solutionBoard.get(solutionBoard.size() - 1).indexOf(getLastPieceInThisRow(solutionBoard)) == calculateWidth(pieces) - 1)
                    &&
                    (piece.getTop().value + getAbovePieceDownEdgeTypeValue(solutionBoard) == 0)
                    && (piece.getRight() == EdgeType.STRAIGHT)
                    && (piece.getLeft().value + getLastPieceInThisRow(solutionBoard).getRight().value == 0)) {
                solutionBoard.get(solutionBoard.size() - 1).add(piece);
                if (solutionBoard.size() == calculateHeight(pieces)) {
                    break;

                }
                else {
                    continue;
                }

            } else if (!solutionBoard.isEmpty()
                    && getLastPieceInThisRow(solutionBoard).getRight().value + piece.getLeft().value == 0
                    && piece.getTop().value + getAbovePieceDownEdgeTypeValue(solutionBoard) == 0) {
                solutionBoard.get(solutionBoard.size() - 1).add(piece);
                trySolvePuzzle(solutionBoard, pieces);
            }
        }
    }

    private Piece getLastPieceInThisRow(ArrayList<ArrayList<Piece>> solutionBoard) throws IllegalArgumentException {
        if (solutionBoard.isEmpty()) {
            throw new IllegalArgumentException("your board is empty");
        }
        int currentRow = solutionBoard.size() - 1;
        Piece lastOne = solutionBoard.get(currentRow).get(solutionBoard.get(currentRow).size() - 1);

        return lastOne;
    }

    private int getAbovePieceDownEdgeTypeValue(ArrayList<ArrayList<Piece>> solutionBoard) {
        int upperRowIndex = solutionBoard.size() - 2;
        int currentRow = solutionBoard.size() - 1;
        if (upperRowIndex < 0) {
            return 0;
        }
        int upperPieceIndex = 0;
        if (solutionBoard.get(currentRow).size() != 0) {
            upperPieceIndex = solutionBoard.get(currentRow).indexOf(getLastPieceInThisRow(solutionBoard)) + 1;
        }

        return solutionBoard.get(upperRowIndex).get(upperPieceIndex).getDown().value;
    }

    private int calculateWidth(ArrayList<Piece> pieces) {
        return (int) pieces.stream().filter(p -> p.getTop() == EdgeType.STRAIGHT).count();
    }

    private int calculateHeight(ArrayList<Piece> pieces) {
        return (int) pieces.stream().filter(p -> p.getLeft() == EdgeType.STRAIGHT).count();
     }

    public boolean isSolvable() {
        trySolvePuzzle(solutionBoard, getPieces());
        return false;
    }
}
