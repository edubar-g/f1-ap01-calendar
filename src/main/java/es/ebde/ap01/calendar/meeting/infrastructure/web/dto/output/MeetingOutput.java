package es.ebde.ap01.calendar.meeting.infrastructure.web.dto.output;

import java.time.OffsetDateTime;

public record MeetingOutput(Integer circuitKey,

		String circuitInfoUrl,

		String circuitImage,

		String circuitShortName,

		String circuitType,

		String countryCode,

		String countryFlag,

		Integer countryKey,

		String countryName,

		OffsetDateTime dateEnd,

		OffsetDateTime dateStart,

		String gmtOffset,

		String location,

		Integer meetingKey,

		String meetingName,

		String meetingOfficialName,

		Integer year) {

}
