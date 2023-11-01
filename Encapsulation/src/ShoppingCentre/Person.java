package ShoppingCentre;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person implements Identity {
    private String name;
    private double money;
    private List<Product> productList;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.productList = new ArrayList<>();
    }

    private void setMoney(double money) {
        Validator.validateMoney(money);
        this.money=money;
    }

    public List<Product> getProductList() {
        return productList;
    }

    private void setName(String name) {
        Validator.validateName(name);
        this.name=name;
    }
    public void buyProduct(Product product){
        money-=product.getCost();
        getProductList().add(product);
    }

    public double getMoney() {
        return money;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s - %s",
                name,
                productList.isEmpty() ? "Nothing bought"
                : productList.stream().map(Product::getName)
                        .collect(Collectors.joining(", ")));
    }
}
