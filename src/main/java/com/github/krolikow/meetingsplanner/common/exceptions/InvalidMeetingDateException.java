package com.github.krolikow.meetingsplanner.common.exceptions;

public class InvalidMeetingDateException extends RuntimeException {
    public InvalidMeetingDateException(String message) {
        super("Invalid meeting date: " + message);
    }
}