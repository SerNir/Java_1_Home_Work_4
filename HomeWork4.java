import java.util.Random;
import java.util.Scanner;

public class HomeWork4  {

    private static char[][] gameMap;
    public static Scanner sc = new Scanner(System.in);

    private static final int DOTS_TO_WIN = 4;
    private static final char EMPTY_DOT = '*';
    private static final char X_DOT = 'X';
    private static final char O_DOT = 'O';

    public static void main(String[] args) throws InterruptedException {

        startGame();
    }

    private static void startGame() throws InterruptedException {
        initGameMap(5, 5);
        paintGameMap();
        int count = 0;

        while (true) {
            humanTurn();
            count++;
            paintGameMap();
            if (checkWin(X_DOT)) {
                System.out.println("You Win!");
                break;
            }
            if (count == gameMap.length * gameMap[0].length) {
                System.out.println("FRENDLY WIN");
            }

            computerTurn();

            count++;
            paintGameMap();
            if (checkWin(O_DOT)) {
                System.out.println("PC Win!");
            }
            if (count == gameMap.length * gameMap[0].length) {
                System.out.println("FRENDLY WIN");
            }
        }
    }

    private static boolean checkWin(char c) {
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                if (checkLine(i, j, 0, 1,  c)) return true;
                if (checkLine(i, j, 1, 1,  c)) return true;
                if (checkLine(i, j, 1, 0,  c)) return true;
                if (checkLine(i, j, -1, 1, c)) return true;
            }

        }

        return false;
    }

    private static boolean checkLine(int y, int x, int vy, int vx, char c) {
        int wayX = x + (DOTS_TO_WIN - 1) * vx;
        int wayY = y + (DOTS_TO_WIN - 1) * vy;
        if (wayX < 0 || wayY < 0 || wayX > gameMap.length - 1 || wayY > gameMap.length - 1) return false;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            int itemY = y + i * vy;
            int itemX = x + i * vx;
            if (gameMap[itemY][itemX] != c) return false;
        }
        return true;
    }

    private static void computerTurn() throws InterruptedException {
        Thread.sleep(1000);
        int x, y;
        boolean checkCell;
        Random random = new Random();

        do {
            x = random.nextInt(gameMap.length);
            y = random.nextInt(gameMap.length);
            checkCell = isValidCell(x, y);
        } while (!checkCell);
        gameMap[x][y] = O_DOT;
    }

    private static void humanTurn() {
        int x = -1;
        int y = -1;
        boolean checkCell;
        do {
            System.out.println("Введите координаты: в формате Х У");
            if (sc.hasNextInt()) {
                x = sc.nextInt() - 1;
            }
            if (sc.hasNextInt()) {
                y = sc.nextInt() - 1;
            }

            checkCell = isValidCell(x, y);
            sc.nextLine();

        } while (!checkCell);
        gameMap[x][y] = X_DOT;
    }

    private static void paintGameMap() {
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                System.out.print(gameMap[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void initGameMap(int length, int width) {
        if (length < 3 || width < 3){
            System.out.println("You stupid man");
            System.exit(0);
        }
        gameMap = new char[length][width];
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                gameMap[i][j] = EMPTY_DOT;
            }
        }
    }

    private static boolean isValidCell(int x, int y) {
        if (x < 0 || y < 0 || x > gameMap.length || y > gameMap[0].length) {
            return false;
        }
        return gameMap[x][y] == EMPTY_DOT;
    }
}

