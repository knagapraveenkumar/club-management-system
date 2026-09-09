/*
 * package com.club.clubmanagement.service;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.club.clubmanagement.model.Event; import
 * com.club.clubmanagement.repository.EventRepository;
 * 
 * @Service public class EventService {
 * 
 * @Autowired private EventRepository eventRepository;
 * 
 * public List<Event> getAllEvents() { return eventRepository.findAll(); }
 * 
 * public List<Event> getEventsByClub(Long clubId) { return
 * eventRepository.findByClubId(clubId); }
 * 
 * public Event addEvent(Event event) { return eventRepository.save(event); }
 * 
 * 
 * public void deleteEvent(Long id) { eventRepository.deleteById(id); }
 * 
 * // ✅ Add this public Event getEventById(Long id) { return
 * eventRepository.findById(id) .orElseThrow(() -> new
 * RuntimeException("Event not found with id: " + id)); } public void
 * updateEvent(Event updatedEvent) { eventRepository.save(updatedEvent); //
 * updates if ID already exists }
 * 
 * }
 * 
 */
package com.club.clubmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.club.clubmanagement.model.Event;
import com.club.clubmanagement.repository.EventRepository;
import com.club.clubmanagement.repository.EventRegistrationRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    // ✅ correct variable name (small letter)
    @Autowired
    private EventRegistrationRepository eventRegistrationRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventsByClub(Long clubId) {
        return eventRepository.findByClubId(clubId);
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    // ✅ Proper Delete Method
    @Transactional
    public void deleteEvent(Long id) {

        // Step 1: Delete all registrations for this event
        eventRegistrationRepository.deleteByEventId(id);

        // Step 2: Delete the event
        eventRepository.deleteById(id);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    public void updateEvent(Event updatedEvent) {
        eventRepository.save(updatedEvent);
    }
}