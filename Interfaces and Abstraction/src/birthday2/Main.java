package birthday2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();
        List<Birthable> list = new ArrayList<>();

        while (!line.equals("End")){
            String[] tokens = line.split("\\s+");
            switch (tokens[0]){
                case "Citizen":
                    list.add(new Citizen(tokens[1],Integer.parseInt(tokens[2]),
                            tokens[3],tokens[4]));
                    break;
                case "Pet":
                    list.add(new Pet(tokens[1],tokens[2]));
                    break;
            }
            line=scan.nextLine();
        }
        String year = scan.nextLine();
        list.stream()
                .filter(o->o.getBirthDate().endsWith(year))
                .forEach(o-> System.out.println(o.getBirthDate()));
    }
}
