public interface Transaction {
    int getId();
    String getFrom();
    TransactionStatus getStatus();
    double getAmount();
    String getTo();
    void setStatus(TransactionStatus status);
}
