package org.rpg_lab;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AxeTest {
    private static final int ATTACK = 42;
    private static final int DURABILITY = 13;
    private static final int HEALTH = 100;
    private static final int EXPERIENCE = 200;
    private Axe axe;
    private Dummy dummy;

    @Before
    public void setUp(){
        this.axe=new Axe(ATTACK,DURABILITY);
        this.dummy= new Dummy(HEALTH,EXPERIENCE);
    }
    @Test()
    public void test_Axe_Removes_DurabilityPoints(){
        //Arrange

        //Act
        axe.attack(dummy);

        //Assert
        assertEquals(DURABILITY -1,axe.getDurabilityPoints());


    }

}
