package kr.kakaocloud.kakeulgae.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AuthenticationService() {

    private static final String OAUTH_ACCESS_TOKEN_TYPE = "BEARER";
    private UserRepository userRepository;
    private ProfileColorService profileColorService;
    private FirebaseTokenHelper firebaseTokenHelper;
    private UsersDocumentRepository usersDocumentRepository;

    public void googleLoginProcess(String googleUsername) {
        if (!userRepository.existsByUsername(googleUsername)) {
            throw UnRegisteredUserException("$googleUsername: 존재하지 않는 회원입니다");
        }
    }

    @Transactional
    public void register(RegisterRequest registerRequest) {
        val termsIdList = registerRequest.termsIdList
        termsService.checkRequiredTerms(termsIdList)
        validateRegisterInformation(registerRequest)
        val randomProfileColor = profileColorService.getRandomProfileColor()
        val user = userRepository.save(createUser(registerRequest, randomProfileColor))
        if (registerRequest.socialType == SocialType.KAKAO) {
            firebaseTokenHelper.createFirebaseUser(registerRequest.username)
        }
        termsService.saveAllAgreedTerms(user, termsIdList)
        usersDocumentRepository.save(UsersDocument(user))
    }

    public void validateNickname(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw ExistResourceException(nickname + "이미 존재하는 닉네임입니다")
        }
    }

    public void validateEmail(email:String) {
        if (userRepository.existsByEmail(email)) {
            throw ExistResourceException("$email: 이미 존재하는 이메일입니다")
        }
    }

    public void validatePhoneNumber(phoneNumber:String) {
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw ExistResourceException("$phoneNumber: 이미 존재하는 전화번호입니다")
        }
    }

    public String getUsernameByIdToken(idToken:String):String =firebaseTokenHelper.getUid(idToken)


    private public String getBearerToken(
        oAuthAccessToken:String) ="$OAUTH_ACCESS_TOKEN_TYPE $oAuthAccessToken"

    private public void validateRegisterInformation(registerRequest:RegisterRequest) {
        if (userRepository.existsByUsername(registerRequest.username)) {
            throw ExistResourceException("${registerRequest.username}: 이미 존재하는 회원입니다")
        }

        registerRequest.apply {
            email ?.apply {
                validateEmail(this)
            }
            phoneNumber ?.apply {
                validatePhoneNumber(this)
            }
            validateNickname(nickname)
        }
    }

    private User createUser(
        registerRequest:RegisterRequest
    ) {
        User(
            username = registerRequest.username,
            email = registerRequest.email ?.trim(),
            nickname = registerRequest.nickname.trim(),
            phoneNumber = registerRequest.phoneNumber ?.trim(),
            gender = registerRequest.gender,
            birthday = registerRequest.birthday,
            socialType = registerRequest.socialType,
            profile = Profile(profileColor = randomProfileColor),
    )
    }
}
