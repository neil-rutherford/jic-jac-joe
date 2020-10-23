import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class hello {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

    public static void main(String[] args) {
        char [] [] gameBoard = {{' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '},
            {'-', '+', '-', '+', '-'},
            {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        while(true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Please enter your placement (1-9)");
            int playerPosition = scan.nextInt();

            while(playerPositions.contains(playerPosition) || cpuPositions.contains(playerPosition)) {
                System.out.println("Position taken! Enter a correct position.");
                playerPosition = scan.nextInt();
            }

            placePiece(gameBoard, playerPosition, "player");
            String result = checkWinner();
            if(result.length() > 0){
                System.out.println(result);
                printGameBoard(gameBoard);
                break;
            }

            java.util.Random random = new Random();
            int cpuPosition = random.nextInt(9) + 1;

            while(playerPositions.contains(cpuPosition) || cpuPositions.contains(cpuPosition)) {
                System.out.println("Processing...");
                cpuPosition = random.nextInt(9) + 1;
            }

            placePiece(gameBoard, cpuPosition, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();

            if(result.length() > 0){
                System.out.println(result);
                break;
            }
        }
    }

    public static void printGameBoard(char[][] gameBoard) {
        for (char[] row : gameBoard) {
            for (char c: row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int position, String user){
        char symbol;

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else {
            symbol = 'O';
            cpuPositions.add(position);
        }

        switch(position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        List leftColumn = Arrays.asList(1, 4, 7);
        List middleColumn = Arrays.asList(2, 5, 8);
        List rightColumn = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);
        winning.add(leftColumn);
        winning.add(middleColumn);
        winning.add(rightColumn);
        winning.add(cross1);
        winning.add(cross2);

        for(List l: winning){
            if (playerPositions.containsAll(l)) {
                return "Congratulations, you won!";
            } else if (cpuPositions.containsAll(l)) {
                return "Oh no, the computer won...";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return "Tie!";
            }
        }

        return "";
    }
}