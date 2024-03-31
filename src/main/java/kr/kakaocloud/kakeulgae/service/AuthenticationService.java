package kr.kakaocloud.kakeulgae.service;

import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.Profile;
import kr.kakaocloud.kakeulgae.repository.MemberRepository;
import kr.kakaocloud.kakeulgae.security.FirebaseTokenHelper;
import kr.kakaocloud.kakeulgae.service.dto.auth.GoogleInformation;
import kr.kakaocloud.kakeulgae.service.dto.auth.GoogleRegisterRequest;
import kr.kakaocloud.kakeulgae.service.dto.auth.RegisterRequest;
import kr.kakaocloud.kakeulgae.service.dto.member.MemberSimpleResponse;
import kr.kakaocloud.kakeulgae.support.exception.KakeulgaeException.ExistResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final static String OAUTH_ACCESS_TOKEN_TYPE = "BEARER";
    final MemberRepository memberRepository;
    final FirebaseTokenHelper firebaseTokenHelper; //firebaseTokenHelper는 구글 토큰을 파싱하는 클래스
    final FileService fileService; //fileService는 파일을 저장하는 클래스

    @Transactional
    public MemberSimpleResponse register(GoogleRegisterRequest registerRequest) {
        GoogleInformation googleInformation = getGoogleinfomation(
            registerRequest.getIdToken());//구글 정보를 업데이트
        registerRequest.updateMemberImpomation(googleInformation);
        validateRegisterInformation(registerRequest);
        Member member = memberRepository.save(
            createMember(registerRequest, googleInformation.profileUrl));
        return new MemberSimpleResponse(member);
    }

    private void validateRegisterInformation(RegisterRequest registerRequest) {
        if (memberRepository.existsAllByMemberName(registerRequest.memberName)) {
            throw new ExistResourceException(STR."\{registerRequest.memberName} : 이미 존재하는 회원입니다");
        }
        validateEmail(registerRequest.email); //이메일 중복 검사
        validateNickname(registerRequest.nickname); //닉네임 중복 검사
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
        return firebaseTokenHelper.getUid(idToken); //firebaseTokenHelper는 구글 토큰을 파싱하는 클래스
    }

    public GoogleInformation getGoogleinfomation(String idToken) {
        return new GoogleInformation(firebaseTokenHelper.getProfileurl(idToken),
            getMemberNameByIdToken(idToken));
    }

    private Member createMember(
        RegisterRequest registerRequest, String profileUrl) {
        return Member.builder()
            .email(registerRequest.email)
            .memberName(registerRequest.memberName)
            .nickname(registerRequest.nickname)
            .phoneNumber(registerRequest.phoneNumber)
            .gender(registerRequest.gender)
            .birthday(registerRequest.birthday)
            .socialType(registerRequest.socialType)
            .memberRole(registerRequest.memberRole)
            .profile(new Profile(profileUrl))
            .build();
    }
}
