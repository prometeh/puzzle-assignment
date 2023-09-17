package com.vitecsoftware.app;

public class Piece {
    private String name;
    private EdgeType top;
    private EdgeType right;
    private EdgeType down;
    private EdgeType left;

    public Piece(String name) {
        setName(name);
        setTop();
        setRight();
        setDown();
        setLeft();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) throws IllegalArgumentException {
        if (name.length() != 4) {
            throw new IllegalArgumentException("The length of argument/name should be 4 charachters!");
        } else if (!name.matches("^[IUR]*$")) {
            throw new IllegalArgumentException("The augument/name should be contained of only R,U and/or Is!");
        }
        this.name = name;
    }

    public EdgeType getTop() {
        return top;
    }

    private void setTop() {
        this.top = getType(name.charAt(0));
    }

    public EdgeType getRight() {
        return right;
    }

    private void setRight() {
        this.right = getType(name.charAt(1));
    }

    public EdgeType getDown() {
        return down;
    }

    private void setDown() {
        this.down = getType(name.charAt(2));
    }

    public EdgeType getLeft() {
        return left;
    }

    private void setLeft() {
        this.left = getType(name.charAt(3));
    }

    private EdgeType getType(char letter) {
        EdgeType result = null;

        switch (letter) {
            case 'R':
                result = EdgeType.STRAIGHT;
                break;
            case 'U':
                result = EdgeType.OUTWARD;
                break;
            case 'I':
                result = EdgeType.INWARD;
                break;
        }

        return result;
    }

}
