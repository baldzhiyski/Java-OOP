package food;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        Map<String,Buyer> buyerMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] tokens = scan.nextLine().split("\\s+");
            if(tokens.length==3){
                Rebel rebel = new Rebel(tokens[0],Integer.parseInt(tokens[1]),tokens[2]);
                buyerMap.put(tokens[0],rebel);
            }else{
                Citizen citizen = new Citizen(tokens[0],Integer.parseInt(tokens[1]),tokens[2]
                ,tokens[3]);
                buyerMap.put(tokens[0],citizen);
            }
        }
        String line = scan.nextLine();
        while (!line.equals("End")){
            String name = line;
            Buyer buyer = buyerMap.get(name);
            if(buyer!=null){
                buyer.buyFood();
            }
            line=scan.nextLine();
        }
        int sum = buyerMap.values().stream()
                .mapToInt(Buyer::getFood).sum();
        System.out.println(sum);
    }
}
