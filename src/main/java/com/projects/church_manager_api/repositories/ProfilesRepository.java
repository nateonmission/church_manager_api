package com.projects.church_manager_api.repositories;

import com.projects.church_manager_api.models.MemberRecords;
import com.projects.church_manager_api.models.Profiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesRepository extends JpaRepository<Profiles, Long> {

}
