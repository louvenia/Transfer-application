package ex00;

public class Program {
    public static void main(String[] args) {
        User user1 = new User(1, "John", 1000);
        User user2 = new User(2, "Mike", 500);
        user1.printData();
        user2.printData();

        Transaction trans1 = new Transaction(user2, user1, Transaction.Category.CREDITS, -500);
        trans1.printData();

        user1.printData();
        user2.printData();

        Transaction trans2 = new Transaction(user1, user2, Transaction.Category.DEBITS, 500);
        trans2.printData();

        user1.printData();
        user2.printData();

        User user3 = new User(3, "Nastya", -100);
        Transaction trans3 = new Transaction(user1, user2, Transaction.Category.DEBITS, -100);
        trans2.setTransferAmount(800);
    }
}
