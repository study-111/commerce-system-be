package study111.commerce.response;

import lombok.Getter;

@Getter
public class CommonResponse<TData> {

    private TData data;

    public CommonResponse() {
    }

    public CommonResponse(TData data) {
        this.data = data;
    }

    public static <TData> CommonResponse<TData> of(TData data) {
        return new CommonResponse<>(data);
    }
}
