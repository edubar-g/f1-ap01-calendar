package es.ebde.ap01.calendar.meeting.infrastructure.web.controller;

import es.ebde.ap01.calendar.meeting.application.port.in.MeetingService;
import es.ebde.ap01.calendar.meeting.domain.entity.Meeting;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class MeetingController {

	private final MeetingService meetingService;

	@GetMapping
	public ResponseEntity<List<Meeting>> getMeetings() {
		return ResponseEntity.ok(meetingService.getMeetings());
	}

}
