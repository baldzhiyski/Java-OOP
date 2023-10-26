package PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] coordinates = readArray(scan);
        Rectangle rectangle = new Rectangle(coordinates[0],coordinates[1],coordinates[2],
                coordinates[3]);
        int times = Integer.parseInt(scan.nextLine());

        while (times-->0){
            int[] arr = readArray(scan);
            Point point = new Point(arr[0],arr[1]);
            System.out.println(rectangle.contains(point));
        }
    }

    private static int[] readArray(Scanner scan) {
        return Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
