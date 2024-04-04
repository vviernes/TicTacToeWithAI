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

    private static int[][][] gameboard = new int[BOARD_X_DIMENSION][BOARD_Y_DIMENSION][2];
    private static Map<String, Character> map = new HashMap<>();

    public static void main(String[] args) {
        // Initialize or configure the board
        configBoard();
        String initialState = initializeGameState();
        createBoardMapping(initialState);
        printBoard();
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

        Scanner scanner = new Scanner(System.in);
        String initialState;
        do {
            System.out.println("Enter the cells");
            initialState = scanner.nextLine();
        // checks if the string contains only valid chars and is exactly of length 9
        } while (!initialState.matches("[XO_]{9}"));

        scanner.close();
        return initialState;
    }

    public static void createBoardMapping(String initialState) {
        for (int i = 0; i < BOARD_X_DIMENSION; i++) {
            for (int j = 0; j < BOARD_Y_DIMENSION; j++) {
                map.put(Arrays.toString(gameboard[i][j]), initialState.toCharArray()[i * BOARD_Y_DIMENSION + j]);
            }
        }
    }

    // Method to print the board
    public static void printBoard() {
        System.out.print("---------\n");    //Print top border
        for (int i = 0; i < BOARD_X_DIMENSION; i++) {
            System.out.print("| "); // Print board's left edge
            for (int j = 0; j < BOARD_Y_DIMENSION; j++) {
                char mapValue = map.get(Arrays.toString(gameboard[i][j]));
                System.out.print(mapValue + " ");
            }
            System.out.println("|"); // Print board's right edge and move to next line
        }
        System.out.print("---------\n");    //Print bottom border
    }
}
