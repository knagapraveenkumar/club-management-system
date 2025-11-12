package com.club.clubmanagement.controller;

import com.club.clubmanagement.model.Event;
import com.club.clubmanagement.model.EventRegistration;
import com.club.clubmanagement.model.User;
import com.club.clubmanagement.repository.EventRegistrationRepository;
import com.club.clubmanagement.repository.EventRepository;
import com.club.clubmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/registrations")
@CrossOrigin(origins = "*")
public class EventRegistrationController {

    @Autowired
    private EventRegistrationRepository registrationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    // Register for an event
    @PostMapping
    public String registerForEvent(@RequestParam Long userId, @RequestParam Long eventId) {
        User user = userRepository.findById(userId).orElse(null);
        Event event = eventRepository.findById(eventId).orElse(null);

        if (user == null || event == null) return "User or Event not found.";

        EventRegistration registration = new EventRegistration();
        registration.setUser(user);
        registration.setEvent(event);
        registration.setRegisteredAt(LocalDateTime.now());

        registrationRepository.save(registration);
        return "Successfully registered for event!";
    }
}
