package kr.kakaocloud.kakeulgae.controller;

import static kr.kakaocloud.kakeulgae.support.JobPostingFixture.createJobPosting;
import static kr.kakaocloud.kakeulgae.support.MemberFixture.MEMBER_ID;
import static kr.kakaocloud.kakeulgae.support.MemberFixture.createMember;
import static kr.kakaocloud.kakeulgae.support.NotificationFixture.DEFAULT_LAST_ID;
import static kr.kakaocloud.kakeulgae.support.NotificationFixture.DEFAULT_SIZE;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;
import kr.kakaocloud.kakeulgae.service.NotificationService;
import kr.kakaocloud.kakeulgae.service.dto.SliceResponse.SliceResponse;
import kr.kakaocloud.kakeulgae.service.dto.notification.NotificationListDto;
import kr.kakaocloud.kakeulgae.support.NotificationFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificationService notificationService;

    @Test
    public void getNotificationListTest() throws Exception {
        NotificationListDto notificationListDto = NotificationFixture.createNotificationListDto(
            createMember(), createJobPosting(null, null));// replace with actual data
        SliceResponse<NotificationListDto> sliceResponse = new SliceResponse<>(DEFAULT_SIZE,
            Collections.singletonList(notificationListDto));
        given(notificationService.getNotificationList(MEMBER_ID, DEFAULT_SIZE,
            DEFAULT_LAST_ID)).willReturn(
            sliceResponse);

        mockMvc.perform(get("/api/v1/notifications/list")

                .headers(NotificationFixture.createHeaders()
                .param("size", String.valueOf(DEFAULT_SIZE))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.content[0]", is(notificationListDto)));
    }
}
