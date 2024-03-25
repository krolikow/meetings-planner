package com.github.krolikow.meetingsplanner.meetings;

import com.github.krolikow.meetingsplanner.common.exceptions.MeetingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return meetingRepository.save(meeting);
    }

    @Override
    public void deleteById(int id) {
        meetingRepository.deleteById(id);
    }
}