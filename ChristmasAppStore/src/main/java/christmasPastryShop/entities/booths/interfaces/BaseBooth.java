package christmasPastryShop.entities.booths.interfaces;

import christmasPastryShop.common.ExceptionMessages;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;

public  abstract class BaseBooth implements Booth{
    private Collection<Delicacy> delicacyOrders;
    private Collection<Cocktail> cocktailOrders;
    private int boothNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;
    public BaseBooth(int boothNumber, int capacity, double pricePerPerson) {
        this.boothNumber = boothNumber;
        setCapacity(capacity);
        this.numberOfPeople = 0;
        this.pricePerPerson = pricePerPerson;
        this.isReserved = false;
        this.price = 0;
        cocktailOrders = new ArrayList<>();
        delicacyOrders = new ArrayList<>();
    }
    public void setCapacity(int capacity) {
        if(capacity<=0) {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;
    }
    public void setNumberOfPeople(int numberOfPeople) {
        if(numberOfPeople<=0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public int getBoothNumber() {
        return boothNumber;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isReserved() {
        return isReserved;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public double getPricePerPerson() {
        return pricePerPerson;
    }

    public Collection<Cocktail> getCocktailOrders() {
        return cocktailOrders;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public Collection<Delicacy> getDelicacyOrders() {
        return delicacyOrders;
    }

    public void setPrice(double price) {
        this.price=price;
    }

    public void setCocktailOrders(Collection<Cocktail> cocktailOrders) {
        this.cocktailOrders = cocktailOrders;
    }

    public void setBoothNumber(int boothNumber) {
        this.boothNumber = boothNumber;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }

    public void setDelicacyOrders(Collection<Delicacy> delicacyOrders) {
        this.delicacyOrders = delicacyOrders;
    }
    @Override
    public void reserve(int numberOfPeople) {
        setReserved(true);
        setNumberOfPeople(numberOfPeople);
        this.price=numberOfPeople*pricePerPerson;
    }

    @Override
    public double getBill() {
        return cocktailOrders.stream().mapToDouble(Cocktail::getPrice).sum()
                + delicacyOrders.stream().mapToDouble(Delicacy::getPrice).sum()
                + getPrice();
    }

    @Override
    public void clear() {
        cocktailOrders.forEach(cocktail -> cocktailOrders.remove(cocktail));
        delicacyOrders.forEach(delicacy -> delicacyOrders.remove(delicacy));
        this.price= 0;
        this.numberOfPeople=0;
        setReserved(false);
    }
}
