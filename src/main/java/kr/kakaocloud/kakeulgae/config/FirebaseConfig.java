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
class FirebaseConfig{
    private final ResourceLoader resourceLoader;
    @Bean
    public FirebaseApp firebaseApp() throws IOException {//FirebaseApp을 Bean으로 등록
        Resource serviceAccount =
            resourceLoader.getResource("classpath:/firebase/it-s-goal-keeper-firebase-adminsdk-g1vhy-e51f76927c.json");
        //Firebase Admin SDK에서 다운로드한 JSON 파일을 FileInputStream으로 읽어온다.(token 생성을 위해 필요한 정보가 담겨있음)
        FirebaseOptions options =  FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
               .build();//FirebaseOptions를 생성한다.(FirebaseOptions는 FirebaseApp을 초기화하는데 필요한 설정을 담고있음)
        return FirebaseApp.initializeApp(options);//FirebaseApp을 초기화한다.
    }

    @Bean
    public FirebaseAuth firebaseAuth() throws IOException {
        return FirebaseAuth.getInstance(firebaseApp());
    }

}
