package es.ebde.ap01.calendar.meeting.infrastructure.persistence.externalapi;

import es.ebde.ap01.calendar.meeting.application.port.out.MeetingWebClient;
import es.ebde.ap01.calendar.meeting.infrastructure.persistence.externalapi.dto.MeetingExternalDto;
import es.ebde.ap01.calendar.shared.exception.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MeetingWebClientImpl implements MeetingWebClient {

    private static final Logger logger = LoggerFactory.getLogger(MeetingWebClientImpl.class);

    private final WebClient webClient;

    @Value("${app.dependencies.api.openf1.meeting-endpoint}")
    private String endpoint;

    @Override
    public List<MeetingExternalDto> getMeetings(Integer year) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(endpoint)
                            .queryParam("year", year)
                            .build())
                    .retrieve()
                    .bodyToFlux(MeetingExternalDto.class)
                    .collectList()
                    .blockOptional()
                    .orElse(Collections.emptyList());

        } catch (WebClientResponseException e) {
            logger.error("Error HTTP {} llamando a la API externa: {}", e.getStatusCode(), e.getMessage());
            handleWebClientException(e);

        } catch (Exception e) {
            logger.error("Error de conexión o inesperado llamando a la API externa", e);
            throw new InternalServerErrorEbdeException("CALENDAR-EXT-0005");
        }

        return Collections.emptyList();
    }

    private void handleWebClientException(WebClientResponseException e) {
        HttpStatus status = HttpStatus.valueOf(e.getStatusCode().value());

        switch (status) {
            case BAD_REQUEST:
                throw new BadRequestEbdeException("CALENDAR-EXT-0001");
            case UNAUTHORIZED:
                throw new UnauthorizedEbdeException("CALENDAR-EXT-0002");
            case FORBIDDEN:
                throw new ForbiddenEbdeException("CALENDAR-EXT-0003");
            case NOT_FOUND:
                throw new NotFoundEbdeException("CALENDAR-T-0002");
        }
    }

}
