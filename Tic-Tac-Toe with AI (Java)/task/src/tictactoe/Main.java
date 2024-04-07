package tictactoe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    // GLOBAL CONSTANTS
    private static final int BOARD_X_DIMENSION = 3;
    private static final int BOARD_Y_DIMENSION = 3;
    private static final int BOARD_LENGTH = BOARD_X_DIMENSION * BOARD_Y_DIMENSION;
    private final static Scanner scanner = new Scanner(System.in);

    private static int[][][] gameboard = new int[BOARD_X_DIMENSION][BOARD_Y_DIMENSION][2];
    private static Map<String, Character> moveMapping = new HashMap<>();

    public static void main(String[] args) {
        // Initialize or configure the board
        configBoard();
        String initialState = initializeGameState();
        createBoardMapping(initialState);
        printBoard();

        getMove();
        printBoard();

        boolean isGameOver = checkWinCondition();
        System.out.println(isGameOver);
        scanner.close();
    }

    // Configure the gameboard
    public static void configBoard() {
        for (int i = 0; i < BOARD_X_DIMENSION; i++) {
            for (int j = 0; j < BOARD_Y_DIMENSION; j++) {
                // Initialize each cell with their coordinate value
                gameboard[i][j] = new int[] {i + 1, j + 1};
            }
        }
    }

    // Initialize starting game state via user input
    public static String initializeGameState() {

        String initialState;
        do {
            System.out.println("Enter the cells");
            initialState = scanner.nextLine();
        // checks if the string contains only valid chars and is exactly of length 9
        } while (!initialState.matches("[XO_]{9}"));

        return initialState;
    }

    public static void createBoardMapping(String initialState) {
        for (int i = 0; i < BOARD_X_DIMENSION; i++) {
            for (int j = 0; j < BOARD_Y_DIMENSION; j++) {
                moveMapping.put(Arrays.toString(gameboard[i][j]), initialState.toCharArray()[i * BOARD_Y_DIMENSION + j]);
            }
        }
    }

    // Method to print the board
    public static void printBoard() {
        System.out.print("---------\n");    //Print top border
        for (int i = 0; i < BOARD_X_DIMENSION; i++) {
            System.out.print("| "); // Print board's left edge
            for (int j = 0; j < BOARD_Y_DIMENSION; j++) {
                char mapValue = moveMapping.get(Arrays.toString(gameboard[i][j]));
                System.out.print(mapValue + " ");
            }
            System.out.println("|"); // Print board's right edge and move to next line
        }
        System.out.print("---------\n");    //Print bottom border
    }

    public static void getMove() {
        System.out.println("Enter the coordinates:");
        String userPlacementLine = scanner.nextLine();
        String[] coordinates = userPlacementLine.split(" ");

        int moveXCoordinate = 0;
        int moveYCoordinate = 0;
        boolean validInput = false;
        boolean validRange = false;

        while (!validInput) {
            try {
                moveXCoordinate = Integer.parseInt(coordinates[0]); // Parse the first part as X
                moveYCoordinate = Integer.parseInt(coordinates[1]); // Parse the second part as Y
                validInput = true; // Parsed successfully, break the loop
            } catch (NumberFormatException e) {
                // If parsing fails, it will throw NumberFormatException
                System.out.println("You should enter numbers!");
                System.out.println("Enter the coordinates:");
                userPlacementLine = scanner.nextLine();
                coordinates = userPlacementLine.split(" ");
            }
        }

        while (!validRange) {
            if (moveXCoordinate < 1 || moveXCoordinate > 3 || moveYCoordinate < 1 || moveYCoordinate > 3) {
                System.out.println("Coordinates should be from 1 to 3!");
                System.out.println("Enter the coordinates:");
                userPlacementLine = scanner.nextLine();
                coordinates = userPlacementLine.split(" ");
                moveXCoordinate = Integer.parseInt(coordinates[0]);
                moveYCoordinate = Integer.parseInt(coordinates[1]);
            } else
                validRange = true;
        }
        moveMapping.put(Arrays.toString(gameboard[moveXCoordinate - 1][moveYCoordinate - 1]), '&');
    }


    public static boolean checkWinCondition() {
        // Get the characters from the gameboard for clarity
        char char00 = moveMapping.get(Arrays.toString(gameboard[0][0]));
        char char01 = moveMapping.get(Arrays.toString(gameboard[0][1]));
        char char02 = moveMapping.get(Arrays.toString(gameboard[0][2]));
        char char10 = moveMapping.get(Arrays.toString(gameboard[1][0]));
        char char11 = moveMapping.get(Arrays.toString(gameboard[1][1]));
        char char12 = moveMapping.get(Arrays.toString(gameboard[1][2]));
        char char20 = moveMapping.get(Arrays.toString(gameboard[2][0]));
        char char21 = moveMapping.get(Arrays.toString(gameboard[2][1]));
        char char22 = moveMapping.get(Arrays.toString(gameboard[2][2]));

        // Check horizontal conditions
        boolean winHorizontalCondition1 = (char00 == char01) && (char00 == char02) && char00 != '_';
        boolean winHorizontalCondition2 = (char10 == char11) && (char10 == char12) && char10 != '_';
        boolean winHorizontalCondition3 = (char20 == char21) && (char20 == char22) && char20 != '_';

        // Check vertical conditions
        boolean winVerticalCondition1 = (char00 == char10) && (char00 == char20) && char00 != '_';
        boolean winVerticalCondition2 = (char01 == char11) && (char01 == char21) && char01 != '_';
        boolean winVerticalCondition3 = (char02 == char12) && (char02 == char22) && char02 != '_';

        // Check diagonal conditions
        boolean winDiagonalCondition1 = (char00 == char11) && (char00 == char22) && char00 != '_';
        boolean winDiagonalCondition2 = (char02 == char11) && (char02 == char20) && char02 != '_';

        // Check if any win condition is met
        return winHorizontalCondition1 || winHorizontalCondition2 || winHorizontalCondition3 ||
                winVerticalCondition1 || winVerticalCondition2 || winVerticalCondition3 ||
                winDiagonalCondition1 || winDiagonalCondition2;
    }

}
