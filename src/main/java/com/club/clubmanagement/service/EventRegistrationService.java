package com.club.clubmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club.clubmanagement.model.EventRegistration;
import com.club.clubmanagement.repository.EventRegistrationRepository;

@Service
public class EventRegistrationService {

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    // Get all registrations
    public List<EventRegistration> getAllRegistrations() {
        return eventRegistrationRepository.findAll();
    }
}