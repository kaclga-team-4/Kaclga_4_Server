package kr.kakaocloud.kakeulgae.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Configuration//스프링 컨테이너에게 해당 클래스를 Bean 구성 클래스임을 알려준다.
@RequiredArgsConstructor
class FirebaseConfig {

    private final ResourceLoader resourceLoader; //리소스를 읽어오기 위한 ResourceLoader

    @Bean
    public FirebaseApp firebaseApp()
        throws IOException {//FirebaseApp을 Bean으로 등록 (Bean으로 등록된 객체는 스프링 컨테이너에 의해 관리되며, DI를 통해 주입받을 수 있다.)
        Resource serviceAccount =
            resourceLoader.getResource(
                "classpath:/firebase/its-goal-keeper-firebase-adminsdk-6rej8-cbacbc8caf.json");
        //Firebase Admin SDK에서 다운로드한 JSON 파일을 FileInputStream으로 읽어온다.(token 생성을 위해 필요한 정보가 담겨있음)
        FirebaseOptions options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
            .build();//FirebaseOptions를 생성한다.(FirebaseOptions는 FirebaseApp을 초기화하는데 필요한 설정을 담고있음)
        if (FirebaseApp.getApps().isEmpty()) {
            // 초기화되어 있지 않은 경우에만 초기화 수행
            return FirebaseApp.initializeApp(options);
        } else {
            // 이미 초기화되어 있는 경우, 기존의 FirebaseApp 인스턴스를 반환
            return FirebaseApp.getInstance();
        }//FirebaseApp을 초기화한다. (FirebaseApp은 Firebase의 인증, 데이터베이스, 스토리지 등의 기능을 사용하기 위한 진입점)
    }

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        return FirebaseAuth.getInstance(
            firebaseApp()); //FirebaseApp을 통해 FirebaseAuth를 초기화한다. (FirebaseAuth는 Firebase Authentication 서비스를 사용하기 위한 진입점)
    }

}
