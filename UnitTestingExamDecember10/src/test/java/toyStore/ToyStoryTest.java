package toyStore;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class ToyStoryTest {
    private ToyStore store;
    private static final Toy TOY1 = Mockito.mock(Toy.class);
    private static final Toy TOY2 = Mockito.mock(Toy.class);

    @Before
    public void setUp(){
        store = new ToyStore();
        when(TOY1.getManufacturer()).thenReturn("LEVSKI");
        when(TOY2.getManufacturer()).thenReturn("PLOVDIV");
        when(TOY1.getToyId()).thenReturn("123");
        when(TOY2.getToyId()).thenReturn("345");
    }
    @Test
    public void getVaultCells() {
        Map<String, Toy> toyShelf;
        toyShelf = new LinkedHashMap<>();
        toyShelf.put("A", null);
        toyShelf.put("B", null);
        toyShelf.put("C", null);
        toyShelf.put("D", null);
        toyShelf.put("E", null);
        toyShelf.put("F", null);
        toyShelf.put("G", null);
        assertEquals(toyShelf, store.getToyShelf());

    }
    @Test
    public void testConstructor(){
        store = new ToyStore();
        Map<String, Toy> toyShelf;
        toyShelf = new LinkedHashMap<>();
        toyShelf.put("A", null);
        toyShelf.put("B", null);
        toyShelf.put("C", null);
        toyShelf.put("D", null);
        toyShelf.put("E", null);
        toyShelf.put("F", null);
        toyShelf.put("G", null);
        assertEquals(toyShelf,store.getToyShelf());

    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldFailInvalidShelf() throws OperationNotSupportedException {
        store.addToy("T",TOY1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldFailTakenShelf() throws OperationNotSupportedException {
        store.addToy("B",TOY1);
        store.addToy("B",TOY2);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testAddShouldFailAddingAnExistingToy() throws OperationNotSupportedException {
        store.addToy("B",TOY1);
        store.addToy("C",TOY1);
    }
    @Test
    public void testAddShouldAddTheNewToyToTheShelf() throws OperationNotSupportedException {
        store.addToy("B",TOY1);
        store.addToy("C",TOY2);
        long exist = store.getToyShelf().values().stream().filter(Objects::nonNull).count();
        assertEquals(TOY1,store.getToyShelf().get("B"));
        assertEquals(2,exist);
        assertEquals(TOY2,store.getToyShelf().get("C"));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveMethodShouldFailNotExistingShelf(){
        store.removeToy("T",TOY1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveShouldFailNotExistingToyOnTheShelf() throws OperationNotSupportedException {
        store.addToy("B",TOY1);
        store.addToy("C",TOY2);
        store.removeToy("B",TOY2);
    }
    @Test
    public void testRemoveShouldWorkCorrect() throws OperationNotSupportedException {
        store.addToy("B", TOY1);
        store.addToy("C", TOY2);
        String result = store.removeToy("C", TOY2);
        assertNull(store.getToyShelf().get("C"));
        assertEquals("Remove toy:345 successfully!",result);
    }


}