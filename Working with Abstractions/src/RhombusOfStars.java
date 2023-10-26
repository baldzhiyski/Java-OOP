import java.util.Scanner;

public class RhombusOfStars {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int size = Integer.parseInt(scan.nextLine());

        printRhombus(size);
    }

    private static void printRhombus(int size) {
        for (int i = 0; i < size; i++) {
            printRow(size,i);
        }
        for (int i = size; i >=1 ; i--) {
            printRow(size,i);
        }
    }

    private static void printRow(int size, int i) {
        for (int s = 0; s < size-i; s++) {
            System.out.print(" ");
        }
        for (int a = 0; a < i; a++) {
            System.out.print("* ");
        }
        System.out.println();
    }
}