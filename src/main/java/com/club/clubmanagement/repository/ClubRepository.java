package com.club.clubmanagement.repository;

import com.club.clubmanagement.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
}
