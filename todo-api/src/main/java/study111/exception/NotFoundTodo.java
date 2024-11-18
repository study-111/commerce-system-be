package study111.exception;

import lombok.Getter;

@Getter
public class NotFoundTodo extends CustomException {

    private static final String MESSAGE = "할 일 찾을 수 없습니다.";

    public NotFoundTodo() {
        super(MESSAGE);
    }

    @Override
    public int getStatus() {
        return 404;
    }
}
