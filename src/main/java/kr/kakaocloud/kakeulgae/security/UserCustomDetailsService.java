package kr.kakaocloud.kakeulgae.security;

import java.util.List;
import java.util.NoSuchElementException;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCustomDetailsService implements UserDetailsService {
    final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberName) {
        Member member = memberRepository.findByMemberName(memberName);
        if (member == null) {
            throw new NoSuchElementException(STR."\{memberName}사용자가 존재하지 않습니다");
        } else {
        return new User(member.getId().toString(),"", List.of(new SimpleGrantedAuthority(member.getMemberRole().name())));//SimpleGrantedAuthority는 권한을 나타내는 클래스
        }
    }
}
