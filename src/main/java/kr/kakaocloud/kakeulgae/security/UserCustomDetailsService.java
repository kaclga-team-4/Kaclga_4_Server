package kr.kakaocloud.kakeulgae.security;

import java.util.List;
import java.util.NoSuchElementException;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.repository.MemberRepository;
import kr.kakaocloud.kakeulgae.repository.MemberRepositoryFunction;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
class UserCustomDetailsService implements UserDetailsService {
    private MemberRepository memberRepository;
    private MemberRepositoryFunction memberRepositoryFunction;

    @Override
    public UserDetails loadUserByUsername(String memberName) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberName(memberName);
        if (member == null) {
            throw new NoSuchElementException(STR."\{memberName}사용자가 존재하지 않습니다");
        } else {
        return new User(member.getId().toString(),"", List.of(new SimpleGrantedAuthority(member.getMemberRole().name())));
        }
    }
}
