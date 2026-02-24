package es.ebde.ap01.calendar.meeting.application.service;

import es.ebde.ap01.calendar.meeting.application.mapper.MeetingMapper;
import es.ebde.ap01.calendar.meeting.application.port.in.MeetingService;
import es.ebde.ap01.calendar.meeting.application.port.out.MeetingWebClient;
import es.ebde.ap01.calendar.meeting.domain.entity.Meeting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MeetingServiceImpl implements MeetingService {

	private final MeetingMapper meetingMapper;

	private final MeetingWebClient meetingWebClient;

	@Override
	public List<Meeting> getMeetings() {
		return meetingWebClient.getMeetings().stream().map(meetingMapper::mapToDomainFromExternalDto).toList();
	}

	@Override
	public Meeting getCurrentMeeting() {
		return meetingWebClient.getMeetings().stream()
				.map(meetingMapper::mapToDomainFromExternalDto)
				.filter(meeting -> OffsetDateTime.now().isBefore(meeting.getDateEnd()))
				.findFirst().orElseThrow();
	}

}
