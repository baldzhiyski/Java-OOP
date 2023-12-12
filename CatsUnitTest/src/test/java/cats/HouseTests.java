package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class HouseTests {
    private Collection<Cat> cats;
    private static final String NAME = "Hristo";
    private static final int CAPACITY = 5;
    private House house;

    @Before
    public void setUp(){
        this.cats = createCats();
        this.house = new House(NAME,CAPACITY);
    }
    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowIllegalName(){
        house = new House("",CAPACITY);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityShouldFailInvalidCapacity(){
        house = new House(NAME,-12);
    }
    @Test
    public void testGetNameShouldReturnHousesName(){
        assertEquals(NAME,house.getName());
    }
    @Test
    public void testGetCapacityShouldReturnHousesCapacity(){
        assertEquals(CAPACITY,house.getCapacity());
    }
    @Test
    public void testGetCountShouldReturnCountOfCats(){
        fillTheHouse();
        assertEquals(4,house.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldFailNotEnoughCapacity(){
        fillTheHouse();
        house.addCat(new Cat("Jirka"));
        house.addCat(new Cat("Moro"));
    }
    @Test
    public void testAddShouldWorkCorrectly(){
        fillTheHouse();
        assertEquals(4,house.getCount());
        house.addCat(new Cat("Jirka"));
        assertEquals(5,house.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveShouldThrowNotFoundCat(){
        fillTheHouse();
        house.removeCat("Parko");
    }
    @Test
    public void testRemoveShouldRemoveCorrectly(){
        fillTheHouse();
        house.removeCat("Dori");
        assertEquals(3,house.getCount());

        house.removeCat("Roko");
        assertEquals(2,house.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testForSaleShouldThrowInvalidCatName(){
        fillTheHouse();
        house.catForSale("Roki");
    }
    @Test
    public void testForSaleShouldSetProperlyValue(){
        fillTheHouse();
        Cat catToAdd = new Cat("Parorano");
        house.addCat(catToAdd);
        house.catForSale(catToAdd.getName());
        assertFalse(catToAdd.isHungry());
    }
    @Test
    public void testGetStatistics(){
        fillTheHouse();
        String expected = "The cat Dori, Roko, Djarko, Migel is in the house Hristo!";
        assertEquals(expected,house.statistics());
    }
    private Collection<Cat> createCats() {
        return List.of(new Cat("Dori"),
                new Cat("Roko"),
                new Cat("Djarko"),
                new Cat("Migel"));
    }
    private void  fillTheHouse(){
        cats.forEach(house::addCat);
    }

}
