package com.github.krolikow.meetingsplanner.common.exceptions;

public class MeetingNotFoundException extends RuntimeException {
    public MeetingNotFoundException(int id) {
        super("Meeting with id " + id + " not found");
    }
}