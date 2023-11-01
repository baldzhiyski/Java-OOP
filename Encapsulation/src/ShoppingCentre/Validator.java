package ShoppingCentre;

public class Validator {
    private Validator(){

    }
    public static void validateName(String name){
        if(name.isBlank() || name==null){
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
    public static void validateMoney(double money){
        if(money<0){
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }
}
