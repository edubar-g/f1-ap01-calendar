package es.ebde.ap01.calendar.meeting.application.port.out;

import es.ebde.ap01.calendar.meeting.infrastructure.persistence.externalapi.dto.MeetingExternalDto;

import java.util.List;

public interface MeetingWebClient {

    List<MeetingExternalDto> getMeetings(Integer year);

}
