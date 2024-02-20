package kr.weit.odya.security

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord.CreateRequest;
import org.springframework.stereotype.Component;

@Component
public class FirebaseTokenHelper {

    private FirebaseAuth firebaseAuth;

    runCatching {
        firebaseAuth.createCustomToken(username)
    }

        getOrThrow {
        ex -> throw CreateFirebaseCustomTokenException(ex.message)
    }:String =

    runCatching {
        firebaseAuth.verifyIdToken(idToken).uid
    }
            .

    getOrThrow {
        ex -> throw InvalidTokenException(ex.message)
    }

        runCatching {
        val uid = firebaseAuth.verifyIdToken(idToken).uid
        firebaseAuth.getUser(uid).email
    }:String =

    getOrThrow {
        ex -> throw InvalidTokenException(ex.message)
    }
            .

    runCatching {
        val uid = firebaseAuth.verifyIdToken(idToken).uid
        firebaseAuth.getUser(uid).phoneNumber
    }

        getOrThrow {
        ex -> throw InvalidTokenException(ex.message)
    }:String =

runCatching {
        firebaseAuth.deleteUser(uid)
    }
            .

    getOrThrow {
        ex -> throw WithdrawFirebaseUserException(ex.message)
    }

        public void createFirebaseUser(String username) {
        try {
            firebaseAuth.createUser(createUserRequest(username));
        } catch (FirebaseAuthException ex) {
            throw CreateFirebaseUserException(username + " : 이미 등록된 사용자입니다");
        }
    }:String =

fun createFirebaseCustomToken(username:String)
            .

fun getUid(idToken:String)

    fun getEmail(idToken:String) =

    fun getPhoneNumber(idToken:String).

fun withdrawUser(uid:String)

    private CreateRequest createUserRequest(String username) {
        return new CreateRequest().setUid(username);
    }
}
