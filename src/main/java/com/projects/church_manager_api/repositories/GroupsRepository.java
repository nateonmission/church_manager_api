package com.projects.church_manager_api.repositories;

import com.projects.church_manager_api.models.Groups;
import com.projects.church_manager_api.models.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface GroupsRepository extends JpaRepository<Groups, Long> {
    Optional<Groups> findByName(String name);
}
