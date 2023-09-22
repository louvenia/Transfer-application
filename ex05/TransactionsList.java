package ex05;

import java.util.UUID;

interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeTransaction(String uuid);
    Transaction[] toArray();
}
