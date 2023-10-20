package ex00;

public class Program {
    public static void main(String[] args) {
        User user1 = new User(1, "John", 1000);
        User user2 = new User(2, "Mike", 500);
        System.out.println(user1);
        System.out.println(user2);
        System.out.println();

        Transaction trans1 = new Transaction(user2, user1, Transaction.Category.CREDITS, -500);
        System.out.println(trans1);
        System.out.println();

        System.out.println(user1);
        System.out.println(user2);
        System.out.println();

        Transaction trans2 = new Transaction(user1, user2, Transaction.Category.DEBITS, 500);
        System.out.println(trans2);
        System.out.println();

        System.out.println(user1);
        System.out.println(user2);
        System.out.println();

        System.out.println("Variations of user creation and transfer with incorrect initial values:");
        User user3 = new User(3, "Nastya", -100);
        Transaction trans3 = new Transaction(user1, user2, Transaction.Category.DEBITS, -100);
        trans2.setTransferAmount(800);
    }
}
