package ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("John", 1000);
        User user2 = new User("Mike", 500);
        User user3 = new User("Jo", 3000);
        User user4 = new User("Karl", 10000);

        Transaction trans1 = new Transaction(user2, user1, Transaction.Category.CREDITS, -500);
        Transaction trans2 = new Transaction(user1, user2, Transaction.Category.DEBITS, 250);
        Transaction trans3 = new Transaction(user4, user3, Transaction.Category.DEBITS, 2000);
        Transaction trans4 = new Transaction(user1, user3, Transaction.Category.CREDITS, -1000);

        TransactionsList list = new TransactionsLinkedList();
        list.addTransaction(trans1);
        list.addTransaction(trans2);
        list.addTransaction(trans3);
        list.addTransaction(trans4);

        Transaction[] transArray = list.toArray();

        System.out.println("View array after adding:");
        for(Transaction trans : transArray) {
            trans.printData();
        }

        list.removeTransaction(trans2.getIdentifier());
        transArray = list.toArray();

        System.out.println("Viewing the array after deleting the second transaction:");
        for(Transaction trans : transArray) {
            trans.printData();
        }

        try {
            list.removeTransaction(UUID.randomUUID());
        } catch (TransactionNotFoundException error) {
            System.err.println(error.getMessage());
        }
    }
}
