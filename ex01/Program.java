package ex01;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("John", 1000);
        User user2 = new User("Mike", 2000);
        User user3 = new User("Nastya", 3000);

        System.out.println(user1);
        System.out.println(user2);
        System.out.println(user3);

        System.out.println();
        User user4 = new User("ERROR", -100);
    }
}
