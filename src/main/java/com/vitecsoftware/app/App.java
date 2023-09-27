package com.vitecsoftware.app;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        try {

            if (args.length > 0) {
                Path path = Paths.get(args[0].toString());
                String[] data = Files.readAllLines(path).toArray(new String[Files.readAllLines(path).size()]);
                Puzzle puzzle = new Puzzle(data);

                if (puzzle.isSolvable()) {
                    int numberOfSolutions = puzzle.getSolutionsNumber();
                    System.out.println("The puzzle is solvable with " + numberOfSolutions + " solution(s).");
                } else {
                    System.out.println("The puzzle is unsolvable.");
                }

            } else {
                System.out.println("please give the file path as only argument to the app!");
                System.out.println("Example:");
                System.out.println("java -jar puzzle-assignment-1.0-SNAPSHOT.jar path/to/the/puzzle.pzl");
            }

        } catch (Throwable e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }

    }
}
