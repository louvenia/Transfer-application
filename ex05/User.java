package ex05;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;
    private TransactionsLinkedList listTrans;

    public User(String name, Integer balance) {
        if(balance >= 0) {
            identifier = UserIdsGenerator.getInstance().generateId();
            setName(name);
            setBalance(balance);
            listTrans = new TransactionsLinkedList();
        } else {
            System.err.println("The initial user balance (it cannot be negative)");
        }
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public Integer getBalance() {
        return balance;
    }

    public TransactionsLinkedList getTransList() {
        return listTrans;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Integer balance) {
        if(balance >= 0) {
            this.balance = balance;
        }
    }

    public void setListTrans(Transaction trans) {
        listTrans.addTransaction(trans);
    }

    public void printData() {
        System.out.println("User data:");
        System.out.println("ID: " + getIdentifier());
        System.out.println("Name: " + getName());
        System.out.println("Balance: " + getBalance());
        System.out.println();
    }
}
