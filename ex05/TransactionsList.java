package ex05;

interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeTransaction(String uuid);
    Transaction[] toArray();
}
