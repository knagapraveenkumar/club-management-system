package com.club.clubmanagement.service;

import com.club.clubmanagement.model.Event;
import com.club.clubmanagement.model.User;
import com.club.clubmanagement.repository.EventRepository;
import com.club.clubmanagement.repository.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public void registerForEvent(Long userId, Long eventId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Event> optionalEvent = eventRepository.findById(eventId);

        if (optionalUser.isPresent() && optionalEvent.isPresent()) {
            User user = optionalUser.get();
            Event event = optionalEvent.get();

            if (user.getRegisteredEvents() == null) {
                user.setRegisteredEvents(new ArrayList<>());
            }

            user.getRegisteredEvents().add(event);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User or Event not found.");
        }
    }


    // Add any other methods like getUserById() if needed
}
