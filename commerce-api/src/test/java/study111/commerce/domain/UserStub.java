package study111.commerce.domain;

public class UserStub extends User {

    private UserStub(Long id, String username, String password, String email, String address) {
        super(id, username, password, email, address);
    }

    public static User of(Long id, String username, String password) {
        return new UserStub(id, username, password, null, null);
    }
}
