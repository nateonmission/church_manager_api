package com.projects.church_manager_api.controllers;

import com.projects.church_manager_api.models.Groups;
import com.projects.church_manager_api.models.MemberRecords;
import com.projects.church_manager_api.models.People;
import com.projects.church_manager_api.models.Profiles;
import com.projects.church_manager_api.services.ChurchManagerServices;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class ChurchController {
    private static final Logger LOGGER = Logger.getLogger(ChurchController.class.getName());

    private ChurchManagerServices churchManagerServices;
    @Autowired
    public void setChurchManagerServices(ChurchManagerServices churchManagerServices) {

        this.churchManagerServices = churchManagerServices;
    }

    @GetMapping("/")
    public String isAlive(){
        LOGGER.info("calling isAlive method from controller");
        return churchManagerServices.isAlive();
    }

    @PostMapping(path = "/people")
    public People createPerson(@RequestBody People personObject){
        LOGGER.info("calling createPerson method from controller");
        return churchManagerServices.createPerson(personObject);
    }

    // GET All People
    @GetMapping("/people")
    public List<People> getPeople() {
        LOGGER.info("calling getPeople method from controller");
        return churchManagerServices.getPeople();
    }

    // GET a single Person by Email Address
    @GetMapping(path = "/people/'{emailAddress}'")
    public Optional getPersonByEmailAddress(@PathVariable String emailAddress) {
        LOGGER.info("controller calling getPersonByEmailAddress from controller");
        return churchManagerServices.getPersonByEmailAddress(emailAddress);
    }

    // GET a list of people by Phone Number
    @GetMapping(path = "/people/tn'{phoneNumber}'")
    public Set<People> getPersonByPhoneNumber(@PathVariable String phoneNumber) {
        LOGGER.info("controller calling getPersonByPhoneNumber from controller");
        return churchManagerServices.getPersonByPhoneNumber(phoneNumber);
    }

    // Update a single Person by Id
    @PutMapping(path = "/people/{id}")
    public People updatePersonById(@PathVariable Long id, @RequestBody People personObject) {
        LOGGER.info("controller calling updatePersonById from controller");
        return churchManagerServices.updatePersonById(id, personObject);
    }

    // Delete a single Person by Id
    @DeleteMapping(path = "/people/{id}")
    public String deletePersonById(@PathVariable Long id) {
        LOGGER.info("controller calling deletePersonById from controller");
        return churchManagerServices.deletePersonById(id);
    }

    //////////// PROFILES ////////////
    // Update a single Person's profile by person Id
    @PutMapping(path = "/people/{id}/profile")
    public People updateProfileByPersonId(@PathVariable Long id, @RequestBody Profiles profileObject) {
        LOGGER.info("controller calling updateProfileByPersonId from controller");
        return churchManagerServices.updateProfileByPersonId(id, profileObject);
    }


    //////////// MEMBER RECORDS ////////////
    // Update a single Person's member record by person Id
    @PutMapping(path = "/people/{id}/member_record")
    public People updateMemberRecordByPersonId(@PathVariable Long id, @RequestBody MemberRecords MemberRecordObject) {
        LOGGER.info("controller calling updateProfileByPersonId from controller");
        return churchManagerServices.updateMemberRecordByPersonId(id, MemberRecordObject);
    }


    ///////// GROUPS /////////
    @PostMapping(path = "/groups")
    public Groups createGroup(@RequestBody Groups groupObject){
        LOGGER.info("calling createGroup method from controller");
        return churchManagerServices.createGroup(groupObject);
    }

    @GetMapping(path = "/groups")
    public List<Groups> getAllGroups(){
        LOGGER.info("calling getAllGroups method from controller");
        return churchManagerServices.getAllGroups();
    }

    @GetMapping(path = "/groups/{id}")
    public Groups getGroupById(@PathVariable Long id){
        LOGGER.info("calling getGroupById method from controller");
        return churchManagerServices.getGroupById(id);
    }

    // Update a single group by Id
    @PutMapping(path = "/groups/{id}")
    public Groups updateGroupById(@PathVariable Long id, @RequestBody Groups groupObject) {
        LOGGER.info("controller calling updateGroupById from controller");
        return churchManagerServices.updateGroupById(id, groupObject);
    }

    // Delete a single Person by Id
    @DeleteMapping(path = "/groups/{id}")
    public String deleteGroupById(@PathVariable Long id) {
        LOGGER.info("controller calling deleteGroupById from controller");
        return churchManagerServices.deleteGroupById(id);
    }

    // Add a Staff Supervisor to a single group by Id
    @PutMapping(path = "/groups/{groupId}/addStaffSup/{personId}")
    public Groups addStaffSupToGroupById(@PathVariable Long groupId, @PathVariable Long personId){
        return churchManagerServices.addStaffSupToGroupById(groupId, personId);
    }

    // Add a Staff Supervisor to a single group by Id
    @PutMapping(path = "/groups/{groupId}/removeStaffSup")
    public Groups removeStaffSupFromGroup(@PathVariable Long groupId){
        return churchManagerServices.removeStaffSupFromGroup(groupId);
    }

    // Add a Staff Supervisor to a single group by Id
    @PutMapping(path = "/groups/{groupId}/addMember/{personId}")
    public Groups addMemberToGroupById(@PathVariable Long groupId, @PathVariable Long personId){
        return churchManagerServices.addMemberToGroupById(groupId, personId);
    }

    // Remove a member from a single group by Id
    @PutMapping(path = "/groups/{groupId}/removeMember/{personId}")
    public Groups removeMemberFromGroupById(@PathVariable Long groupId, @PathVariable Long personId){
        return churchManagerServices.removeMemberFromGroupById(groupId, personId);
    }

    // Add a Staff Supervisor to a single group by Id
    @PutMapping(path = "/groups/{groupId}/addLeader/{personId}")
    public Groups addLeaderToGroupById(@PathVariable Long groupId, @PathVariable Long personId){
        return churchManagerServices.addLeaderToGroupById(groupId, personId);
    }

    // Remove a member from a single group by Id
    @PutMapping(path = "/groups/{groupId}/removeLeader/{personId}")
    public Groups removeLeaderFromGroupById(@PathVariable Long groupId, @PathVariable Long personId){
        return churchManagerServices.removeLeaderFromGroupById(groupId, personId);
    }




}
