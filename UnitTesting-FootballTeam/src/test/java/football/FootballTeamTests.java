package football;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FootballTeamTests {
    private static final String NAME = "LEVSKI";
    private static final int VACATION_POSITIONS = 5;
    private FootballTeam team;
    private Collection<Footballer> footballers;

    @Before
    public void setUp(){
        team = new FootballTeam(NAME,VACATION_POSITIONS);
        footballers = createFootballers();
    }
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldFailInvalidVacationPositions(){
        team = new FootballTeam(NAME,-234);
    }
    @Test(expected = NullPointerException.class)
    public void testConstructorShouldFailInvalidName(){
        team = new FootballTeam("",VACATION_POSITIONS);
    }
    @Test
    public void testConstructorShouldWork(){
        team = new FootballTeam("Work",12);
        assertEquals("Work",team.getName());
        assertEquals(12,team.getVacantPositions());
    }
    @Test
    public void testGetPositions(){
        assertEquals(VACATION_POSITIONS,team.getVacantPositions());
    }
    @Test
    public void testGetName(){
        assertEquals(NAME,team.getName());
    }
    @Test
    public void testGetCount(){
        fillTheTeam();
        assertEquals(footballers.size(),team.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddFootballerShouldFailNotEnoughSpace(){
        fillTheTeam();
        team.addFootballer(new Footballer("Pesho"));
        team.addFootballer(new Footballer("Krisko"));
    }
    @Test
    public void testAddShouldWork(){
        assertEquals(0,team.getCount());
        team.addFootballer(new Footballer("Chefo"));
        assertEquals(1,team.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveShouldFailInvalidName(){
        fillTheTeam();
        team.removeFootballer("Krisko");
    }
    @Test
    public void testRemoveShouldWork(){
        fillTheTeam();
        team.removeFootballer("Kaloyan");
        assertEquals(footballers.size()-1,team.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testFootballerForSaleInvalidFootballerShouldThrow(){
        team.footballerForSale("Krisko");
    }
    @Test
    public void testFootballerForSaleShouldWork(){
        fillTheTeam();
        Footballer footballer = team.footballerForSale("Kaloyan");
        assertFalse(footballer.isActive());
    }
    @Test
    public void testGetStatistics(){
        fillTheTeam();
        String expected = "The footballer Petar, Georgi, Kaloyan, Hristo is in the team LEVSKI.";
        assertEquals(expected,team.getStatistics());
    }
    private void fillTheTeam(){
        footballers.forEach(team::addFootballer);
    }
    private Collection<Footballer> createFootballers() {
        return List.of(new Footballer("Petar"),
                new Footballer("Georgi"),
                new Footballer("Kaloyan"),
                new Footballer("Hristo"));
    }
}
