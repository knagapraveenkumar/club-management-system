/*
 * package com.club.clubmanagement.service;
 * 
 * import com.club.clubmanagement.model.Event; import
 * com.club.clubmanagement.model.User; import
 * com.club.clubmanagement.repository.EventRepository; import
 * com.club.clubmanagement.repository.UserRepository;
 * 
 * import jakarta.transaction.Transactional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import java.util.ArrayList; import java.util.Optional;
 * 
 * @Service public class UserService {
 * 
 * @Autowired private UserRepository userRepository;
 * 
 * @Autowired private EventRepository eventRepository;
 * 
 * @Transactional public void registerForEvent(Long userId, Long eventId) {
 * Optional<User> optionalUser = userRepository.findById(userId);
 * Optional<Event> optionalEvent = eventRepository.findById(eventId);
 * 
 * if (optionalUser.isPresent() && optionalEvent.isPresent()) { User user =
 * optionalUser.get(); Event event = optionalEvent.get();
 * 
 * if (user.getRegisteredEvents() == null) { user.setRegisteredEvents(new
 * ArrayList<>()); }
 * 
 * user.getRegisteredEvents().add(event); userRepository.save(user); } else {
 * throw new RuntimeException("User or Event not found."); } }
 * 
 * 
 * // Add any other methods like getUserById() if needed }
 */
package com.club.clubmanagement.service;

import com.club.clubmanagement.model.Event;
import com.club.clubmanagement.model.User;
import com.club.clubmanagement.model.EventRegistration;
import com.club.clubmanagement.repository.EventRepository;
import com.club.clubmanagement.repository.UserRepository;
import com.club.clubmanagement.repository.EventRegistrationRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;


    // ✅ Register user for event
    @Transactional
    public void registerForEvent(Long userId, Long eventId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Prevent duplicate registration
        boolean alreadyRegistered = eventRegistrationRepository
                .existsByUserIdAndEventId(userId, eventId);

        if (alreadyRegistered) {
            throw new RuntimeException("User already registered for this event.");
        }

        EventRegistration registration = new EventRegistration();
        registration.setUser(user);
        registration.setEvent(event);
        registration.setRegisteredAt(LocalDateTime.now());

        eventRegistrationRepository.save(registration);
    }


    // ✅ Get user by ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    // ✅ Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}