package NotModified.User.service;

import NotModified.User.domain.User;
import NotModified.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User register(String userId, String password, String name, String email) {
        if(userRepository.findByUserId(userId).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 ID입니다.");
        }

        User user = User.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .email(email)
                .build();

        userRepository.save(user);

        return user;
    }

    public User login(String userId, String password) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다."));
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }
}
