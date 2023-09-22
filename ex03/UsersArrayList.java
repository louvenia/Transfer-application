package ex03;

public class UsersArrayList implements UserList {
    private Integer sizeArr = 10;
    private User[] users = new User[sizeArr];
    private Integer number = 0;

    public void addUser(User newUser) {
        if (!number.equals(sizeArr)) {
            this.users[number++] = newUser;
        } else {
            User[] users = new User[sizeArr * 2];
            System.arraycopy(this.users, 0, users, 0, sizeArr);
            sizeArr = sizeArr * 2;
            this.users = users;
            this.users[number++] = newUser;
        }
    }

    public User getUserId(Integer id) {
        for (int i = 0; i < number; i++) {
            if (users[i].getIdentifier().equals(id)) {
                return users[i];
            }
        }
        throw new UserNotFoundException("User with this id was not found");
    }

    public User getUserIndex(Integer index) {
        if (index >= 0 && index <= number) {
            return users[index];
        }
        throw new UserNotFoundException("User with this index was not found");
    }

    public Integer getNumberUsers() {
        return number;
    }

    public Integer getSizeArr() {
        return sizeArr;
    }
}
