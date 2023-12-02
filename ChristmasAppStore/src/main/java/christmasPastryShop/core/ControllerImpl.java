package christmasPastryShop.core;

import christmasPastryShop.common.ExceptionMessages;
import christmasPastryShop.common.OutputMessages;
import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.interfaces.OpenBooth;
import christmasPastryShop.entities.booths.interfaces.PrivateBooth;
import christmasPastryShop.entities.cocktails.interfaces.Hibernation;
import christmasPastryShop.entities.cocktails.interfaces.MulledWine;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.delicacies.interfaces.Gingerbread;
import christmasPastryShop.entities.delicacies.interfaces.Stolen;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;


public class ControllerImpl implements Controller {
    private final DelicacyRepository<Delicacy> delicacyRepository;
    private final CocktailRepository<Cocktail> cocktailRepository;
    private final BoothRepository<Booth> boothRepository;
    private double totalIncome;



    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository, CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository) {
        this.delicacyRepository=delicacyRepository;
        this.cocktailRepository=cocktailRepository;
        this.boothRepository=boothRepository;
        this.totalIncome =0;
    }


    @Override
    public String addDelicacy(String type, String name, double price) {
        Delicacy delicacy = delicacyRepository.getByName(name);
        if(delicacy==null) {
            switch (type) {
                case "Stolen":
                    delicacy = new Stolen(name, price);
                    break;
                case "Gingerbread":
                    delicacy = new Gingerbread(name, price);
                    break;
            }
        }else{
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST,
                    type,name));
        }
        delicacyRepository.add(delicacy);
        return String.format(OutputMessages.DELICACY_ADDED,name,type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        Cocktail cocktail= cocktailRepository.getByName(name);
        if(cocktail==null) {
            switch (type) {
                case "MulledWine":
                    cocktail = new MulledWine(name, size, brand);
                    break;
                case "Hibernation":
                    cocktail = new Hibernation(name, size, brand);
                    break;
            }
        }else {
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST,
                    type,name));
        }
        cocktailRepository.add(cocktail);
        return String.format(OutputMessages.COCKTAIL_ADDED,name,brand);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        Booth booth = boothRepository.getByNumber(boothNumber);
        if(booth!=null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.BOOTH_EXIST,boothNumber));
        }
        switch (type){
            case "OpenBooth":
                booth= new OpenBooth(boothNumber,capacity);
                break;
            case "PrivateBooth":
                booth = new PrivateBooth(boothNumber,capacity);
                break;
        }
        boothRepository.add(booth);
        return String.format(OutputMessages.BOOTH_ADDED,boothNumber);
    }

    @Override
    public String reserveBooth(int numberOfPeople) {
        Booth booth = boothRepository.getAll().stream().filter(b -> !b.isReserved() && b.getCapacity() >= numberOfPeople)
                .findFirst().orElse(null);
        if(booth==null){
            return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE,numberOfPeople);
        }
        booth.reserve(numberOfPeople);
        return String.format(OutputMessages.BOOTH_RESERVED,booth.getBoothNumber(),numberOfPeople);
    }

    @Override
    public String leaveBooth(int boothNumber) {
        Booth booth = boothRepository.getAll().stream().filter(b -> b.getBoothNumber() >= boothNumber)
                .findFirst().orElse(null);
        double bill = booth.getBill();
        booth.clear();
        totalIncome+=bill;
        return  String.format(OutputMessages.BILL, boothNumber, bill);
    }

    @Override
    public String getIncome() {
        return String.format(OutputMessages.TOTAL_INCOME, this.totalIncome);
    }
}
