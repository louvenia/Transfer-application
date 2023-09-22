package ex04;

import java.util.UUID;

interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeTransaction(UUID uuid);
    Transaction[] toArray();
}
