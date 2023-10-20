package ex04;

public class Program {
    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();

        service.addUser("John", 1000);
        service.addUser("Mike", 500);
        service.addUser("Karl", 100);
        service.addUser("Phillip", 500);

        System.out.println("Check users name and balance: ");
        for(int i = 1; i < service.getListUser().getNumberUsers() + 1; i++) {
            System.out.print(service.getListUser().getUserId(i).getName());
            System.out.print(" ");
            System.out.println(service.retrievingBalance(i));
        }

        service.perfomingTransaction(2, 1, 500);
        service.perfomingTransaction(3, 4, 200);
        service.perfomingTransaction(4, 2, 100);
        service.perfomingTransaction(3, 2, 50);

        for(int i = 1; i < service.getListUser().getNumberUsers() + 1; i++) {
            System.out.print("\nTransactions of user ");
            System.out.println(service.getListUser().getUserId(i).getName());
            for(Transaction trans : service.retrievingTransfer(i)) {
                System.out.println(trans);
            }
        }

        System.out.println("\nCheck users name and balance: ");
        for(int i = 1; i < service.getListUser().getNumberUsers() + 1; i++) {
            System.out.print(service.getListUser().getUserId(i).getName());
            System.out.print(" ");
            System.out.println(service.retrievingBalance(i));
        }

        Transaction[] transactions1 = service.getListUser().getUserId(2).getTransList().toArray();
        Transaction[] transactions2 = service.getListUser().getUserId(3).getTransList().toArray();
        Transaction[] transactions3 = service.getListUser().getUserId(4).getTransList().toArray();

        service.removeTransaction(2, transactions1[0].getIdentifier());
        service.removeTransaction(3, transactions2[1].getIdentifier());
        service.removeTransaction(4, transactions3[0].getIdentifier());

        System.out.println("\nCheck validity of transactions:");
        for(Transaction unpairTrans : service.validityTransactions()) {
            System.out.println(unpairTrans);
        }

        System.out.println("\nOptions for transferring users with incorrect initial values:");
        service.perfomingTransaction(4, 3, 1000);
        service.perfomingTransaction(1,1, 100);
        service.perfomingTransaction(1, 2, 0);
    }
}
