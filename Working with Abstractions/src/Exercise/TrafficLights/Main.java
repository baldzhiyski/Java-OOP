package Exercise.TrafficLights;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Color[] colors = Arrays.stream(scan.nextLine().split("\\s+"))
                .map(Color::valueOf).toArray(Color[]::new);
        int times = Integer.parseInt(scan.nextLine());

        List<TrafficLight> trafficLightList = new ArrayList<>();

        for (Color color : colors) {
            TrafficLight trafficLight = new TrafficLight(color);
            trafficLightList.add(trafficLight);
        }

        for (int i = 0; i < times; i++) {
            for (TrafficLight trafficLight : trafficLightList) {
                trafficLight.changeColor();
                System.out.print(trafficLight.getColor() + " ");
            }
            System.out.println();
        }

    }
}
