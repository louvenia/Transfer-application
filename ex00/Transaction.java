package ex00;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    private Category transferCategory;
    private Integer transferAmount;
    public enum Category {
        DEBITS, CREDITS
    }

    public Transaction(User recipient, User sender, Category transferCategory, Integer transferAmount) {
        setIdentifier(UUID.randomUUID());
        setRecipient(recipient);
        setSender(sender);
        setTransferCategory(transferCategory);
        setTransferAmount(transferAmount);
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Category getTransferCategory() {
        return transferCategory;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier = identifier;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setTransferCategory(Category transferCategory) {
        this.transferCategory = transferCategory;
    }

    public void setTransferAmount(Integer transferAmount) {
        if((transferCategory == Category.DEBITS && transferAmount > 0) ||
            (transferCategory == Category.CREDITS && transferAmount < 0)) {
            this.transferAmount = transferAmount;
            rebalancing();
        } else {
            System.err.println("The balance for the outgoing (negative amounts only) and incoming (positive amounts only) transactions");
        }
    }

    public void rebalancing() {
        if((recipient.getBalance() - transferAmount) >= 0 && (sender.getBalance() + transferAmount) >= 0) {
            recipient.setBalance(recipient.getBalance() - transferAmount);
            sender.setBalance(sender.getBalance() + transferAmount);
        } else {
            System.err.println("Insufficient funds to complete the transaction");
        }
    }

    @Override
    public String toString() {
        return "[Transaction data: " +
                "ID: " + getIdentifier() +
                ", Recipient: " + getRecipient().getName() +
                ", Sender: " + getSender().getName() +
                ", Category: " + getTransferCategory() +
                ", Amount: " + getTransferAmount() + "]";
    }
}
