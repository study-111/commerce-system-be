package study111.exception;

public class SomethingException extends CustomException {

    public SomethingException(String message) {
        super(message);
    }

    @Override
    public int getStatus() {
        return 403;
    }
}
