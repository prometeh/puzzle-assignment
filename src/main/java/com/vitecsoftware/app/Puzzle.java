package com.vitecsoftware.app;

import java.util.ArrayList;

public class Puzzle {
    private ArrayList<ArrayList<Piece>> solutionBoard;
    private ArrayList<Piece> pieces;
    private int width;
    private int height;
    private int totalPieces;
    private boolean solvable;

    public Puzzle(String[] data) {
        this.pieces = getPieces(data);
        this.solutionBoard = new ArrayList<>();
        this.width = calculateWidth(pieces);
        this.height = calculateHeight(pieces);

    }

    public ArrayList<Piece> getPieces(String[] data) {
        this.totalPieces = Integer.parseInt(data[0]);
        ArrayList<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < totalPieces; ++i) {
            pieces.add(new Piece(data[i + 1]));
        }
        
        return pieces;
    }

    public void trySolvePuzzle() {
        ArrayList<Piece> rowResult = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            rowResult.clear();
            solveArow(pieces, rowResult);
            if (!solvable) {
                break;
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

    private int calculateWidth(ArrayList<Piece> pieces) {
        return (int) pieces.stream().filter(p -> p.getTop() == EdgeType.STRAIGHT).count();
    }

    private int calculateHeight(ArrayList<Piece> pieces) {
        return (int) pieces.stream().filter(p -> p.getLeft() == EdgeType.STRAIGHT).count();
    }

    // TODO: needs refactoring
    private EdgeType getExpectedEdgeType(ArrayList<ArrayList<Piece>> solutionBoard, Direction direction) {
        int upperRowIndex = solutionBoard.size() - 2;
        int currentRowIndex = solutionBoard.size() - 1;
        int upperPieceIndex = 0;
        int edgeValue = 0;
        if (direction == Direction.UP) {
            if (upperRowIndex < 0) {
                return EdgeType.STRAIGHT;
            } else if (solutionBoard.get(currentRowIndex).size() != 0
                    && solutionBoard.get(currentRowIndex).size() != width) {
                upperPieceIndex = solutionBoard.get(currentRowIndex).indexOf(getLastPieceInThisRow(solutionBoard)) + 1;
            }

            edgeValue = solutionBoard.get(upperRowIndex).get(upperPieceIndex).getDown().value * -1;

        } else { // if (direction == Direction.LEFT)
            if (solutionBoard.isEmpty() || currentRowIndex < 0) {
                return EdgeType.STRAIGHT;
            }
            edgeValue = solutionBoard.get(currentRowIndex).get(solutionBoard.get(currentRowIndex).size() - 1)
                    .getRight().value * -1;
        }

        if (edgeValue == EdgeType.INWARD.value) {
            return EdgeType.INWARD;
        } else
            return EdgeType.OUTWARD;
    }

    // TODO: needs refactoring
    private void solveArow(ArrayList<Piece> pieces, ArrayList<Piece> result) {
        ArrayList<Piece> rowCandidates = new ArrayList<>();
        ArrayList<Piece> secondRowCandidates = new ArrayList<>();
        int initialPieces = pieces.size();

        for (Piece piece : pieces) {
            if (piece.getTop() == getExpectedEdgeType(solutionBoard, Direction.UP)) {
                rowCandidates.add(piece);
            }
        }

        for (Piece piece : rowCandidates) {
            pieces.remove(piece);
            if (piece.getLeft() == getExpectedEdgeType(solutionBoard, Direction.LEFT)) {
                secondRowCandidates.add(piece);
            } else {
                pieces.add(piece);
            }
        }

        for (Piece piece : secondRowCandidates)
            rowCandidates.remove(piece);

        result.add(secondRowCandidates.get(0));
        solutionBoard.add(result);
        secondRowCandidates.remove(0);
        for (Piece piece : secondRowCandidates) {
            pieces.add(piece);
        }
        if (pieces.size() > 0 && pieces.size() < initialPieces) {
            solveArow(pieces, result);
        } else if (pieces.size() > 0 && pieces.size() == initialPieces) {
            if (result.size() > 0) {
                pieces.add(result.get(result.size() - 1));
                result.remove(result.get(result.size() - 1));
                solutionBoard.get(solutionBoard.size() - 1).remove(getLastPieceInThisRow(solutionBoard));
                solveArow(pieces, result);
            } else {
                solvable = false;
            }
        }

        if (result.size() == width) {
            solvable = true;
        } else
            solvable = false;

    }

    public boolean isSolvable() {
        trySolvePuzzle();
        return solvable;
    }

}
