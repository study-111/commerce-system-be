package study111.commerce.domain;

public class UserStub extends User {

    public static User of(Long id, String username, String password) {
        return new User(id, username, password);
    }
}
