package ex02;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("John", 1000);
        User user2 = new User("Mike", 500);
        User user3 = new User("Alex", 2000);
        User user4 = new User("Jo", 5000);
        User user5 = new User("Nick", 10000);
        User user6 = new User("Dine", 1000);
        User user7 = new User("Jin", 500);
        User user8 = new User("Roman", 2000);
        User user9 = new User("Phillip", 5000);
        User user10 = new User("Tim", 10000);
        User user11 = new User("Greg", 100000000);

        UserList list = new UsersArrayList();
        list.addUser(user1);
        list.addUser(user2);
        list.addUser(user3);
        list.addUser(user4);
        list.addUser(user5);
        list.addUser(user6);
        list.addUser(user7);
        list.addUser(user8);
        list.addUser(user9);
        list.addUser(user10);
        list.addUser(user11);

        System.out.println("User obtained using the method getUserId(8): " + list.getUserId(8));
        System.out.println("User obtained using the method getUserIndex(6): " + list.getUserIndex(6));
        System.out.println("The number of users: " + list.getNumberUsers());

        System.out.println();
        try {
            System.out.println(list.getUserId(12));
        } catch (UserNotFoundException error) {
            System.err.println(error.getMessage());
        }

        try {
            System.out.println(list.getUserIndex(21));
        } catch (UserNotFoundException error) {
            System.err.println(error.getMessage());
        }
    }
}
