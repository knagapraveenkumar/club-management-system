package com.club.clubmanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.club.clubmanagement.model.Club;
import com.club.clubmanagement.repository.ClubRepository;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Club addClub(Club club) {
        return clubRepository.save(club);
    }

    public void deleteClub(Long id) {
        clubRepository.deleteById(id);
    }

    // ✅ Add this method
    public Club getClubById(Long id) {
        return clubRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Club not found with id: " + id));
    }
}
