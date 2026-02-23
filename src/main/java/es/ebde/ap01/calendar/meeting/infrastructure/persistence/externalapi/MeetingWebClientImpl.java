package es.ebde.ap01.calendar.meeting.infrastructure.persistence.externalapi;

import es.ebde.ap01.calendar.meeting.application.port.out.MeetingWebClient;
import es.ebde.ap01.calendar.meeting.infrastructure.config.WebClientConfig;
import es.ebde.ap01.calendar.meeting.infrastructure.persistence.externalapi.dto.MeetingExternalDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MeetingWebClientImpl implements MeetingWebClient {

	private final WebClient webClient;

	@Value("${app.dependencies.api.openf1.meeting-endpoint}")
	private String endpoint;

	@Override
	public List<MeetingExternalDto> getMeetings() {
		try {
			List<MeetingExternalDto> meetings = webClient.get()
					.uri(uriBuilder -> uriBuilder.path(endpoint).build())
					.retrieve()
					.onStatus(HttpStatusCode::isError,
							response -> Mono
								.error(new RuntimeException("Error llamando a la API de F1  : " + response.statusCode())))
					.bodyToFlux(MeetingExternalDto.class)
					.collectList()
					.blockOptional()
					.orElse(Collections.emptyList());

			return meetings;

		}
		catch (Exception e) {
			throw new InternalException("No se pudo conectar con el servicio de calendario", e);
		}
	}

}
