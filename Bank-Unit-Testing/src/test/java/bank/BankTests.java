package bank;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class BankTests {
    private Bank bank;
    private static final String NAME = "DSK";
    private static final int CAPACITY = 8;
    private Collection<Client> clientList;

    @Before
    public void setUp() {
        bank = new Bank(NAME, CAPACITY);
        clientList = createListOfClients();
    }
    @Test(expected = NullPointerException.class)
    public void testSetNameOnNull() {
        new Bank(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameOnWhitespaces() {
        new Bank("      ", 10);
    }
    @Test (expected = IllegalArgumentException.class)
    public  void testConstructorShouldThrowInvalidCapacity(){
        bank = new Bank(NAME,-24);
    }
    @Test (expected = NullPointerException.class)
    public  void testConstructorShouldThrowInvalidName(){
        bank = new Bank("",CAPACITY);
    }
    @Test
    public void testConstructorShouldCreateABank(){
        bank = new Bank("Name",15);
       assertEquals("Name",bank.getName());
       assertEquals(15,bank.getCapacity());
    }

    @Test
    public void testGetNameWorks(){
        assertEquals(NAME,bank.getName());
    }
    @Test
    public void testGetCapacity(){
        assertEquals(CAPACITY,bank.getCapacity());
    }
    @Test
    public void testGetCountShouldReturnCorrectCountOfClients(){
        assertEquals(0,bank.getCount());
        fillTheBank();
        assertEquals(clientList.size(),bank.getCount());
    }
    @Test (expected = IllegalArgumentException.class)
    public void testAddClientShouldThrowNotEnoughCapacity(){
        fillTheBank();
        bank.addClient(new Client("Stefan"));
        bank.addClient(new Client("George"));
        bank.addClient(new Client("Kiril"));
    }
    @Test
    public void testAddShouldAddAtTheEnd(){
        fillTheBank();
        Client petar = new Client("Petar");
        bank.addClient(petar);
        assertEquals(clientList.size()+1,bank.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void removeShouldThrowWhenClientNotExist(){
        bank.removeClient("Petar");
    }
    @Test
    public void testRemoveClientWorks(){
        fillTheBank();
        assertEquals(6,bank.getCount());
        bank.removeClient("Kaloyan");
        assertEquals(5,bank.getCount());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testLoanWithdrawShouldThrowNotExistingClient(){
        bank.loanWithdrawal("Ceca");
    }
    @Test
    public void testLoanWithdrawShouldBeSuccessful(){
        fillTheBank();
        Client client = bank.loanWithdrawal("Darnel");
        assertFalse(client.isApprovedForLoan());
    }
    @Test
    public void testGetStatistics(){
        fillTheBank();
        String actual = bank.statistics();
        String expected = "The client Steven, Darnel, Kaloyan, Georgi, Sasho, Izo is at the DSK bank!";
        assertEquals(expected,actual);
    }
    private List<Client> createListOfClients() {
        return List.of(new Client("Steven"),
                new Client("Darnel"),
                new Client("Kaloyan"),
                new Client("Georgi"),
                new Client("Sasho"),
                new Client("Izo"));
    }
    private void fillTheBank(){
        clientList.forEach(bank::addClient);
    }
}
