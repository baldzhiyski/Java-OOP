package christmasPastryShop.entities.delicacies.interfaces;

import christmasPastryShop.common.ExceptionMessages;

public abstract class BaseDelicacy implements Delicacy{
    private String name;
    private double portion;
    private double price;

    public BaseDelicacy(String name, double portion, double price) {
       setName(name);
       setPortion(portion);
       setPrice(price);
    }

    public void setName(String name) {
        if(name==null || name.isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NAME);
        }
        this.name = name;
    }

    public void setPortion(double portion) {
        if(portion<=0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PORTION);
        }
        this.portion = portion;
    }

    public void setPrice(double price) {
        if(price<=0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PRICE);
        }
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPortion() {
        return portion;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f - %.2f",name,portion,price);
    }
}
