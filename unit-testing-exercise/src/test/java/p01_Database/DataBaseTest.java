package p01_Database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;


import static org.junit.Assert.assertEquals;

public class DataBaseTest {
    private static final Integer[] EXPECTED_ELEMENTS = {1,2,3,4,5};
    private static final int EXPECTED_SIZE = EXPECTED_ELEMENTS.length;
    private static final int EXPECTED_INDEX = EXPECTED_ELEMENTS.length-1;
    private static final Integer EXPECTED_LAST_ELEMENT = EXPECTED_ELEMENTS[3];
    private Database database;
    @Before
    public void setUp() throws OperationNotSupportedException {
        database = new Database(EXPECTED_ELEMENTS);
    }
    @Test
    public void test_Constructor_Should_Create_CorrectObject() throws OperationNotSupportedException {
        Integer[] actualElements = database.getElements();
        int actualSize = actualElements.length;
        int actualIndex = actualSize-1;
        Assert.assertArrayEquals(EXPECTED_ELEMENTS,actualElements);
        assertEquals(EXPECTED_SIZE,actualSize);
        assertEquals(EXPECTED_INDEX,actualIndex);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void test_Constructor_ShouldThrow_When_ElementsAreGreater_Than16() throws OperationNotSupportedException{
        Integer[] elements = new Integer[17];
        database = new Database(elements);

    }
    @Test(expected = OperationNotSupportedException.class)
    public void test_Constructor_ShouldThrow_When_ElementsAreZero() throws OperationNotSupportedException{
        Database database = new Database();
    }
    @Test(expected = OperationNotSupportedException.class)
    public void test_Add_Null_ShouldThrow() throws OperationNotSupportedException {
        database.add(null);
    }
    @Test
    public void test_Add_ShouldAdd_Element_At_The_End_And_Increase_Size() throws OperationNotSupportedException {
        database.add(25);
        int actualSize = database.getElements().length;
        Integer[] elements = database.getElements();
        assertEquals(Integer.valueOf(25),elements[actualSize-1]);
        assertEquals("Invalid size",EXPECTED_SIZE+1,actualSize);
    }
    @Test(expected = OperationNotSupportedException.class)
    public void test_Remove_ShouldThrow_When_Database_Empty() throws OperationNotSupportedException{
        for (int i = 0; i <=EXPECTED_SIZE; i++) {
            database.remove();
        }
    }
    @Test
    public void test_ShouldRemove_Successfully_AndDecrease_Size() throws OperationNotSupportedException {
        database.remove();
        Integer[] elements = database.getElements();
        Integer actualLastEl = elements[elements.length-1];
        int actualSize = database.getElements().length;
        assertEquals("Invalid size",EXPECTED_SIZE-1,actualSize);
        assertEquals(EXPECTED_LAST_ELEMENT,actualLastEl);
    }

}
