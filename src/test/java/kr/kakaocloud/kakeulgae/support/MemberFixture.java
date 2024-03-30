package kr.kakaocloud.kakeulgae.support;

import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.Profile;

public class MemberFixture {

    public final static Long MEMBER_ID = 1L;
    final static String MEMBER_NAME = "memberName";
    final static String MEMBER_EMAIL = "memberEmail";
    final static String MEMBER_NICKNAME = "memberNickname";
    final static String MEMBER_PHONE = "memberPhone";
    final static Long PROFILE_ID = 1L;
    final static String PROFILE_NAME = "profileName";

    public static Member createMember() {
        return new Member(MEMBER_ID, MEMBER_NAME, MEMBER_EMAIL, MEMBER_PHONE, MEMBER_NICKNAME,
            createProfile(PROFILE_ID, PROFILE_NAME));
    }

    public static Profile createProfile(Long id, String profileName) {
        return new Profile(id, profileName);
    }
}
