package p05_CustomLinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomLinkedListTest {
    CustomLinkedList<String> customLinkedList;

    @Before
    public void setUp(){
        customLinkedList = new CustomLinkedList<>();
    }
    @Test
    public void testAddSuccess(){
        customLinkedList.add("Pesho");
        assertEquals("Pesho",customLinkedList.get(0));
    }
    @Test
    public void testAddToNonEmptyList(){
        customLinkedList.add("Pesho");
        customLinkedList.add("Ivan");
        assertEquals("Ivan",customLinkedList.get(1));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowForMissingIndex(){
        customLinkedList.get(-1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowForMissingIndexBig(){
        customLinkedList.get(100);
    }
    @Test
    public void testGetSuccess(){
       customLinkedList.add("Kirov");
        assertEquals("Kirov",customLinkedList.get(0));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetShouldThrowForMissingIndexSmall(){
        customLinkedList.set(-1,"Kiro");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetShouldThrowForMissingIndexBig(){
        customLinkedList.set(100,"Kiro");
    }
    @Test
    public void testSetSuccess(){
        customLinkedList.add("Kiro");
        customLinkedList.add("Ivan");
        customLinkedList.add("Kala");
        customLinkedList.set(1,"Kiro");
        assertEquals("Kiro",customLinkedList.get(1));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtShouldThrowForMissingIndexSmall(){
        customLinkedList.removeAt(-1);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtShouldThrowForMissingIndexBig(){
        customLinkedList.removeAt(100);
    }
    @Test
    public void testRemoveAtSuccess(){
        customLinkedList.add("Kiro");
        customLinkedList.add("Ivan");
        customLinkedList.add("Kala");
        customLinkedList.removeAt(1);
        assertNotEquals("Ivan", customLinkedList.get(1));
    }
    @Test
    public void testRemoveSuccess(){
        customLinkedList.add("Kiro");
        customLinkedList.add("Ivan");
        customLinkedList.add("Kala");
        int removed = customLinkedList.remove("Ivan");
        assertEquals(1,removed);
    }
    @Test
    public void testRemoveFailure(){
        customLinkedList.add("Kiro");
        customLinkedList.add("Ivan");
        customLinkedList.add("Kala");
        int removed = customLinkedList.remove("Kakak");
        assertEquals(-1,removed);
    }
    @Test
    public void testContainsSuccess(){
        customLinkedList.add("Kiro");
        customLinkedList.add("Ivan");
        customLinkedList.add("Kala");
        boolean res = customLinkedList.contains("Kiro");
        assertTrue(res);
    }
    @Test
    public void testContainsNotAvailable(){
        customLinkedList.add("Kiro");
        customLinkedList.add("Ivan");
        customLinkedList.add("Kala");
        boolean res = customLinkedList.contains("Meca");
        assertFalse(res);
    }


}
