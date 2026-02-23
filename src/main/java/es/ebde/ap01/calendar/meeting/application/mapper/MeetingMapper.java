package es.ebde.ap01.calendar.meeting.application.mapper;

import es.ebde.ap01.calendar.meeting.domain.entity.Meeting;
import es.ebde.ap01.calendar.meeting.infrastructure.persistence.externalapi.dto.MeetingExternalDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class MeetingMapper {

	public abstract Meeting mapToDomainFromExternalDto(MeetingExternalDto meetingExternalDto);

}
