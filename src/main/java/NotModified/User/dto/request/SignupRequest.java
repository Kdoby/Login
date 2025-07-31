package NotModified.User.dto.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignupRequest {
    private String username;
    private String password;
    private String email;
}
