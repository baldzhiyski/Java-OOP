package catHouse.core;

import catHouse.common.ConstantMessages;
import catHouse.common.ExceptionMessages;
import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.ToyRepository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class ControllerImpl implements Controller{
    private ToyRepository toys;
    private Map<String, House> houses;

    public ControllerImpl() {
        toys = new ToyRepository();
        houses = new LinkedHashMap<>();
    }

    @Override
    public String addHouse(String type, String name) {
        House house;
        switch (type){
            case "LongHouse":
                house = new LongHouse(name);
                break;
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            default:throw new NullPointerException(ExceptionMessages.INVALID_HOUSE_TYPE);
        }
        houses.put(name,house);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_HOUSE_TYPE,type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        switch (type){
            case "Mouse":
                toy = new Mouse();
                break;
            case "Ball":
                toy = new Ball();
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_TOY_TYPE);

        }
        toys.buyToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_TYPE,type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        House house = houses.get(houseName);
        Toy toy=toys.findFirst(toyType);
        if(toy==null){
        throw new IllegalArgumentException(String.format(ExceptionMessages.NO_TOY_FOUND,toyType));
        }
        house.buyToy(toy);
        toys.removeToy(toy);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_TOY_IN_HOUSE,toyType,houseName);
    }
    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        House house = houses.get(houseName);
        Cat cat;
        switch (catType){
            case "LonghairCat":
                cat = new LonghairCat(catName,catBreed,price);
                break;
            case "ShorthairCat":
                cat = new ShorthairCat(catName,catBreed,price);
                break;
            default:throw new IllegalArgumentException(ExceptionMessages.INVALID_CAT_TYPE);
        }
        boolean isUnsuitable = (cat.getClass().getSimpleName().equals("LonghairCat") && house.getClass().getSimpleName().equals("ShortHouse")) ||
                        (cat.getClass().getSimpleName().equals("ShorthairCat") && house.getClass().getSimpleName().equals("LongHouse"));
        if(isUnsuitable){
            return ConstantMessages.UNSUITABLE_HOUSE;
        }
        house.addCat(cat);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_CAT_IN_HOUSE,catType,houseName);
    }
    @Override
    public String feedingCat(String houseName) {
        House house = houses.get(houseName);
        house.getCats().forEach(Cat::eating);
        return String.format(ConstantMessages.FEEDING_CAT,house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = houses.get(houseName);

        double totalSum = house.getCats().stream()
                .mapToDouble(Cat::getPrice).sum()
                + house.getToys().stream()
                .mapToDouble(Toy::getPrice).sum();
        return String.format(ConstantMessages.VALUE_HOUSE,houseName,totalSum);
    }

    @Override
    public String getStatistics() {
       return houses.values()
                .stream()
                .map(House::getStatistics)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
