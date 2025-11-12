package com.club.clubmanagement.controller;

import com.club.clubmanagement.model.Club;
import com.club.clubmanagement.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clubs")
@CrossOrigin(origins = "*")
public class ClubController {

    @Autowired
    private ClubRepository clubRepository;

    // Get all clubs
    @GetMapping
    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }
}
