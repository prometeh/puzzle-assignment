package com.vitecsoftware.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Puzzle {
    private ArrayList<ArrayList<Piece>> solutionBoard;
    private ArrayList<Piece> usedPieces;
    private ArrayList<Piece> pieces;
    private ArrayList<Piece> untouchedPieces;
    private int width;
    private int height;
    private int totalPieces;
    private boolean solvable;

    public Puzzle(String[] data) {
        this.pieces = getPieces(data);
        this.untouchedPieces = getPieces(data);
        this.usedPieces = new ArrayList<>();
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

        for (int i = 0; i < height; i++) {
            ArrayList<Piece> rowResult = new ArrayList<>();
            solveRow(pieces, rowResult);
            if (!solvable) {
                break;
            }
        }
    }

    private int calculateWidth(ArrayList<Piece> pieces) {
        return (int) pieces.stream().filter(p -> p.getTop() == EdgeType.STRAIGHT).count();
    }

    private int calculateHeight(ArrayList<Piece> pieces) {
        return (int) pieces.stream().filter(p -> p.getLeft() == EdgeType.STRAIGHT).count();
    }

    private EdgeType getExpectedEdgeType(ArrayList<ArrayList<Piece>> solutionBoard, ArrayList<Piece> result,
            Direction direction) {
        int upperRowIndex = solutionBoard.size() - 1;
        int upperPieceIndex = 0;
        int edgeValue = 0;
        if (direction == Direction.UP) {
            if (upperRowIndex < 0) {
                return EdgeType.STRAIGHT;
            } else if (result.size() != width) {
                upperPieceIndex = result.size();
            }

            edgeValue = solutionBoard.get(upperRowIndex).get(upperPieceIndex).getDown().value * -1;

        } else { // if (direction == Direction.LEFT)
            if (result.isEmpty()) {
                return EdgeType.STRAIGHT;
            }
            edgeValue = result.get(result.size() - 1)
                    .getRight().value * -1;
        }

        if (edgeValue == EdgeType.INWARD.value) {
            return EdgeType.INWARD;
        } else
            return EdgeType.OUTWARD;
    }

    private void solveRow(ArrayList<Piece> pieces, ArrayList<Piece> result) {
        ArrayList<Piece> rowCandidatesA = new ArrayList<>();
        ArrayList<Piece> rowCandidatesB = new ArrayList<>();
        int initialPieces = pieces.size();

        for (Piece piece : pieces) {
            if (piece.getTop() == getExpectedEdgeType(solutionBoard, result, Direction.UP)) {
                rowCandidatesA.add(piece);
            }
        }

        for (Piece piece : rowCandidatesA) {
            pieces.remove(piece);
            if (piece.getLeft() == getExpectedEdgeType(solutionBoard, result, Direction.LEFT)) {
                rowCandidatesB.add(piece);
            } else {
                pieces.add(piece);
            }
        }

        for (Piece piece : rowCandidatesB) {
            rowCandidatesA.remove(piece);
        }

        if (!rowCandidatesB.isEmpty()) {
            result.add(rowCandidatesB.get(0));
            rowCandidatesB.remove(0);

        }
        pieces.addAll(rowCandidatesB);

        if (result.size() == width) {
            solvable = true;

            // checks if all the lower side of last row has straight edge type
            if (solutionBoard.size() + 1 == height) {
                for (Piece piece : result) {
                    if (!piece.getDown().equals(EdgeType.STRAIGHT)) {
                        solvable = false;
                        break;
                    }
                }
            }

            // checks if the right side of last piece in the row is straight
            if (!result.get(result.size() - 1).getRight().equals(EdgeType.STRAIGHT)) {
                solvable = false;
            }

            if (solvable) {
                solutionBoard.add(result);
            }

        } else if (!pieces.isEmpty() && pieces.size() < initialPieces) {
            solveRow(pieces, result);
        } else if (!pieces.isEmpty() && pieces.size() == initialPieces) {
            Piece unfittingPiece = result.get(result.size() - 1);
            if (!result.isEmpty() && !usedPieces.contains(unfittingPiece)) {
                usedPieces.add(unfittingPiece);
                pieces.add(unfittingPiece);
                result.remove(unfittingPiece);
                solveRow(pieces, result);

            } else {
                solvable = false;
            }
        } else {
            solvable = false;
        }

        usedPieces.clear();
    }

    public boolean isSolvable() {
        trySolvePuzzle();
        return solvable;
    }

    private int getFactorial(int n) {
        if (n <= 1) {
            return 1;
        } else {
            return n * getFactorial(n - 1);
        }
    }

    public int getSolutionsNumber() {
        int noOfSolutions = 0;

        if (solvable) {
            for (String pieceName : untouchedPieces.stream().map(Piece::getName).distinct()
                    .toList()) {
                int frequency = Collections.frequency(
                        untouchedPieces.stream().map(Piece::getName).collect(Collectors.toList()), pieceName);
                if (frequency > 1) {
                    noOfSolutions += getFactorial(frequency);
                }
            }
            System.out.println();

            if (noOfSolutions == 0) {
                return 1;
            } else {
                return noOfSolutions;
            }
        } else {
            return noOfSolutions;
        }
    }

}
