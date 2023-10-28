package Exercise.greedyTimes;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        long entrance = Long.parseLong(scanner.nextLine());
        String[] chamber = scanner.nextLine().split("\\s+");

        LinkedHashMap<String,LinkedHashMap<String,Long>> map = new LinkedHashMap<>();
        long gold = 0;
        long stone = 0;
        long money = 0;

        for (int i = 0; i < chamber.length; i += 2) {
            String name = chamber[i];
            long count = Long.parseLong(chamber[i + 1]);

            String command = "";

            if (name.length() == 3) {
                command = "Cash";
            } else if (name.toLowerCase().endsWith("gem")) {
                command = "Gem";
            } else if (name.toLowerCase().equals("gold")) {
                command = "Gold";
            }

            if (command.equals("")) {
                continue;
            } else if (entrance < map.values().stream().map(Map::values).flatMap(Collection::stream).mapToLong(e -> e).sum() + count) {
                continue;
            }

            switch (command) {
                case "Gem":
                    if (!map.containsKey(command)) {
                        if (map.containsKey("Gold")) {
                            if (count > map.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (map.get(command).values().stream().mapToLong(e -> e).sum() + count > map.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
                case "Cash":
                    if (!map.containsKey(command)) {
                        if (map.containsKey("Gem")) {
                            if (count > map.get("Gold").values().stream().mapToLong(e -> e).sum()) {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else if (map.get(command).values().stream().mapToLong(e -> e).sum() + count > map.get("Gem").values().stream().mapToLong(e -> e).sum()) {
                        continue;
                    }
                    break;
            }

            if (!map.containsKey(command)) {
                map.put((command), new LinkedHashMap<>());
            }

            if (!map.get(command).containsKey(name)) {
                map.get(command).put(name, 0L);
            }


            map.get(command).put(name, map.get(command).get(name) + count);
            if (command.equals("Gold")) {
                gold += count;
            } else if (command.equals("Gem")) {
                stone += count;
            } else if (command.equals("Cash")) {
                money += count;
            }
        }

        for (var x : map.entrySet()) {
            Long sumValues = x.getValue().values().stream().mapToLong(l -> l).sum();

            System.out.println(String.format("<%s> $%s", x.getKey(), sumValues));

            x.getValue().entrySet().stream().sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())).forEach(i -> System.out.println("##" + i.getKey() + " - " + i.getValue()));

        }
    }
}