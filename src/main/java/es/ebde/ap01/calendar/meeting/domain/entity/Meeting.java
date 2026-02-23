package es.ebde.ap01.calendar.meeting.domain.entity;

import lombok.*;

import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Meeting {

	private Integer meetingKey;

	private String meetingName;

	private String meetingOfficialName;

	private Integer circuitKey;

	private String circuitInfoUrl;

	private String circuitImage;

	private String circuitShortName;

	private String circuitType;

	private String countryCode;

	private String countryFlag;

	private Integer countryKey;

	private String countryName;

	private OffsetDateTime dateStart;

	private OffsetDateTime dateEnd;

	private String gmtOffset;

	private String location;

	private Integer year;

}