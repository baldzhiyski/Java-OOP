package Exercise.jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = readingCoordinates(scanner.nextLine());
        int row = dimensions[0];
        int col = dimensions[1];

        int[][] matrix=creatingMatrix(row,col);
        long sum = 0;
        String command = scanner.nextLine();
        while (!command.equals("Let the Force be with you")) {
            int[] jediCoordinates = readingCoordinates(command);
            int[] evilCoordinates = readingCoordinates(scanner.nextLine());
            int rowOfEvil = evilCoordinates[0];
            int colOfEvil = evilCoordinates[1];

            moveEvil(matrix, rowOfEvil, colOfEvil);

            int rowOfJedi = jediCoordinates[0];
            int colOfJedi = jediCoordinates[1];

            long getCollectedStars = getSum(matrix,colOfJedi, rowOfJedi);
            sum+=getCollectedStars;
            command = scanner.nextLine();
        }

        System.out.println(sum);


    }

    public static long getSum(int[][] matrix, int colOfJedi, int rowOfJedi) {
        long countStars = 0;
        while (rowOfJedi >= 0 && colOfJedi < matrix[1].length) {
            if (rowOfJedi<matrix.length && colOfJedi>=0 && colOfJedi<matrix[0].length) {
                countStars += matrix[rowOfJedi][colOfJedi];
            }

            colOfJedi++;
            rowOfJedi--;
        }
        return countStars;
    }

    public static void moveEvil(int[][] matrix, int rowOfEvil, int colOfEvil) {
        while (rowOfEvil >= 0 && colOfEvil >= 0) {
            if (isValid(matrix, rowOfEvil, colOfEvil)) {
                matrix[rowOfEvil][colOfEvil] = 0;
            }
            rowOfEvil--;
            colOfEvil--;
        }
    }

    public static boolean isValid(int[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
    }

    public static int[] readingCoordinates(String command) {
        return Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static int[][] creatingMatrix(int row, int col){
        int[][] result = new int[row][col];
        int value = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                result[i][j] = value++;
            }
        }
        return result;
    }
}
