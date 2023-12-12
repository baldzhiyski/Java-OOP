package catHouse.entities.cat;

public class LonghairCat extends BaseCat{
    public LonghairCat(String name, String breed, double price) {
        super(name, breed, 9, price);
    }

    @Override
    public void eating() {
        super.eating();
        setKilograms(getKilograms()+2);
    }
}
