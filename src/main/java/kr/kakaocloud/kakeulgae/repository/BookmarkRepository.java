package kr.kakaocloud.kakeulgae.domain.repository;

import kr.kakaocloud.kakeulgae.domain.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

}
