package com.club.clubmanagement.controller;

import com.club.clubmanagement.model.Club;
import com.club.clubmanagement.model.Event;
import com.club.clubmanagement.service.ClubService;
import com.club.clubmanagement.service.EventService;
import com.club.clubmanagement.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    // ✅ View all clubs
    @GetMapping("/clubs")
    public String viewClubs(Model model) {
        List<Club> clubs = clubService.getAllClubs();
        model.addAttribute("clubs", clubs);
        return "user/clubs"; // View: templates/user/clubs.html
    }

    // ✅ View events of a specific club
    @GetMapping("/clubs/{clubId}/events")
    public String viewClubEvents(@PathVariable Long clubId, Model model) {
        try {
            Club club = clubService.getClubById(clubId);
            List<Event> events = eventService.getEventsByClub(clubId);
            model.addAttribute("club", club);
            model.addAttribute("events", events);
            return "user/club_events"; // View: templates/user/club_events.html
        } catch (Exception e) {
            model.addAttribute("error", "Club or events not found");
            return "error"; // Create templates/error.html if needed
        }
    }

    // ✅ Register for event
    @PostMapping("/events/register")
    public String registerForEvent(@RequestParam Long eventId, HttpSession session, Model model) {
        Long userId = (Long) session.getAttribute("loggedInUserId");

        if (userId == null) {
            model.addAttribute("error", "You must be logged in to register");
            return "login_user"; // Redirect to login if not logged in
        }

        try {
            userService.registerForEvent(userId, eventId);
        } catch (Exception e) {
            model.addAttribute("error", "Event registration failed: " + e.getMessage());
            return "error";
        }

        return "redirect:/user/clubs";
    }
}
