package com.github.krolikow.meetingsplanner.meetings;

import com.github.krolikow.meetingsplanner.common.exceptions.MeetingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/meetings")
public class MeetingController {
    private final MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping("/")
    public List<Meeting> findAll() {
        return meetingService.findAll();
    }

    @GetMapping("/{meetingId}")
    public Meeting getMeeting(@PathVariable int meetingId) {
        Meeting meeting = meetingService.findById(meetingId);
        if (meeting == null) {
            throw new MeetingNotFoundException(meetingId);
        }
        return meeting;
    }

    @PostMapping("/")
    public Meeting addMeeting(@RequestBody Meeting meeting) {
        meeting.setId(0);
        return meetingService.save(meeting);
    }

    @PutMapping("/")
    public Meeting updateMeeting(@RequestBody Meeting meeting) {
        return meetingService.save(meeting);
    }

    @DeleteMapping("/{meetingId}")
    public void deleteMeeting(@PathVariable int meetingId) {
        Meeting meeting = meetingService.findById(meetingId);
        if (meeting == null) {
            throw new MeetingNotFoundException(meetingId);
        }
        meetingService.deleteById(meetingId);
    }
}