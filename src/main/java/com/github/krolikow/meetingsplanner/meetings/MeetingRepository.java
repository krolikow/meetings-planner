package com.github.krolikow.meetingsplanner.meetings;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Integer>{
}