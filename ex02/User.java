package ex02;

public class User {
    private final Integer identifier;
    private String name;
    private Integer balance;

    public User(String name, Integer balance) {
        identifier = UserIdsGenerator.getInstance().generateId();
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

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(Integer balance) {
        if(balance >= 0) {
            this.balance = balance;
        }
    }

    @Override
    public String toString() {
        return "[User data: " +
                "ID: " + getIdentifier() +
                ", Name: " + getName() +
                ", Balance: " + getBalance() + "]";
    }
}
