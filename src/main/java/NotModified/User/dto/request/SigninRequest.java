package NotModified.User.dto.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SigninRequest {
    private String username;
    private String password;
}
