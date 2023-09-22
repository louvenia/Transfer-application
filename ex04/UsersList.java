package ex04;

interface UserList {
    void addUser(User newUser);
    User getUserId(Integer id);
    User getUserIndex(Integer index);
    Integer getNumberUsers();
}