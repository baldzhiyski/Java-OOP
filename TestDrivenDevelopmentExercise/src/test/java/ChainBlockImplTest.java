import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.Assert.*;

public class ChainBlockImplTest {
    private ChainblockImpl database;
    private List<Transaction> transactions;

    @Before
    public void setUp(){
        database = new ChainblockImpl();
        transactions = createTransactions();
    }

    @Test
    public void testGetCountMethodWorksProperly(){
        assertEquals(0,database.getCount());
       fillDataBase();
       assertEquals(6,database.getCount());
    }
    @Test
    public void testContainsByIdsShouldSucceed(){
        fillDataBase();
        assertFalse(database.contains(100));
        assertTrue(database.contains(1));
    }
    @Test
    public void testContainsByTransaction(){
        Transaction transactionToCheck = new TransactionImpl(6,TransactionStatus.ABORTED,"Sai","Kai",51.5);
        assertFalse(database.contains(transactionToCheck));
        fillDataBase();
        assertTrue(database.contains(transactionToCheck));
    }
    @Test
    public void testAddMethodShouldWorkAsIntended(){
        Transaction expectedTransaction = transactions.get(0);
        database.add(expectedTransaction);
        Transaction actualTransaction = database.getById(1);

        assertEquals(expectedTransaction,actualTransaction);
        assertEquals(1,database.getCount());

    }
    @Test
    public void testAddMethodShouldNotAddExistingItems(){
        Transaction expectedTransaction = transactions.get(1);
        database.add(expectedTransaction);
        database.add(expectedTransaction);
        database.add(expectedTransaction);
        assertEquals(1,database.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionShouldFailAndThrow(){
        Transaction transaction = transactions.get(2);
        database.changeTransactionStatus(transaction.getId(),TransactionStatus.FAILED);
    }
    @Test
    public void testChangeTransactionShouldWorkProperly(){
        fillDataBase();
        database.changeTransactionStatus(2,TransactionStatus.ABORTED);
        assertEquals(TransactionStatus.ABORTED,database.getById(2).getStatus());
    }
    @Test(expected = IllegalArgumentException.class)
    public void removeTransactionByIdShouldThrowInvalidId(){
        database.removeTransactionById(1);
    }
    @Test
    public void removeTransactionShouldWork(){
        fillDataBase();
        database.removeTransactionById(1);
        assertEquals(5,database.getCount());
        database.removeTransactionById(2);
        assertEquals(4,database.getCount());
        assertFalse(database.contains(1));
        assertFalse(database.contains(2));
    }
    @Test
    public void testGetByIdCorrectTransaction(){
        fillDataBase();
        Transaction  actualTransaction = database.getById(3);
        Transaction expectedTransaction = transactions.get(2);
        assertEquals(expectedTransaction,actualTransaction);
        assertEquals(expectedTransaction.getId(),actualTransaction.getId());
    }
    @Test
    public void testGetByTransactionStatusShouldReturnWithChosenStatus(){
        fillDataBase();
        List<Transaction> expectedList = transactions.stream()
                .filter(transactionStatus -> transactionStatus.getStatus().equals(TransactionStatus.UNAUTHORIZED))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        List<Transaction> actualList = toList(database.getByTransactionStatus(TransactionStatus.UNAUTHORIZED));
        assertEquals(expectedList.size(),actualList.size());
        actualList.forEach(transaction -> assertEquals(TransactionStatus.UNAUTHORIZED,transaction.getStatus()));
    }
    @Test
    public void testByTransactionStatusShouldReturnInRightOrder(){
        fillDataBase();
        List<Transaction> actualList = toList(database.getByTransactionStatus(TransactionStatus.FAILED));
        assertEquals(transactions.get(1).getId(),actualList.get(0).getId());
        assertEquals(transactions.get(0),actualList.get(1));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusTransactionsNonExists() {
       fillDataBase();
        database.getByTransactionStatus(TransactionStatus.ABORTED);
    }
    @Test(expected = IllegalArgumentException.class)
    public void getAllSenderWithTransactionStatusShouldThrowWhenNotExistingStatus(){
        database.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);
    }
    @Test
    public void getAllSenderWithTransactionStatusShouldWork(){
        fillDataBase();
        Iterable<String> transactions = database.getAllSendersWithTransactionStatus(TransactionStatus.UNAUTHORIZED);
        List<String> senders = StreamSupport.stream(transactions.spliterator(), false)
                .collect(Collectors.toList());

        String sender1 = senders.get(0);
        String sender2 = senders.get(1);
        String sender3 = senders.get(2);

        assertEquals("Ivan", sender1);
        assertEquals("Ivan", sender2);
        assertEquals("Kaloyan", sender3);
    }
    @Test(expected = IllegalArgumentException.class)
    public void getAllReceiversShouldFailNotExistingStatus(){
        fillDataBase();
        database.getAllReceiversWithTransactionStatus(TransactionStatus.ABORTED);
    }
    @Test
    public void getAllReceiversShouldWork(){
        fillDataBase();
        Iterable<String> transactions = database.getAllReceiversWithTransactionStatus(TransactionStatus.UNAUTHORIZED);
        List<String> senders = StreamSupport.stream(transactions.spliterator(), false)
                .collect(Collectors.toList());

        String sender1 = senders.get(0);
        String sender2 = senders.get(1);
        String sender3 = senders.get(2);

        assertEquals("Maria", sender1);
        assertEquals("Maria", sender2);
        assertEquals("Andrei", sender3);
    }
    @Test
    public void testGetAllOrderByAmountDescendingThenById(){
        fillDataBase();
        List<Transaction> expected = transactions.stream()
                .sorted(Comparator.comparing(Transaction::getAmount).thenComparing(Transaction::getId).reversed())
                .collect(Collectors.toList());

        List<Transaction> actual = toList(database.getAllOrderedByAmountDescendingThenById());
        assertEquals(expected.size(),actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected.get(i).getId(),actual.get(i).getId());
        }
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderOrderedByAmountDescendingShouldThrow(){
        database.getBySenderOrderedByAmountDescending("Kaloyan");
    }
    @Test
    public void testGetBySenderOrderedByAmountDescendingWorks(){
        fillDataBase();
        List<Transaction> expectedTransactions = transactions.stream()
                .filter(transaction -> transaction.getFrom().equals("Ivan"))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        List<Transaction> actualList = toList(database.getBySenderOrderedByAmountDescending("Ivan"));
        assertEquals("Maria",actualList.get(1).getTo());
        assertEquals("Andrei",actualList.get(0).getTo());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverShouldThrow(){
        database.getByReceiverOrderedByAmountThenById("Kaloyan");
    }
    @Test
    public void testGetByReceiverOrderedByAmountThenByIdWorks(){
        fillDataBase();
        List<Transaction> expected = transactions.stream()
                .filter(transaction -> transaction.getTo().equals("Maria"))
                .sorted(Comparator.comparing(Transaction::getAmount).thenComparing(Transaction::getId).reversed())
                .collect(Collectors.toList());
        List<Transaction> actual = toList(database.getByReceiverOrderedByAmountThenById("Maria"));

        assertEquals(expected.size(),actual.size());
        assertEquals("Ivan",actual.get(0).getFrom());
        assertEquals("Kaloyan",actual.get(1).getFrom());
    }
    @Test
    public void testGetByTransactionStatusAndMaximumAmount(){
        fillDataBase();
        List<Transaction> actual = toList(database.getByTransactionStatusAndMaximumAmount(TransactionStatus.UNAUTHORIZED, 50));
        List<Transaction> expected = transactions.stream()
                .filter(transaction -> transaction.getStatus().equals(TransactionStatus.UNAUTHORIZED))
                .filter(transaction -> transaction.getAmount() <= 50)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        assertEquals(expected.get(0).getId(),actual.get(0).getId());
        assertEquals(expected.get(0).getFrom(),actual.get(0).getFrom());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetBySenderAndMinimumAmountDescendingFail(){
       database.getBySenderAndMinimumAmountDescending("Kaloyan",21);
    }
    @Test
    public void testGetBySenderAndMinimumAmountDescendingSuccess(){
        fillDataBase();
        List<Transaction> actual = toList(database.getBySenderAndMinimumAmountDescending("Ivan", 25));
        List<Transaction> expected = transactions.stream()
                .filter(transaction -> transaction.getFrom().equals("Ivan"))
                .filter(transaction -> transaction.getAmount() >= 25)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        assertEquals(expected.get(0).getId(),actual.get(0).getId());
        assertEquals(expected.get(0).getTo(),actual.get(0).getTo());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRangeShouldFail(){
        database.getByReceiverAndAmountRange("Hi",12,50);
    }
    @Test
    public void testGetByReceiverAndAmountRangeShouldWork(){
        fillDataBase();
        List<Transaction> actual = toList(database.getByReceiverAndAmountRange("Maria", 10, 47));
        List<Transaction> expected = transactions.stream()
                .filter(transaction -> transaction.getTo().equals("Maria"))
                .filter(transaction -> transaction.getAmount() >= 10 && transaction.getAmount() < 47)
                .sorted(Comparator.comparing(Transaction::getId).reversed())
                .collect(Collectors.toList());

        assertEquals(expected.size(),actual.size());
        assertEquals(expected.get(0).getId(),actual.get(0).getId());
        assertEquals(expected.get(0).getFrom(),actual.get(0).getFrom());
    }
    @Test
    public void testGetAllInAmountRangeSuccess(){
        fillDataBase();
        List<Transaction> actualList = toList(database.getAllInAmountRange(50, 90));
        List<Transaction> expected = transactions.stream()
                .filter(transaction -> transaction.getAmount() > 50 && transaction.getAmount() < 90)
                .collect(Collectors.toList());
        assertEquals(expected.size(),actualList.size());
        assertEquals(expected.get(0).getId(),actualList.get(0).getId());
    }
    @Test
    public void testIteratorReturnAllTransactions(){
        fillDataBase();
        List<Transaction> expected = transactions;
        Iterator<Transaction> iterator = database.iterator();
        List<Transaction> actual = new ArrayList<>();
        while (iterator.hasNext()){
            actual.add(iterator.next());
        }
        assertEquals(expected, actual);
    }
    public List<Transaction> createTransactions(){
      return  List.of(new TransactionImpl(1,TransactionStatus.FAILED,"Peter","Kaloyan",22.7),
        new TransactionImpl(2,TransactionStatus.FAILED,"Georgi","Stefan",77.5),
        new TransactionImpl(3,TransactionStatus.SUCCESSFUL,"Martin","Kaloyan",89.1),
        new TransactionImpl(4,TransactionStatus.UNAUTHORIZED,"Kaloyan","Maria",21.7),
        new TransactionImpl(5,TransactionStatus.UNAUTHORIZED,"Ivan","Maria",46.9),
        new TransactionImpl(6,TransactionStatus.UNAUTHORIZED,"Ivan","Andrei",51.5));
    }
    public void fillDataBase(){
        transactions.forEach(transaction -> database.add(transaction));
    }
    private List<Transaction> toList(Iterable<Transaction> iterable){
        assertNotNull(iterable);
        return StreamSupport.stream(iterable.spliterator(),false)
                .collect(Collectors.toList());
    }
}
