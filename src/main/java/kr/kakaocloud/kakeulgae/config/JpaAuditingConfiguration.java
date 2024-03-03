package kr.kakaocloud.kakeulgae.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing//createDate, modifiedDate를 자동으로 관리
class JpaAuditingConfiguration{

}
