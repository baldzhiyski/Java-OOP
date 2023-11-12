package vehicles;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String[] tokensCar = scan.nextLine().split("\\s+");
        Vehicle vehicle1 = new Car(Double.parseDouble(tokensCar[1]),Double.parseDouble(tokensCar[2]),
                Double.parseDouble(tokensCar[3]));

        String[] tokensTruck = scan.nextLine().split("\\s+");
        Vehicle vehicle2 = new Truck(Double.parseDouble(tokensTruck[1]),Double.parseDouble(tokensTruck[2])
        ,Double.parseDouble(tokensTruck[3]));

        String[] tokensBus = scan.nextLine().split("\\s+");
        Vehicle vehicle3 = new Bus(Double.parseDouble(tokensBus[1]),Double.parseDouble(tokensBus[2])
                ,Double.parseDouble(tokensBus[3]));

        Map<String,Vehicle> vehicleMap = new LinkedHashMap<>(Map.of("Car",vehicle1,
                "Truck",vehicle2,
                "Bus",vehicle3));

        int n = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < n; i++) {
            String[] commandsArr = scan.nextLine().split("\\s+");
            double km = Double.parseDouble(commandsArr[2]);
            Vehicle vehicle = vehicleMap.get(commandsArr[1]);
            try {
                switch (commandsArr[0]) {
                    case "Drive":
                        vehicle.setEmpty(false);
                        vehicle.turnOnOfAC(vehicle.isEmpty());
                        System.out.println(vehicle.drive(km));
                        break;
                    case "Refuel":
                        vehicle.refuel(km);
                        break;
                    case "DriveEmpty":
                        vehicle.setEmpty(true);
                        vehicle.turnOnOfAC(vehicle.isEmpty());
                        System.out.println(vehicle.drive(km));
                        break;
                }
            }catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println(vehicle1);
        System.out.println(vehicle2);
        System.out.println(vehicle3);
    }
}
