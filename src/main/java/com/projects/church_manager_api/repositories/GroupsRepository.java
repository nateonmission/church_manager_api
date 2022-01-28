package com.projects.church_manager_api.repositories;

import com.projects.church_manager_api.models.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PeopleRepository extends JpaRepository<People, Long> {
    Set<People> findByPhoneNumber(String phoneNumber);
    //Optional<People> findById(Long id);
    Optional<People> findByEmailAddress(String emailAddress);
}
