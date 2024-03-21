package kr.kakaocloud.kakeulgae.service.dto.auth;

public class GoogleInformation { //구글에서 받아온 사용자 정보를 담는 클래스

    public String profileUrl;
    public String memberName;

    public GoogleInformation(String profileUrl, String memberName) {
        this.profileUrl = profileUrl;
        this.memberName = memberName;
    }
}
