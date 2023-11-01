package ShoppingCentre;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LinkedHashMap<String,Person> people = readMapOf(scan,Person::new);
        LinkedHashMap<String,Product> products = readMapOf(scan,Product::new);

        String input = scan.nextLine();
        while (!input.equalsIgnoreCase("end")){
            String[] arr = input.split("\\s+");
            Person person = people.get(arr[0]);
            Product product = products.get(arr[1]);
            if(person!=null && product!=null) {
                buyProduct(person, product);
            }
            input=scan.nextLine();
        }
        System.out.println(people.values().stream()
                .map(Person::toString)
                .collect(Collectors.joining(System.lineSeparator())));

    }

    private static void buyProduct(Person person, Product product) {
        if (person.getMoney() >= product.getCost()) {
            person.buyProduct(product);
            System.out.println(person.getName() + " bought " + product.getName());
        } else {
            System.out.println(person.getName() + " can't afford " + product.getName());
        }
    }

    private static <T extends Identity> LinkedHashMap<String, T> readMapOf(Scanner scan, BiFunction<String,Double,T> constructor){
        return Arrays.stream(scan.nextLine().split(";"))
                .map(s->createEntity(s,constructor))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toMap(Identity::getName,p->p,(x,y)->y,LinkedHashMap::new));
    }

    private static <T> Optional<T> createEntity(String data, BiFunction<String, Double, T> constructor) {
        String[] tokens = data.split("=");
        String name = tokens[0];
        double money = Double.parseDouble(tokens[1]);

        Optional<T> entity = Optional.empty();
        try {
            entity=Optional.of(constructor.apply(name,money));
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
        return entity;
    }
}
