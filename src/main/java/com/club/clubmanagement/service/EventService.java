package com.club.clubmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club.clubmanagement.model.Event;
import com.club.clubmanagement.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getEventsByClub(Long clubId) {
        return eventRepository.findByClubId(clubId);
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }
 

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    // ✅ Add this
    public Event getEventById(Long id) {
        return eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }
    public void updateEvent(Event updatedEvent) {
        eventRepository.save(updatedEvent); // updates if ID already exists
    }

}
	