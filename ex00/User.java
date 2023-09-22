package ex00;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;

    public User(Integer identifier, String name, Integer balance) {
        setIdentifier(identifier);
        setName(name);
        if(balance >= 0) {
            setBalance(balance);
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

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Integer balance) {
        if(balance >= 0) {
            this.balance = balance;
        }
    }

    public void printData() {
        System.out.println("User data:");
        System.out.println("ID: " + getIdentifier());
        System.out.println("Name: " + getName());
        System.out.println("Balance: " + getBalance());
        System.out.println();
    }
}
