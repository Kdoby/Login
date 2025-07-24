package NotModified.User.controller;

import NotModified.User.domain.User;
import NotModified.User.dto.request.UserRequest;
import NotModified.User.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/session")
public class UserSessionController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest.Register dto) {
        userService.register(dto.getUserId(), dto.getPassword(), dto.getName(), dto.getEmail());
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserRequest.Login dto, HttpSession session) {
        User user = userService.login(dto.getUserId(), dto.getPassword());
        session.setAttribute("loginUser", user.getId());
        return ResponseEntity.ok("로그인 성공");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃 성공");
    }
}
