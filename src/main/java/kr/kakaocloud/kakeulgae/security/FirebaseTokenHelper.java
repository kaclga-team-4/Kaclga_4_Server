package kr.kakaocloud.kakeulgae.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord.CreateRequest;
import kr.kakaocloud.kakeulgae.support.exception.firebaseException.CreateFirebaseUserException;
import kr.kakaocloud.kakeulgae.support.exception.firebaseException.InvalidTokenException;
import org.springframework.stereotype.Component;

@Component
public class FirebaseTokenHelper {

    private FirebaseAuth firebaseAuth;

    public void createFirebaseUser(String memberName) {
        try {
            firebaseAuth.createUser(createUserRequest(memberName));
        } catch (FirebaseAuthException ex) {
            throw new CreateFirebaseUserException(STR."\{memberName} : 이미 등록된 사용자입니다");
        }
    }

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
