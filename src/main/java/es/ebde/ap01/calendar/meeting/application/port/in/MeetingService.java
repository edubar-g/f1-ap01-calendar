package es.ebde.ap01.calendar.meeting.application.port.in;

import es.ebde.ap01.calendar.meeting.domain.entity.Meeting;

import java.util.List;

public interface MeetingService {

	List<Meeting> getMeetings();

}
