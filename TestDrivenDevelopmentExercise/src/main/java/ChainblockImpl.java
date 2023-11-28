import java.util.*;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock{
    private Map<Integer,Transaction> database;
    public ChainblockImpl(){
        this.database = new LinkedHashMap<>();
    }
    public int getCount() {
        return database.size();
    }

    public void add(Transaction transaction) {
        database.putIfAbsent(transaction.getId(), transaction);
    }

    public boolean contains(Transaction transaction) {
        return database.containsKey(transaction.getId());
    }

    public boolean contains(int id) {
        return database.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        Transaction transaction = getById(id);
        transaction.setStatus(newStatus);
    }

    public void removeTransactionById(int id) {
        Transaction transaction = getById(id);
        database.remove(transaction.getId());
    }

    public Transaction getById(int id) {
        if (!contains(id)) {
            throw new IllegalArgumentException();
        }
        return database.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> transactions = database.values()
                .stream()
                .filter(transaction -> transaction.getStatus().equals(status))
                .sorted((t1, t2) -> Double.compare(t2.getAmount(), t1.getAmount()))
                .collect(Collectors.toList());
        if (transactions.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return transactions;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {

        //Die groupingBy-Operation gruppiert Transaktionen nach Absender, und die Absender-IDs dienen als Schlüssel in der resultierenden Map.
        Map<String, List<Transaction>> sendersMap = database.values()
                .stream()
                .filter(transaction -> transaction.getStatus().equals(status))
                .collect(Collectors.groupingBy(Transaction::getFrom));
        if (sendersMap.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<String> result = new LinkedList<>();

        sendersMap.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .forEach(entry -> {
                    entry.getValue().forEach(transaction -> result.add(transaction.getFrom()));
                });
        return result;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {

        Map<String, List<Transaction>> receiver = database.values()
                .stream()
                .filter(transaction -> transaction.getStatus().equals(status))
                .collect(Collectors.groupingBy(Transaction::getTo));
        if (receiver.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<String> result = new LinkedList<>();

        receiver.entrySet()
                .stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .forEach(entry -> {
                    entry.getValue().forEach(transaction -> result.add(transaction.getTo()));
                });
        return result;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return database.values().stream()
                .sorted(Comparator.comparing(Transaction::getAmount).thenComparing(Transaction::getId).reversed())
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> list = database.values().stream()
                .filter(transaction -> transaction.getFrom().equals(sender))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if (list.isEmpty()){
            throw new IllegalArgumentException();
        }
        return list;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> list = database.values().stream()
                .filter(transaction -> transaction.getTo().equals(receiver))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if(list.isEmpty()){
            throw new IllegalArgumentException();
        }
        return list;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        List<Transaction> list = database.values()
                .stream()
                .filter(transaction -> transaction.getStatus().equals(status))
                .filter(transaction -> transaction.getAmount() <= amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if(list.isEmpty()){
            throw new IllegalArgumentException();
        }
        return list;
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> list = database.values()
                .stream()
                .filter(transaction -> transaction.getFrom().equals(sender))
                .filter(transaction -> transaction.getAmount() >= amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
        if(list.isEmpty()){
            throw new IllegalArgumentException();
        }
        return list;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> list = database.values().stream()
                .filter(transaction -> transaction.getTo().equals(receiver))
                .filter(transaction -> transaction.getAmount() >= lo && transaction.getAmount() < hi)
                .sorted(Comparator.comparing(Transaction::getId).reversed())
                .collect(Collectors.toList());
        if(list.isEmpty()){
            throw  new IllegalArgumentException();
        }
        return list;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return database.values().stream()
                .filter(transaction -> transaction.getAmount() > lo && transaction.getAmount() < hi)
                .collect(Collectors.toList());
    }
    public Iterator<Transaction> iterator() {
        return new Iterator<Transaction>() {
            private Iterator<Map.Entry<Integer, Transaction>> iterator = database.entrySet().iterator();

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public Transaction next() {
                return iterator.next().getValue();
            }
        };
    }
}
