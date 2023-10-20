package ex04;

import java.util.UUID;

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

    @Override
    public String toString() {
        return "[User data: " +
                "ID: " + getIdentifier() +
                ", Name: " + getName() +
                ", Balance: " + getBalance() + "]";
    }
}
