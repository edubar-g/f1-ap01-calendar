package es.ebde.ap01.calendar.meeting.unit_tests;

import es.ebde.ap01.calendar.meeting.infrastructure.persistence.externalapi.MeetingWebClientImpl;
import es.ebde.ap01.calendar.meeting.infrastructure.persistence.externalapi.dto.MeetingExternalDto;
import mockwebserver3.MockResponse;
import mockwebserver3.MockWebServer;
import org.apache.logging.log4j.util.InternalException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MeetingWebClientImplTest {

    @Autowired
    private MeetingWebClientImpl meetingWebClient;

    private static MockWebServer mockBackEnd;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.close();
    }

    @DynamicPropertySource
    static void registerProperties(DynamicPropertyRegistry registry) {
        registry.add("app.dependencies.api.openf1.base-url", () -> "http://localhost:" + mockBackEnd.getPort());

        registry.add("app.dependencies.api.openf1.meeting-endpoint", () -> "/v1/meetings");
    }

    @Test
    void getMeetings_ReturnsListSuccessfully() {
        mockBackEnd.enqueue(new MockResponse.Builder().code(200)
                .body("[{\"id\": 1, \"name\": \"GP de España\"}]")
                .addHeader("Content-Type", "application/json")
                .build());

        List<MeetingExternalDto> result = meetingWebClient.getMeetings();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void getMeetings_ApiReturnsError_ThrowsRuntimeException() {
        mockBackEnd.enqueue(new MockResponse.Builder().code(500).body("Internal Server Error").build());

        InternalException exception = assertThrows(InternalException.class, () -> {
            meetingWebClient.getMeetings();
        });

        assertEquals("No se pudo conectar con el servicio de calendario", exception.getMessage());

        assertNotNull(exception.getCause());
        assertTrue(exception.getCause().getMessage().contains("Error llamando a la API de F1"));
    }

    @Test
    void getMeetings_ConnectionFails_ThrowsInternalException() throws IOException {
        mockBackEnd.close();

        InternalException exception = assertThrows(InternalException.class, () -> {
            meetingWebClient.getMeetings();
        });

        assertEquals("No se pudo conectar con el servicio de calendario", exception.getMessage());

        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

}