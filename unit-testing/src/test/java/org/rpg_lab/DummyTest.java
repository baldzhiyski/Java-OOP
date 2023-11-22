package org.rpg_lab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DummyTest {
    private static final int HEALTH=100;
    private static final int EXPERIENCE = 200;
    private static final int ATTACK = 10;
    private Dummy dummy;

    private Dummy deadDummy;
    @Before
    public void setUp(){
        this.dummy=new Dummy(HEALTH,EXPERIENCE);
        this.deadDummy=new Dummy(0,EXPERIENCE);
    }
    @Test
    public void test_Dummy_LossesHealth_WhenAlive_AndAttack(){
        dummy.takeAttack(ATTACK);

        assertEquals(HEALTH-ATTACK,dummy.getHealth());

    }
    @Test(expected = IllegalStateException.class)
    public void test_Dummy_CannotBeAttacked_WhenDead(){
        deadDummy.takeAttack(ATTACK);
    }
    @Test
    public void test_Dummy_GivesExp_WhenDead(){
        int actualXp = deadDummy.giveExperience();
        assertEquals(EXPERIENCE,actualXp);
    }
    @Test(expected = IllegalStateException.class)
    public void test_Dummy_GivesExp_Throws_Alive(){
        dummy.giveExperience();
    }
    @Test
    public void test_Dummy_IsDeadWhenHealthZero(){
        // so here I want to test that dummy with 0 should be dead.
        assertTrue(deadDummy.isDead());
    }
    @Test
    public void test_Dummy_isAlive(){
        assertFalse(new Dummy(1,1).isDead());
    }

}