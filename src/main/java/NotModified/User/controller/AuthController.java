package NotModified.User.controller;

import NotModified.User.domain.Member;
import NotModified.User.dto.request.SigninRequest;
import NotModified.User.dto.request.SignupRequest;
import NotModified.User.dto.response.TokenResponse;
import NotModified.User.jwt.JwtTokenProvider;
import NotModified.User.repository.UserRepository;
import NotModified.User.security.CustomUserDetails;
import NotModified.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        userService.join(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody SigninRequest request) {
        return ResponseEntity.ok(userService.signin(request));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyInfo(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Member user = userDetails.getUser();
        return ResponseEntity.ok(user);
    }
}
