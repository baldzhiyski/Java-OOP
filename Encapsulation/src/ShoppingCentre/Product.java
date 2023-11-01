package ShoppingCentre;
public class Product implements Identity{
   private String name;
   private double cost;

    public Product(String name, double cost) {
        setName(name);
       setCost(cost);
    }

    private void setName(String name) {
        Validator.validateName(name);
        this.name = name;
    }

    private void setCost(double cost) {
        Validator.validateMoney(cost);
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }
    @Override
    public String getName() {
        return name;
    }
}
