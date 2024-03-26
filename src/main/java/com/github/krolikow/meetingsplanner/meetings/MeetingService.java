package com.github.krolikow.meetingsplanner.meetings;

import java.util.List;

public interface MeetingService {
    List<Meeting> findAll();
    Meeting findById(int id);
    Meeting save(Meeting meeting);
    void deleteById(int id);
}