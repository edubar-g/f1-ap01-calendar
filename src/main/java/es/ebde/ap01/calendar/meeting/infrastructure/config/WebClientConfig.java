package es.ebde.ap01.calendar.meeting.infrastructure.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@Getter
public class WebClientConfig {

	@Value("${app.dependencies.api.openf1.base-url}")
	private String BASE_URL;

	@Bean
	public WebClient webClient() {
		return WebClient.builder()
			.baseUrl(BASE_URL)
			.codecs(configure -> configure.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
			.build();
	}

}
