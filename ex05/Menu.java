package ex05;

import java.util.Scanner;

public class Menu {
    private final TransactionsService service = new TransactionsService();
    private final Scanner inputItem = new Scanner(System.in);

    public void checkTransactions() {
        for(Transaction trans : service.validityTransactions()) {
            System.out.print(trans.getRecipient().getName());
            System.out.print("(");
            System.out.print("id = ");
            System.out.print(trans.getRecipient().getIdentifier());
            System.out.print(") ");
            System.out.print("has an unacknowledged transfer id = ");
            System.out.print(trans.getIdentifier());
            System.out.print(" from ");
            System.out.println(trans.getSender().getName());
            System.out.print("(");
            System.out.print("id = ");
            System.out.print(trans.getSender().getIdentifier());
            System.out.print(") for ");
            System.out.println(trans.getTransferAmount());
        }
    }

    public void removeTransfer() {
        int userId = inputItem.nextInt();
        String transferId = inputItem.next();
        service.removeTransaction(userId, transferId);
        User user = service.getListUser().getUserId(userId);
        System.out.print("Transfer ");
        for(Transaction trans : user.getTransList().toArray()) {
            if(trans.getIdentifier().toString().equals(transferId)) {
                if(!trans.getRecipient().equals(service.getListUser().getUserId(userId))) {
                    System.out.print("To ");
                    System.out.print(trans.getRecipient().getName());
                    System.out.print("(");
                    System.out.print("id = ");
                    System.out.print(trans.getRecipient().getName());
                    System.out.print(") ");
                } else {
                    System.out.print("From ");
                    System.out.print(trans.getSender().getName());
                    System.out.print("(");
                    System.out.print("id = ");
                    System.out.print(trans.getSender().getName());
                    System.out.print(") ");
                }
                System.out.print(trans.getTransferAmount());
                System.out.println(" removed");
            }
        }
    }

    public void userTransactions() {
        int userId = inputItem.nextInt();
        for(Transaction trans : service.retrievingTransfer(userId)) {
            if(!trans.getRecipient().equals(service.getListUser().getUserId(userId))) {
                System.out.print("To ");
                System.out.print(trans.getRecipient().getName());
                System.out.print("(");
                System.out.print("id = ");
                System.out.print(trans.getRecipient().getName());
                System.out.print(") ");
            } else {
                System.out.print("From ");
                System.out.print(trans.getSender().getName());
                System.out.print("(");
                System.out.print("id = ");
                System.out.print(trans.getSender().getName());
                System.out.print(") ");
            }
            System.out.print(trans.getTransferAmount());
            System.out.print(" with id = ");
            System.out.println(trans.getIdentifier());
        }
    }

    public void performTransfer() {
        int senderId = inputItem.nextInt();
        int recipientId = inputItem.nextInt();
        int amount = inputItem.nextInt();
        service.perfomingTransaction(recipientId, senderId, amount);
    }

    public void viewBalance() {
        int userId = inputItem.nextInt();
        int balance = service.retrievingBalance(userId);
        System.out.print(service.getListUser().getUserId(userId).getName());
        System.out.println(" - " + balance);
    }

    public void addUser() {
        String userName = inputItem.next();
        int balance = inputItem.nextInt();
        service.addUser(userName, balance);
        System.out.println("User with id = " + service.getListUser().getNumberUsers() + " is added");
    }

    public void chooseNumber(int number) {
        switch(number) {
            case(1):
                System.out.println("Enter a user name and a balance");
                addUser();
                break;
            case(2):
                System.out.println("Enter a user ID");
                viewBalance();
                break;
            case(3):
                System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
                performTransfer();
                break;
            case(4):
                System.out.println("Enter a user ID");
                userTransactions();
                break;
            case(5):
                System.out.println("Enter a user ID and a transfer ID");
                removeTransfer();
                break;
            case(6):
                System.out.println("Check results:");
                checkTransactions();
                break;
            default:
                System.err.println("There is no such item in the menu");
                break;
        }
    }

    public void printInterface(String launch) {
        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        if(launch.equals("dev")) {
            System.out.println("5. DEV – remove a transfer by ID");
            System.out.println("6. DEV – check transfer validity");
            System.out.println("7. Finish execution");
        } else {
            System.out.println("5. Finish execution");
        }
    }
}
