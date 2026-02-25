package es.ebde.ap01.calendar.meeting.infrastructure.web.controller;

import es.ebde.ap01.calendar.meeting.application.mapper.MeetingMapper;
import es.ebde.ap01.calendar.meeting.application.port.in.MeetingService;
import es.ebde.ap01.calendar.meeting.infrastructure.web.dto.output.MeetingOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    private final MeetingMapper meetingMapper;

    @GetMapping
    public ResponseEntity<List<MeetingOutput>> getCurrentMeetings() {
        return ResponseEntity
                .ok(meetingService.getCurrentMeetings().stream().map(meetingMapper::mapToOutputFromDomain).toList());
    }

    //TODO: SEARCH ANOTHER API TO ACCESS HISTORICAL RACES
    @GetMapping("/historical")
    public ResponseEntity<List<MeetingOutput>> getHistoricalMeetings(@RequestParam Integer year) {
        return ResponseEntity
                .ok(meetingService.getHistoricalMeetings(year).stream().map(meetingMapper::mapToOutputFromDomain).toList());
    }

    @GetMapping("/current")
    public ResponseEntity<MeetingOutput> getCurrentMeeting() {
        return ResponseEntity.ok(meetingMapper.mapToOutputFromDomain(meetingService.getCurrentMeeting()));
    }

}
