package ex04;

import java.util.UUID;

public class TransactionsService {
    private final UserList listUser = new UsersArrayList();
    private Integer countRemove = 0;

    public void addUser(String userName, Integer balance) {
        User user = new User(userName, balance);
        listUser.addUser(user);
    }

    public UsersArrayList getListUser() {
        return (UsersArrayList) listUser;
    }

    public Integer retrievingBalance(Integer userId) {
        return listUser.getUserId(userId).getBalance();
    }

    public void perfomingTransaction(Integer recipientID, Integer senderID, Integer amount) {
        User recipient = listUser.getUserId(recipientID);
        User sender = listUser.getUserId(senderID);
        if(sender.getBalance() < amount) {
            throw new IllegalTransactionException("An attempt to make a transfer of the amount exceeding user's residual balance");
        } else if (senderID.equals(recipientID)) {
            throw new IllegalTransactionException("The transaction can't be made between the same user");
        } else if (amount <= 0) {
            throw new IllegalTransactionException("The transfer amount can't be less than or equal to zero");
        } else {
            Transaction transDebit = new Transaction(recipient, sender, Transaction.Category.DEBITS, amount);
            Transaction transCredit = new Transaction(sender, recipient, Transaction.Category.CREDITS, -amount);
            transCredit.setIdentifier(transDebit.getIdentifier());
            recipient.setListTrans(transDebit);
            recipient.setBalance(recipient.getBalance() + amount);
            sender.setListTrans(transCredit);
            sender.setBalance(sender.getBalance() - amount);
        }
    }

    public Transaction[] retrievingTransfer(Integer userId) {
        return listUser.getUserId(userId).getTransList().toArray();
    }

    public void removeTransaction(Integer userId, UUID transId) {
        TransactionsList list = listUser.getUserId(userId).getTransList();
        list.removeTransaction(transId);
        countRemove++;
    }

    public Transaction[] validityTransactions() {
        Transaction[] unpairedArr = new Transaction[countRemove];
        int countUnpaired = 0;
        for(int i = 0; i < listUser.getNumberUsers(); i++) {
            for (Transaction trans : listUser.getUserIndex(i).getTransList().toArray()) {
                boolean flagUnpaired = false;
                if(!trans.getRecipient().equals(listUser.getUserIndex(i))) {
                    for(Transaction transRecipient : trans.getRecipient().getTransList().toArray()) {
                        if(transRecipient.getIdentifier().equals(trans.getIdentifier())) {
                            flagUnpaired = true;
                        }
                    }
                }  else if (!trans.getSender().equals(listUser.getUserIndex(i))) {
                    for(Transaction transSender : trans.getSender().getTransList().toArray()) {
                        if(transSender.getIdentifier().equals(trans.getIdentifier())) {
                            flagUnpaired = true;
                        }
                    }
                }
                if(!flagUnpaired) {
                    unpairedArr[countUnpaired] = trans;
                    countUnpaired++;
                }
            }
        }
        return unpairedArr;
    }
}
