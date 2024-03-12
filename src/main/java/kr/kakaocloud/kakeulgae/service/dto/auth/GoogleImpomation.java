package kr.kakaocloud.kakeulgae.service.dto.auth;

public class GoogleImpomation { //구글에서 받아온 사용자 정보를 담는 클래스
    public String email;
    public String profileUrl;
    public String memberName;

    public GoogleImpomation(String email, String profileUrl, String memberName) {
        this.email = email;
        this.profileUrl = profileUrl;
        this.memberName = memberName;
    }
}
