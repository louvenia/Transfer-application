package ex01;

public class UserIdsGenerator {
    private static UserIdsGenerator userID;
    private static Integer identifier = 0;

    public static synchronized UserIdsGenerator getInstance() {
        if(userID == null) {
            userID = new UserIdsGenerator();
        }
        return userID;
    }

    public int generateId() {
        identifier += 1;
        return identifier;
    }
}
