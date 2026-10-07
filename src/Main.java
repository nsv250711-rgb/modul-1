import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int step = 0;
        int personLive = 3;
        int sizeBoard = 10;
        int personX = (sizeBoard) / 2;
        int personY = (sizeBoard) / 2;

        String person = "\uD83E\uDDD9";
        String BigMonster = "\uD83E\uDDDB";
        String monster = "\uD83E\uDDDF";
        String castle = "\uD83D\uDC7E";
        String begin = "|    | |";
        String end = " |    |";
        String leftBlock = " | ";
        String rightBlock = " |";
        String wall = " + —— + —— + —— + —— + —— + —— + —— + —— + —— + —— + ";
        String full = begin + monster + end;
        String[][] board = new String[sizeBoard][sizeBoard];

        for (int y = 1; y <= sizeBoard; y++) {
            for (int x = 1; x <= sizeBoard; x++) {
                board[y - 1][x - 1] = "  ";
            }
        }
        int countMonster = sizeBoard * sizeBoard - sizeBoard - 1;
        Random random = new Random();
        for (int i = 0; i < countMonster; i++) {
            board[random.nextInt(sizeBoard - 1)]
                    [random.nextInt(sizeBoard)] = monster;
        }




        int castleX = 1 + random.nextInt(sizeBoard);
        int castleY = 1;

        board[castleY][castleX] = castle;
        System.out.println("Добро пожаловать! Ты готов начать играть в игру? (Напиши: ДА или НЕТ)");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        System.out.println("Ваш ответ:\t" + answer);
        switch (answer) {
            case "ДА":
                System.out.println("Выбери сложность игры (от 1 до 3):");
                int difficultGame = scanner.nextInt();
                if (difficultGame == 3) {
                    personLive = 1;
                }
                if (difficultGame == 2) {
                    personLive = 2;
                }
                System.out.println("Выбранная сложность:\t" + difficultGame);

                while (true) {
                    board[personY - 1][personX - 1] = person;

                    for (String[] row : board) {
                        System.out.println(wall);

                        for (String cell : row) {
                            System.out.print(leftBlock);
                            System.out.print(cell);
                        }

                        System.out.println(rightBlock);
                    }

                    System.out.println(wall);

                    System.out.println("Количество жизней:\t"+ personLive + "\n");

                    System.out.println("Введите куда будет ходить персонаж (ход возможен только по вертикали и горизонтали на одну клетку)" + "\nКоординаты персонажа - (x: " + personX + ", y: " + personY + ")");

                    int x = scanner.nextInt();
                    int y = scanner.nextInt();

                    System.out.println(x + ", " + y);

                    if (x != personX && y != personY) {
                        System.out.println("Некорректный ход");
                    } else if (
                            Math.abs(x - personX) == 1 || Math.abs(y - personY) == 1
                    ) {
                        if (board[y - 1][x - 1].equals("  ")) {
                            board[personY - 1][personX - 1] = "  ";

                            personX = x;
                            personY = y;
                            step++;

                            System.out.println("Ход корректный; Новые координаты: " + personX + ", " + personY + "\nХод номер: " + step);


                        } else {
                            System.out.println("Решите задачу.");
                        }
                    } else {
                        System.out.println("Координаты не изменены");
                    }

                    if (personLive <= 0) {
                        break;
                    }

                    if (board[y - 1][x - 1].equals("  ")) {
                        board[personY - 1][personX - 1] = "  ";
                        personX = x;
                        personY = y;
                        step++;
                        System.out.println("Ход корректный; Новые координаты: " + personX + ", " + personY +
                                "\nХод номер: " + step);
                    } else if (board[y - 1][x - 1].equals(castle)) {
                        System.out.println("Вы прошли игру! Поздравляю!");
                        break;
                    } else {
                        int a = random.nextInt(100);
                        int b = random.nextInt(100);
                        int trueAnswer = a + b;
                        System.out.println("Реши пример: " + a + " + " + b + " = ?");
                        int ans = scanner.nextInt();
                        if (trueAnswer == ans) {
                            System.out.println("Верно! Ты победил монстра");
                        } else {
                            System.out.println("Ты проиграл эту битву!");
                            personLive--;
                            if (board[y - 1][x - 1].equals("  ")) {
                                board[personY - 1][personX - 1] = "  ";
                                personX = x;
                                personY = y;
                                step++;
                            }
                        }
                    }

                }

                break;

            case "НЕТ":
                System.out.println("Жаль, приходи ещё!");
                break;

            default:
                System.out.println("Данные введены некорректно");
                break;

        }
    }
    private static boolean taskMonster() {
        return false;
    }


    static void outputBoard(String[][] board, int live) {
            String leftBlock = "| ";
            String rightBlock = "|";
            String wall = "+ —— + —— + —— + —— + —— +";

            for (String[] raw : board) {
                System.out.println(wall);
                for (String col : raw) {
                    System.out.print(leftBlock + col + " ");
                }
                System.out.println(rightBlock);
            }
            System.out.println(wall);


            System.out.println("Количество жизней:\t" + live + "\n");
    }
}