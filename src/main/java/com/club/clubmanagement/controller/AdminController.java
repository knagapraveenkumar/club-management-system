package com.club.clubmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.club.clubmanagement.model.Club;
import com.club.clubmanagement.model.Event;
import com.club.clubmanagement.service.ClubService;
import com.club.clubmanagement.service.EventService;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private EventService eventService;

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("clubs", clubService.getAllClubs());
        return "admin/dashboard"; // HTML page
    }
    
    @GetMapping("/events/registrations")
    public String viewEventRegistrations(Model model) {
        List<Event> events = eventService.getAllEvents(); // You already have this in EventService
        model.addAttribute("events", events);
        return "admin/event_registrations"; // We'll create this HTML file
    }
    
    @GetMapping("/event/edit")
    public String showEditForm(@RequestParam Long eventId, Model model) {
        Event event = eventService.getEventById(eventId);
        model.addAttribute("event", event);
        return "admin/edit_event"; //
    }



    @PostMapping("/club/add")
    public String addClub(@ModelAttribute Club club) {
        clubService.addClub(club);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/club/delete/{id}")
    public String deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/event/add")
    public String addEvent(@ModelAttribute Event event, @RequestParam Long clubId) {
        Club club = new Club();
        club.setId(clubId);
        event.setClub(club);
        eventService.addEvent(event);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/event/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/admin/dashboard";
    }
    
    @PostMapping("/event/update")
    public String updateEvent(@ModelAttribute Event event, @RequestParam Long clubId) {
        Club club = clubService.getClubById(clubId);
        event.setClub(club);
        eventService.updateEvent(event);
        return "redirect:/admin/dashboard";
    }

}
