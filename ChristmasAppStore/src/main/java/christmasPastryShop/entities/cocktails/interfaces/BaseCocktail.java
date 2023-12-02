package christmasPastryShop.entities.cocktails.interfaces;

import christmasPastryShop.common.ExceptionMessages;

public abstract class BaseCocktail implements Cocktail{
    private String name;
    private int size;
    private double price;
    private String brand;

    public BaseCocktail(String name, int size, double price, String brand) {
        setName(name);
        setSize(size);
        setPrice(price);
        setBrand(brand);
    }

    public void setName(String name) {
        if(name.isBlank()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NAME);
        }
        this.name = name;
    }

    public void setPrice(double price) {
        if(price<=0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        this.price = price;
    }

    public void setBrand(String brand) {
        if(brand.isBlank()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_BRAND);
        }
        this.brand = brand;
    }

    public void setSize(int size) {
        if(size<=0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_SIZE);
        }
        this.size = size;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return String.format("%s %s - %dml - %.2f",name,brand,size,price);
    }
}
