package com.github.krolikow.meetingsplanner.meetings;

import com.github.krolikow.meetingsplanner.common.exceptions.InvalidMeetingDateException;
import com.github.krolikow.meetingsplanner.common.exceptions.MeetingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @Override
    public List<Meeting> findAll() {
        return meetingRepository.findAll();
    }

    @Override
    public Meeting findById(int id) {
        Optional<Meeting> result = meetingRepository.findById(id);
        Meeting meeting;
        if (result.isPresent()) {
            meeting = result.get();
        } else {
            throw new MeetingNotFoundException(id);
        }
        return meeting;
    }

    @Override
    public Meeting save(Meeting meeting) {
        if (meeting.getStartDate().isAfter(meeting.getEndDate())) {
            throw new InvalidMeetingDateException("Start date cannot be after end date.");
        }
        if (meeting.getStartDate().isEqual(meeting.getEndDate())) {
            throw new InvalidMeetingDateException("Start date cannot be equal end date.");
        }
        if (meeting.getStartDate().isBefore(LocalDateTime.now())) {
            throw new InvalidMeetingDateException("Start date cannot be in the past.");
        }
        return meetingRepository.save(meeting);
    }

    @Override
    public void deleteById(int id) {
        Meeting meeting = findById(id);
        if (meeting == null) {
            throw new MeetingNotFoundException(id);
        }
        meetingRepository.deleteById(id);
    }
}