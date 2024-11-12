package study111.commerce.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserModifyRequest {

    @NotBlank
    private String username;
    private String email;
    private String address;

}
