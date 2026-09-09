//package com.club.clubmanagement.repository;
//
//import com.club.clubmanagement.model.EventRegistration;
//import org.springframework.data.jpa.repository.JpaRepository;
//import java.util.List;
//
//public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
//    List<EventRegistration> findByUserId(Long userId);
//}

package com.club.clubmanagement.repository;

import com.club.clubmanagement.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRegistrationRepository
        extends JpaRepository<EventRegistration, Long> {

    // Existing method (keep this)
    List<EventRegistration> findByUserId(Long userId);

    // ✅ ADD THIS METHOD
    boolean existsByUserIdAndEventId(Long userId, Long eventId);
    void deleteByEventId(Long eventId);
}
