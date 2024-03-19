package kr.kakaocloud.kakeulgae.service;

import java.io.InputStream;
import java.net.URL;
import javax.annotation.Nullable;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.Profile;
import kr.kakaocloud.kakeulgae.repository.MemberRepository;
import kr.kakaocloud.kakeulgae.security.FirebaseTokenHelper;
import kr.kakaocloud.kakeulgae.service.dto.auth.GoogleImpomation;
import kr.kakaocloud.kakeulgae.service.dto.auth.RegisterRequest;
import kr.kakaocloud.kakeulgae.support.exception.KakeulgaeException.ExistResourceException;
import kr.kakaocloud.kakeulgae.support.exception.KakeulgaeException.UnRegisteredMemberException;
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


    public void googleLoginProcess(String googleMemberName) {
        if (!memberRepository.existsAllByMemberName(googleMemberName)) {
            throw new UnRegisteredMemberException("$googleMemberName: 존재하지 않는 회원입니다");
        }
    }

    @Transactional
    public void register(RegisterRequest registerRequest, @Nullable String profileUrl) {
        validateRegisterInformation(registerRequest);

        memberRepository.save(createMember(registerRequest,profileUrl));
    }

    private void validateRegisterInformation(RegisterRequest registerRequest) {
        if (memberRepository.existsAllByMemberName(registerRequest.memberName)) {
            throw new ExistResourceException(STR."\{registerRequest.memberName} : 이미 존재하는 회원입니다");
        }
        validateEmail(registerRequest.email); //이메일 중복 검사
        validatePhoneNumber(registerRequest.phoneNumber); //전화번호 중복 검사
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

    public GoogleImpomation getGoogleinfomation(String idToken) {
        return new GoogleImpomation(firebaseTokenHelper.getEmail(idToken), firebaseTokenHelper.getProfileurl(idToken), getMemberNameByIdToken(idToken));
    }

    private String saveProfile(String profileUrl)  {
        InputStream inputStream = null; //InputStream은 바이트 단위로 읽어들이는 클래스
        try {
            URL url = new URL(profileUrl);
            inputStream = url.openStream(); //url.openStream()은 url을 통해 읽어들인 스트림을 반환
            return fileService.saveProfile(inputStream);
        } catch (Exception e) {
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close(); //inputStream을 닫음
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    private Member createMember(
        RegisterRequest registerRequest, String profileUrl) {
        String profileName = saveProfile(profileUrl);
        Profile profile;
        if (profileName != null) {
             profile = new Profile(profileName);
        }else {
            profile = new Profile("default.webp");
        }
        return Member.builder()
            .email(registerRequest.email)
            .memberName(registerRequest.memberName)
            .nickname(registerRequest.nickname)
            .phoneNumber(registerRequest.phoneNumber)
            .gender(registerRequest.gender)
            .birthday(registerRequest.birthday)
            .socialType(registerRequest.socialType)
            .memberRole(registerRequest.memberRole)
            .profile(profile)
            .build();
    }
}