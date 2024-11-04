package study111.commerce.dto;

import lombok.Getter;

@Getter
public class ResponsePayload<TData> {

    private TData data;

    public ResponsePayload() {
    }

    public ResponsePayload(TData data) {
        this.data = data;
    }

    public static <TData> ResponsePayload<TData> of(TData data) {
        return new ResponsePayload<>(data);
    }
}
