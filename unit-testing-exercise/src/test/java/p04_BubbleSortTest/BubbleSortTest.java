package p04_BubbleSortTest;


import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class BubbleSortTest {
    @Test
    public void testSort(){
        int[] numbers = {9,-5,6,1,12,100};
        Bubble.sort(numbers);
        int[] sorted = {-5,1,6,9,12,100};
        assertArrayEquals(numbers,sorted);
    }
}
