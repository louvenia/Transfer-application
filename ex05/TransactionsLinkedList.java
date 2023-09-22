package ex05;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private Node head;
    private Node tail;
    private Integer size;

    private static class Node {
        Node(Transaction transaction) {
            this.transaction = transaction;
            next = null;
            prev = null;
        }

        public Node next;
        public Node prev;
        public Transaction transaction;
    }

    public TransactionsLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public Integer getSize() {
        return size;
    }

    public void addTransaction(Transaction transaction) {
        Node newNode = new Node(transaction);
        if (size == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        ++size;
    }

    public void removeTransaction(String uuid) {
        Node current = head;
        while (current != null) {
            if (current.transaction.getIdentifier().toString().equals(uuid)) {
                if (current == head) {
                    removeHead(current);
                } else if (current == tail) {
                    removeTail(current);
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                --size;
                return;
            }
            current = current.next;
        }
        throw new TransactionNotFoundException("Transaction with this uuid was not found");
    }

    private void removeHead(Node current) {
        head = current.next;
        if (head != null) {
            head.prev = null;
        }
        if (current == tail) {
            tail = null;
        }
    }

    private void removeTail(Node current) {
        tail = current.prev;
        tail.next = null;
    }

    public Transaction[] toArray() {
        Transaction[] array = new Transaction[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            array[index] = current.transaction;
            current = current.next;
            ++index;
        }
        return array;
    }
}
