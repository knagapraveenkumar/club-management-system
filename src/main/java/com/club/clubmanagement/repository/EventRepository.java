package com.club.clubmanagement.repository;

import com.club.clubmanagement.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByClubId(Long clubId);
}
