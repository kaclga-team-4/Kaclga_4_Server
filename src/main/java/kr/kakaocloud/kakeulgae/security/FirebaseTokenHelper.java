package kr.kakaocloud.kakeulgae.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord.CreateRequest;
import kr.kakaocloud.kakeulgae.support.exception.firebaseException.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FirebaseTokenHelper {

    private final FirebaseAuth firebaseAuth;

    public String getUid(String idToken) {
        try {
            return firebaseAuth.verifyIdToken(idToken).getUid();
        } catch (Exception ex) {
            throw new InvalidTokenException(ex.getMessage());
        }
    }

    private CreateRequest createUserRequest(String memberName) {
        return new CreateRequest().setUid(memberName);
    }
}
