package handball;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TeamTests {
    private Team team;

    @Before
    public void setUp(){
        team = new Team("Team1",10);
    }
    @Test
    public void testGetName() {
        assertEquals("Team1", this.team.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowInvalidName(){
        team= new Team("",4);
    }
    @Test
    public void testGetPosition(){
        Assert.assertEquals(10,team.getPosition());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowInvalidPosition(){
        team= new Team("Team2",-3);
    }
    @Test
    public void testConstructorWorkingProperly(){
        Team team1 = new Team("Team1",10);
        Assert.assertEquals(team.getName(),team1.getName());
        Assert.assertEquals(team.getPosition(),team1.getPosition());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddShouldFailAndThrowWhenNotEnoughCapacity(){
        for (int i = 0; i < team.getPosition()+1; i++) {
            team.add(new HandballPlayer(String.valueOf(i)));
        }
    }
    @Test
    public void testAddShouldWorkCorrect(){
        Assert.assertEquals(0,team.getCount());
        team.add(new HandballPlayer("Peter"));
        team.add(new HandballPlayer("Maya"));
        team.add(new HandballPlayer("Gosho"));
        Assert.assertEquals(3,team.getCount());

    }
    @Test
    public void testGetCount(){
        team.add(new HandballPlayer("Peter"));
        team.add(new HandballPlayer("Maya"));
        Assert.assertEquals(2,team.getCount());
    }
    @Test
    public void testSetActive(){
        HandballPlayer player = new HandballPlayer("Pesho");
        player.setActive(true);
        Assert.assertTrue(player.isActive());
        player.setActive(false);
        Assert.assertFalse(player.isActive());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testPlayerForAnotherTeamFailNotFoundPlayer(){
        team.playerForAnotherTeam("Pesho");
    }
    @Test
    public void testPlayerForAnotherTeamShouldSucceed(){
        HandballPlayer player = new HandballPlayer("Pesho");
        team.add(player);
        team.playerForAnotherTeam("Pesho");
        Assert.assertFalse(player.isActive());
    }
    @Test (expected = IllegalArgumentException.class)
    public void testRemoveShouldThrowNotFoundPlayer(){
        team.remove("Petar");
    }
    @Test
    public void testRemoveShouldWorkProperly(){
        team.add(new HandballPlayer("Petar"));
        team.remove("Petar");
        Assert.assertEquals(0,team.getCount());
    }
    @Test
    public void testGetStatisticMethods(){
        team.add(new HandballPlayer("Gosho"));
        team.add(new HandballPlayer("Evgeni"));
        team.add(new HandballPlayer("Andrei"));
        team.add(new HandballPlayer("Celina"));
        team.add(new HandballPlayer("Belin"));
        team.add(new HandballPlayer("Daniel"));
        String expectedMessage = "The player Gosho, Evgeni, Andrei, Celina, Belin, Daniel is in the team "+ team.getName() +".";
        Assert.assertEquals(expectedMessage,team.getStatistics());
    }
}
