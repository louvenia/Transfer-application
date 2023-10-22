package ex05;

import java.util.Scanner;

public class Menu {
    private final TransactionsService service = new TransactionsService();
    private final Scanner inputItem = new Scanner(System.in);

    public void generalWindow(String launch) {
        int numberPosition;
        printInterface(launch);
        try {
            numberPosition = checkItem();
            while((numberPosition != 7 && launch.equals("dev")) || (numberPosition != 5 && launch.equals("production"))) {
                chooseNumber(numberPosition);
                printInterface(launch);
                numberPosition = checkItem();
            }
        } catch(RuntimeException error) {
            error.printStackTrace();
            generalWindow(launch);
        }
        inputItem.close();
    }

    private int checkItem() {
        String[] strGen = inputItem.nextLine().trim().split(" ");
        if(strGen.length != 1) {
            throw new RuntimeException("You must enter one item number from the menu");
        }
        return Integer.parseInt(strGen[0]);
    }

    private void printInterface(String launch) {
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

    private void printDelimiter() {
        System.out.println("---------------------------------------------------------");
    }

    private void chooseNumber(int number) {
        switch(number) {
            case(1):
                addUser();
                break;
            case(2):
                viewBalance();
                break;
            case(3):
                performTransfer();
                break;
            case(4):
                userTransactions();
                break;
            case(5):
                removeTransfer();
                break;
            case(6):
                checkTransactions();
                break;
            default:
                System.err.println("There is no such item in the menu");
                printDelimiter();
                break;
        }
    }

    private void addUser() {
        System.out.println("Enter a user name and a balance");
        String[] addArr = inputItem.nextLine().trim().split(" ");
        try {
            if(addArr.length != 2) {
                throw new RuntimeException("There must be exactly 2 arguments to the function");
            }
            String userName = addArr[0];
            int balance = Integer.parseInt(addArr[1]);
            service.addUser(userName, balance);
            System.out.print("User with id = ");
            System.out.print(service.getListUser().getNumberUsers());
            System.out.println(" is added");
            printDelimiter();
        } catch(RuntimeException error) {
            error.printStackTrace();
            addUser();
        }
    }

    private void viewBalance() {
        System.out.println("Enter a user ID");
        String[] userIdStr = inputItem.nextLine().trim().split(" ");
        try {
            if(userIdStr.length != 1) {
                throw new RuntimeException("The function uses 1 argument");
            }
            int userId = Integer.parseInt(userIdStr[0]);
            int balance = service.retrievingBalance(userId);
            System.out.print(service.getListUser().getUserId(userId).getName());
            System.out.print(" - ");
            System.out.println(balance);
            printDelimiter();
        } catch(RuntimeException error) {
            error.printStackTrace();
            viewBalance();
        }
    }

    private void performTransfer() {
        System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");
        String[] addTransfer = inputItem.nextLine().trim().split(" ");
        try {
            if(addTransfer.length != 3) {
                throw new RuntimeException("There must be exactly 3 arguments to the function");
            }
            int senderId = Integer.parseInt(addTransfer[0]);
            int recipientId = Integer.parseInt(addTransfer[1]);
            int amount = Integer.parseInt(addTransfer[2]);
            service.perfomingTransaction(recipientId, senderId, amount);
            printDelimiter();
        } catch (RuntimeException error) {
            error.printStackTrace();
            performTransfer();
        }
    }

    private void infoUserTransaction(Transaction trans, boolean flag) {
        if(trans.getTransferCategory().equals(Transaction.Category.DEBITS)) {
            if(flag) {
                System.out.print("From ");
            } else {
                System.out.print(" from ");
            }
        } else {
            if(flag) {
                System.out.print("To ");
            } else {
                System.out.print(" to ");
            }
        }
        System.out.print(trans.getSender().getName());
        System.out.print("(");
        System.out.print("id = ");
        System.out.print(trans.getSender().getIdentifier());
        System.out.print(") ");
        if(flag) {
            System.out.print(trans.getTransferAmount());
        }
    }

    private void userTransactions() {
        System.out.println("Enter a user ID");
        String[] userStr = inputItem.nextLine().trim().split(" ");
        try {
            if(userStr.length != 1) {
                throw new RuntimeException("The function uses 1 argument");
            }
            int userId = Integer.parseInt(userStr[0]);
            for(Transaction trans : service.retrievingTransfer(userId)) {
                infoUserTransaction(trans, true);
                System.out.print(" with id = ");
                System.out.println(trans.getIdentifier());
            }
            printDelimiter();
        } catch (RuntimeException error) {
            error.printStackTrace();
            userTransactions();
        }
    }

    private void removeTransfer() {
        System.out.println("Enter a user ID and a transfer ID");
        String[] remTrans = inputItem.nextLine().trim().split(" ");
        try {
            if(remTrans.length != 2) {
                throw new RuntimeException("There must be exactly 2 arguments to the function");
            }
            int userId = Integer.parseInt(remTrans[0]);
            String transferId = remTrans[1];
            System.out.print("Transfer ");
            for(Transaction trans : service.retrievingTransfer(userId)) {
                if(trans.getIdentifier().toString().equals(transferId)) {
                    infoUserTransaction(trans, true);
                    System.out.println(" removed");
                }
            }
            printDelimiter();
            service.removeTransaction(userId, transferId);
        } catch (RuntimeException error) {
            error.printStackTrace();
            removeTransfer();
        }
    }

    private void checkTransactions() {
        System.out.println("Check results:");
        for(Transaction trans : service.validityTransactions()) {
            System.out.print(trans.getRecipient().getName());
            System.out.print("(");
            System.out.print("id = ");
            System.out.print(trans.getRecipient().getIdentifier());
            System.out.print(") ");
            System.out.print("has an unacknowledged transfer id = ");
            System.out.print(trans.getIdentifier());
            infoUserTransaction(trans, false);
            System.out.print("for ");
            System.out.println(trans.getTransferAmount());
        }
        printDelimiter();
    }
}