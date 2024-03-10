package kr.kakaocloud.kakeulgae.security;

import com.google.firebase.auth.FirebaseAuth;
import kr.kakaocloud.kakeulgae.support.exception.firebaseException.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component//스프링 빈으로 등록
@RequiredArgsConstructor
public class FirebaseTokenHelper { //Firebase에서 발급받은 토큰을 검증하는 클래스

    private final FirebaseAuth firebaseAuth;//Firebase 인증 객체로써 토큰을 검증하는 기능을 제공

    public String getUid(String idToken) {
        try {
            return firebaseAuth.verifyIdToken(idToken).getUid();
        } catch (Exception ex) {
            throw new InvalidTokenException(ex.getMessage());
        }
    }

    public String getEmail(String idToken) {
        try {
            return firebaseAuth.verifyIdToken(idToken).getEmail();
        } catch (Exception ex) {
            throw new InvalidTokenException(ex.getMessage());
        }
    }

    public String getProfileurl(String idToken) { //사용자의 프로필 사진을 반환(프로필 사진이 없을 경우 null 반환)
        try {
            return firebaseAuth.verifyIdToken(idToken).getPicture();
        } catch (Exception ex) {
            throw new InvalidTokenException(ex.getMessage());
        }
    }

    public String getMemberName(String idToken) { //사용자의 이름을 반환(이름이 없을 경우 null 반환)
        try {
            return firebaseAuth.verifyIdToken(idToken).getName();
        } catch (Exception ex) {
            throw new InvalidTokenException(ex.getMessage());
        }
    }
}
