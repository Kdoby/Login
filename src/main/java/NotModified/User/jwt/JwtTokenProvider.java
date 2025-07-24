package NotModified.User.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
// JWT Util : 토근 생성 및 검증
public class JwtTokenProvider {
    // secret key
    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // 유효 기간 = 1시간
    private final long validityInMilliseconds = 1000 * 60 * 60;

    // 로그인 성공 시, 토큰 발급
    public String createToken(String userId) {
        // JWT 내부에 사용자 정보 저장
        Claims claims = Jwts.claims().setSubject(userId);

        // 발급 시간
        Date now = new Date();
        // 만료 시간
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                // 서명 생성
                .signWith(key)
                // 최종 문자열 토큰 생성
                .compact();
    }

    // JWT 토큰을 파싱해서 사용자 ID를 알아냄
    public String getUserId(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    // JWT 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

}
