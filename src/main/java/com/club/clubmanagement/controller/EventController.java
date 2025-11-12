package com.club.clubmanagement.controller;

import com.club.clubmanagement.model.Event;
import com.club.clubmanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@CrossOrigin(origins = "*")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    // Get all events by clubId
    @GetMapping("/club/{clubId}")
    public List<Event> getEventsByClub(@PathVariable Long clubId) {
        return eventRepository.findByClubId(clubId);
    }
}
