package p03_IteratorTest;


import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;


public class ListIteratorTest {
    private ListIterator listIterator;
    private static final String[] NAMES = {"Angel","Mariq","Deya"};
    private int currentIndex;

    @Before
    public void setUp() throws OperationNotSupportedException {
        listIterator=new ListIterator("Angel","Mariq","Deya");
        currentIndex=0;
    }
    @Test(expected = OperationNotSupportedException.class)
    public void testCreateListIteratorWithNullShouldThrow() throws OperationNotSupportedException {
        listIterator=new ListIterator(null);
    }
    @Test
    public void testHasNext(){
        assertTrue(listIterator.hasNext());
        listIterator.move();
        listIterator.move();
        listIterator.move();
        assertFalse(listIterator.hasNext());
    }
    @Test
    public void testMove(){
        assertTrue(listIterator.move());
        currentIndex++;
        assertEquals(currentIndex,1);
        listIterator.move();
        currentIndex++;
        listIterator.move();
        currentIndex++;
        listIterator.move();
        currentIndex++;
        assertEquals(currentIndex,4);
        assertFalse(listIterator.move());
    }
    @Test(expected = IllegalStateException.class)
    public void testPrintShouldThrowInvalid() throws OperationNotSupportedException {
        listIterator=new ListIterator();
        listIterator.print();
    }
    @Test
    public void testPrintSuccess(){
        int index = 0;
        while (listIterator.hasNext()){
            assertEquals(NAMES[index],listIterator.print());
            index++;
            listIterator.move();
        }

    }

}
