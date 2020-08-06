package wooteco.subway.maps.map.documentation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.web.context.WebApplicationContext;
import wooteco.security.core.TokenResponse;
import wooteco.subway.common.documentation.Documentation;
import wooteco.subway.maps.map.application.MapService;
import wooteco.subway.maps.map.dto.PathResponse;
import wooteco.subway.maps.map.ui.MapController;
import wooteco.subway.maps.station.dto.StationResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;

@WebMvcTest(controllers = {MapController.class})
public class PathDocumentation extends Documentation {
    protected TokenResponse tokenResponse;
    @MockBean
    private MapService mapService;

    @BeforeEach
    public void setUp(WebApplicationContext context, RestDocumentationContextProvider restDocumentation) {
        super.setUp(context, restDocumentation);
        tokenResponse = new TokenResponse("token");
    }

    @Test
    void findPath() {
        String findPathURL = String.format("/paths?source=%d&target=%d&type=%s", 1L, 2L, "DISTANCE");

        List<StationResponse> stationResponses = new ArrayList<>();
        stationResponses.add(new StationResponse(1L, "광교중앙역", LocalDateTime.now(), LocalDateTime.now()));
        stationResponses.add(new StationResponse(2L, "잠실역", LocalDateTime.now(), LocalDateTime.now()));
        PathResponse pathResponse = new PathResponse(stationResponses, 3, 3, 1_250);
        when(mapService.findPath(any(), any(), any())).thenReturn(pathResponse);

        given().log().all().
            header("Authorization", "Bearer " + tokenResponse.getAccessToken()).
            when().
            get(findPathURL).
            then().
            log().all().
            apply(document("paths",
                           getDocumentRequest(),
                           getDocumentResponse(),
                           requestHeaders(
                               headerWithName("Authorization").description("Bearer auth credentials")),
                           responseFields(
                               fieldWithPath("stations[].id").type(JsonFieldType.NUMBER).description("경로에 속하는 역 id"),
                               fieldWithPath("stations[].name").type(JsonFieldType.STRING).description("경로에 속하는 역 이름"),
                               fieldWithPath("duration").type(JsonFieldType.NUMBER).description("최소 시간"),
                               fieldWithPath("distance").type(JsonFieldType.NUMBER).description("최단 거리"),
                               fieldWithPath("fare").type(JsonFieldType.NUMBER).description("요금")
                           ))).
            extract();
    }
}
