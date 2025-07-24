package NotModified.User.controller;

import NotModified.User.domain.User;
import NotModified.User.dto.request.UserRequest;
import NotModified.User.jwt.JwtTokenProvider;
import NotModified.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/jwt")
public class UserJwtController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest.Register dto) {
        userService.register(dto.getUserId(), dto.getPassword(), dto.getName(), dto.getEmail());
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest.Login dto) {
        User user = userService.login(dto.getUserId(), dto.getPassword());
        String token = jwtTokenProvider.createToken(user.getUserId());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/me")
    public ResponseEntity<String> me(Authentication authentication) {
        String userId = (String) authentication.getPrincipal();
        return ResponseEntity.ok("현재 로그인된 유저: " + userId);
    }
}
