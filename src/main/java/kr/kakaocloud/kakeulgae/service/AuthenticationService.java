package kr.kakaocloud.kakeulgae.service;

import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.Profile;
import kr.kakaocloud.kakeulgae.repository.MemberRepository;
import kr.kakaocloud.kakeulgae.security.FirebaseTokenHelper;
import kr.kakaocloud.kakeulgae.service.dto.auth.RegisterRequest;
import kr.kakaocloud.kakeulgae.support.exception.KakeulgaeException.ExistResourceException;
import kr.kakaocloud.kakeulgae.support.exception.KakeulgaeException.UnRegisteredMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    final String OAUTH_ACCESS_TOKEN_TYPE = "BEARER";
   final MemberRepository memberRepository;
    final FirebaseTokenHelper firebaseTokenHelper;

    public void googleLoginProcess(String googleMemberName) {
        if (!memberRepository.existsAllByMemberName(googleMemberName)) {
            throw new UnRegisteredMemberException("$googleMemberName: 존재하지 않는 회원입니다");
        }
    }

    @Transactional
    public void register(RegisterRequest registerRequest) {
        validateRegisterInformation(registerRequest);
        memberRepository.save(createMember(registerRequest));
    }

    private void validateRegisterInformation(RegisterRequest registerRequest) {
        if (memberRepository.existsAllByMemberName(registerRequest.memberName)) {
            throw new ExistResourceException(STR."\{registerRequest.memberName} : 이미 존재하는 회원입니다");
        }
        validateEmail(registerRequest.email);
        validatePhoneNumber(registerRequest.phoneNumber);
        validateNickname(registerRequest.nickname);
    }

    public void validateNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new ExistResourceException(
                STR."\{nickname}이미 존재하는 닉네임입니다");//대표적인 JDK21의 기능 (nickname+"이미 존재하는 닉네임입니다")으로 안 해도됨
        }
    }

    public void validateEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new ExistResourceException(STR."\{email}: 이미 존재하는 이메일입니다");
        }
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (memberRepository.existsByPhoneNumber(phoneNumber)) {
            throw new ExistResourceException(STR."\{phoneNumber}: 이미 존재하는 전화번호입니다");
        }
    }

    public String getMemberNameByIdToken(String idToken) {
        return firebaseTokenHelper.getUid(idToken);
    }

    private Member createMember(
        RegisterRequest registerRequest) {
        return Member.builder()
            .email(registerRequest.email)
            .memberName(registerRequest.memberName)
            .nickname(registerRequest.nickname)
            .phoneNumber(registerRequest.phoneNumber)
            .gender(registerRequest.gender)
            .birthday(registerRequest.birthday)
            .socialType(registerRequest.socialType)
            .memberRole(registerRequest.memberRole)
            .profile(new Profile("default", "default.jpg"))
            .build();
    }
}
