package com.projects.church_manager_api.services;
import com.projects.church_manager_api.controllers.ChurchController;
import com.projects.church_manager_api.exceptions.InfoAlreadyExists;
import com.projects.church_manager_api.exceptions.InfoNotFound;
import com.projects.church_manager_api.models.Groups;
import com.projects.church_manager_api.models.MemberRecords;
import com.projects.church_manager_api.models.People;
import com.projects.church_manager_api.models.Profiles;
import com.projects.church_manager_api.repositories.GroupsRepository;
import com.projects.church_manager_api.repositories.MemberRecordsRepository;
import com.projects.church_manager_api.repositories.PeopleRepository;
import com.projects.church_manager_api.repositories.ProfilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

@Service
public class ChurchManagerServices {
    private static final Logger LOGGER = Logger.getLogger(ChurchController.class.getName());

    // JavaBeans POJO - One Instance for whole class
    private PeopleRepository peopleRepository;
    @Autowired
    public void setPeopleRepository(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    private MemberRecordsRepository memberRecordsRepository;
    @Autowired
    public void setMemberRecordsRepository(MemberRecordsRepository memberRecordsRepository) {
        this.memberRecordsRepository = memberRecordsRepository;
    }

    private ProfilesRepository profilesRepository;
    @Autowired
    public void setProfilesRepository(ProfilesRepository profilesRepository) {
        this.profilesRepository = profilesRepository;
    }

    private GroupsRepository groupsRepository;
    @Autowired
    public void setGroupsRepository(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }


    public String isAlive() {
        LOGGER.info("calling isAlive method ==>");
        return "<h1>I'm Alive</h1>";
    }

    //////////// PEOPLE ////////////
    // POST create a person api/people
    public People createPerson(People personObject) {
        LOGGER.info("service calling createPerson ==>");

        Optional<People>  person = peopleRepository.findByEmailAddressAndFirstName(personObject.getEmailAddress(), personObject.getFirstName());
        if (person.isPresent() ) {
            if(person.get().isDeleted()){
                person.get().setDeleted(false);
                return person.get();
            } else {

                throw new InfoAlreadyExists("Person with email "
                        + personObject.getEmailAddress() + " already exists");
            }
        } else {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            MemberRecords newRecordObj = new MemberRecords();
            newRecordObj.setRecordCreatedDate(dtf.format(now));
            MemberRecords newMemberRecord = memberRecordsRepository.save(newRecordObj);
            personObject.setMemberRecord(newMemberRecord);

            Profiles newProfileObj = new Profiles();
            newProfileObj.setProfileCreatedDate(dtf.format(now));
            Profiles newProfile = profilesRepository.save(newProfileObj);
            personObject.setProfile(newProfile);

            return peopleRepository.save(personObject);
        }
    }


    // GET all data for all people api/people
    public List<People> getPeople() {
        LOGGER.info("service calling getPeople ==>");
        List<People> people = peopleRepository.findAll();
        if (people.isEmpty()) {
            throw new InfoNotFound("no people found");
        } else {
            LOGGER.info("Sorting people records");
            List<People> newPeople = new ArrayList<>();
            for(People person : people){
                if(!person.isDeleted()) {
                    try {
                        newPeople.add(person);
                        LOGGER.info("person added");
                    } catch (Exception e) {
                        throw new InfoNotFound("Null!!!");
                    }
                }
            }
            LOGGER.info("returning...");
            return newPeople;
        }
    }

    // GET a list of people by Phone Number
    public People getPersonById(Long id) {
        LOGGER.info("service calling getPersonById ==>");
        Optional<People> people = peopleRepository.findById(id);

        if(!people.isPresent()) {
            throw new InfoNotFound("Cannot retrieve people with id "
                    + id + ". It does not exist");
        } else {
            System.out.println(people.get().getFirstName());
            return people.get();
        }
    }
    // GET a list of people by Phone Number
    public Set<People> getPersonByEmailAddress(String emailAddress) {
        LOGGER.info("service calling getPersonByPhoneNumber ==>");
        Set<People> people = peopleRepository.findByEmailAddress(emailAddress);
        if(people.isEmpty()) {
            throw new InfoNotFound("Cannot retrieve people with Phone number "
                    + emailAddress + ". It does not exist");
        } else {
            return people;
        }
    }

    // GET a list of people by Phone Number
    public Set<People> getPersonByPhoneNumber(String phoneNumber) {
        LOGGER.info("service calling getPersonByPhoneNumber ==>");
        Set<People> people = peopleRepository.findByPhoneNumber(phoneNumber);
        if(people.isEmpty()) {
            throw new InfoNotFound("Cannot retrieve people with Phone number "
                    + phoneNumber + ". It does not exist");
        } else {
            return people;
        }
    }

    // PUT update a person api/person/{book_ID}
    public People updatePersonById(Long id, People bookObject) {
        LOGGER.info("service calling updatePersonById method ==> ");
        Optional<People> person = peopleRepository.findById(id);
        if (person.isEmpty()) {
            throw new InfoNotFound("person with id " + id + " not found");
        } else {
            person.get().setFirstName(bookObject.getFirstName());
            person.get().setMiddleName(bookObject.getMiddleName());
            person.get().setLastName(bookObject.getLastName());
            person.get().setPhoneNumber(bookObject.getPhoneNumber());
            return peopleRepository.save(person.get());
        }
    }

    // PUT Add a spouse to a person api/groups/{groupsId}/addStaffSup/{personId}
    public People addSpouseById(Long id, Long spouseId) {
        LOGGER.info("service calling addSpouseById method ==> ");
        Optional<People> person = peopleRepository.findById(id);
        if (person.isEmpty()) {
            throw new InfoNotFound("person with id " + id + " not found");
        } else {
            Optional<People> spouse = peopleRepository.findById(spouseId);
            if (spouse.isEmpty()) {
                throw new InfoNotFound("person with id " + spouseId + " not found");
            } else {
                person.get().setSpouse(spouse.get());
                return peopleRepository.save(person.get());
            }
        }
    }

    // PUT Add a child to a person api/groups/{groupsId}/addStaffSup/{personId}
    public People addChildById(Long id, Long childId) {
        LOGGER.info("service calling addSpouseById method ==> ");
        Optional<People> person = peopleRepository.findById(id);
        if (person.isEmpty()) {
            throw new InfoNotFound("person with id " + id + " not found");
        } else {
            Optional<People> child = peopleRepository.findById(childId);
            if (child.isEmpty()) {
                throw new InfoNotFound("person with id " + childId + " not found");
            } else {
                person.get().setChild(child.get());
                return peopleRepository.save(person.get());
            }
        }
    }

    // PUT Add a parent to a person api/groups/{groupsId}/addStaffSup/{personId}
    public People addParentById(Long id, Long parentId) {
        LOGGER.info("service calling addParentById method ==> ");
        Optional<People> person = peopleRepository.findById(id);
        if (person.isEmpty()) {
            throw new InfoNotFound("person with id " + id + " not found");
        } else {
            Optional<People> parent = peopleRepository.findById(parentId);
            if (parent.isEmpty()) {
                throw new InfoNotFound("person with id " + parentId + " not found");
            } else {
                person.get().setParent(parent.get());
                return peopleRepository.save(person.get());
            }
        }
    }

    // PUT remove a spouse to a person api/groups/{groupsId}/addStaffSup/{personId}
    public People removeSpouse(Long id) {
        LOGGER.info("service calling removeSpouse method ==> ");
        Optional<People> person = peopleRepository.findById(id);
        if (person.isEmpty()) {
            throw new InfoNotFound("person with id " + id + " not found");
        } else {
            person.get().removeSpouse();
            return peopleRepository.save(person.get());
        }
    }

    // PUT Add a child to a person api/groups/{groupsId}/addStaffSup/{personId}
    public People removeChildById(Long id, Long childId) {
        LOGGER.info("service calling removeChildById method ==> ");
        Optional<People> person = peopleRepository.findById(id);
        if (person.isEmpty()) {
            throw new InfoNotFound("person with id " + id + " not found");
        } else {
            Optional<People> child = peopleRepository.findById(childId);
            if (child.isEmpty()) {
                throw new InfoNotFound("person with id " + childId + " not found");
            } else {
                person.get().removeChild(child.get());
                return peopleRepository.save(person.get());
            }
        }
    }

    // PUT Add a parent to a person api/groups/{groupsId}/addStaffSup/{personId}
    public People removeParentById(Long id, Long parentId) {
        LOGGER.info("service calling removeParentById method ==> ");
        Optional<People> person = peopleRepository.findById(id);
        if (person.isEmpty()) {
            throw new InfoNotFound("person with id " + id + " not found");
        } else {
            Optional<People> parent = peopleRepository.findById(parentId);
            if (parent.isEmpty()) {
                throw new InfoNotFound("person with id " + parentId + " not found");
            } else {
                person.get().removeParent(parent.get());
                return peopleRepository.save(person.get());
            }
        }
    }



    // DELETE a person api/people/{id}
    public String deletePersonById(Long id) {
        LOGGER.info("service calling deletePersonById method ==>");
        Optional<People> person = peopleRepository.findById(id);
        if (person.isPresent()) {
            person.get().setDeleted(true);
            peopleRepository.save(person.get());
            return (person.get().getFirstName()) + " " + (person.get().getLastName()) + " -- REMOVED = "
                    + person.get().isDeleted();
        } else {
            throw new InfoNotFound("person with id: "
                    + id + " does NOT exist");
        }
    }

    //////////// PROFILES ////////////
    public People updateProfileByPersonId(Long personId, Profiles profileObject){

        LOGGER.info("service calling updateProfileByPersonId method ==> ");
        Optional<People> person = peopleRepository.findById(personId);
        if (person.isEmpty()) {
            throw new InfoNotFound("person with id " + personId + " not found");
        } else {
            Profiles profile = person.get().getProfile();

            profile.setMarried(profileObject.isMarried());
            profile.setAnniversaryDate(profileObject.getAnniversaryDate());
            profile.setBirthDate(profileObject.getBirthDate());
            profilesRepository.save(profile);

            return peopleRepository.getById(personId);
        }
    }


    //////////// PROFILES ////////////
    public People updateMemberRecordByPersonId(Long personId, MemberRecords memberRecordsObject){

        LOGGER.info("service calling updateMemberRecordByPersonId method ==> ");
        Optional<People> person = peopleRepository.findById(personId);
        if (person.isEmpty()) {
            throw new InfoNotFound("person with id " + personId + " not found");
        } else {
            MemberRecords memberRecord = person.get().getMemberRecord();

            memberRecord.setJoinDate(memberRecordsObject.getJoinDate());
            memberRecord.setJoinMethod(memberRecordsObject.getJoinMethod());
            memberRecord.setMembershipEndDate(memberRecordsObject.getMembershipEndDate());
            memberRecord.setVolunteer(memberRecordsObject.isVolunteer());
            memberRecord.setVolunteerLeader(memberRecordsObject.isVolunteerLeader());
            memberRecord.setDeacon(memberRecordsObject.isDeacon());
            memberRecord.setComments(memberRecordsObject.getComments());
            memberRecordsRepository.save(memberRecord);

            return peopleRepository.getById(personId);
        }
    }

    //////////// GROUPS ////////////
    // POST create a person api/people
    public Groups createGroup(Groups groupObject) {
        LOGGER.info("service calling createGroup ==>");

        Optional<Groups> group = groupsRepository.findByName(groupObject.getName());
        if (group.isPresent() ) {
            if(group.get().isDeleted()){
                group.get().setDeleted(false);
                return group.get();
            } else {

                throw new InfoAlreadyExists("Person with name "
                        + groupObject.getName() + " already exists");
            }
        } else {

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            groupObject.setGroupCreatedDate(dtf.format(now));

            return groupsRepository.save(groupObject);
        }
    }

    public List<Groups> getAllGroups() {
        LOGGER.info("service calling getAllGroups ==>");
        List<Groups> groupsList = groupsRepository.findAll();
        if (groupsList.isEmpty()) {
            throw new InfoNotFound("no groups found");
        } else {
            LOGGER.info("Sorting group records");
            List<Groups> newGroups = new ArrayList<>();
            for(Groups group : groupsList){
                if(!group.isDeleted()) {
                    try {
                        newGroups.add(group);
                        LOGGER.info("group added");
                    } catch (Exception e) {
                        throw new InfoNotFound("Null!!!");
                    }
                }
            }
            LOGGER.info("returning...");
            return newGroups;
        }
    }

    // GET a single group by id
    public Groups getGroupById(Long id) {
        LOGGER.info("service calling getGroupById ==>");
        Optional<Groups> group = groupsRepository.findById(id);
        if (group.isPresent()) {
            return group.get();
        } else {
            throw new InfoNotFound("Group with ID " + id + "not found");
        }
    }

    // PUT update a book api/person/{book_ID}
    public Groups updateGroupById(Long id, Groups groupObject) {
        LOGGER.info("service calling updateGroupById method ==> ");
        Optional<Groups> group = groupsRepository.findById(id);
        if (group.isEmpty()) {
            throw new InfoNotFound("group with id " + id + " not found");
        } else {

            group.get().setName(groupObject.getName());
            group.get().setType(groupObject.getType());
            group.get().setDescription(groupObject.getDescription());
            group.get().setNotes(groupObject.getNotes());
            return groupsRepository.save(group.get());
        }
    }

    // DELETE a group api/people/{id}
    public String deleteGroupById(Long id) {
        LOGGER.info("service calling deleteGroup method ==>");
        Optional<Groups> group = groupsRepository.findById(id);
        if (group.isPresent()) {
            group.get().setDeleted(true);
            groupsRepository.save(group.get());
            return (group.get().getName()) + " -- REMOVED = "
                    + group.get().isDeleted();
        } else {
            throw new InfoNotFound("group with id: "
                    + id + " does NOT exist");
        }
    }


    // PUT Add a Staff Sup to a group api/groups/{groupsId}/addStaffSup/{personId}
    public Groups addStaffSupToGroupById(Long groupId, Long personId) {
        LOGGER.info("service calling addStaffSupToGroupById method ==> ");
        Optional<Groups> group = groupsRepository.findById(groupId);
        if (group.isEmpty()) {
            throw new InfoNotFound("group with id " + groupId + " not found");
        } else {
            Optional<People> person = peopleRepository.findById(personId);
            if (person.isEmpty()) {
                throw new InfoNotFound("person with id " + personId + " not found");
            } else {
                group.get().setStaffSupervisor(person.get());
                return groupsRepository.save(group.get());
            }
        }
    }

    public Groups removeStaffSupFromGroup(Long groupId) {
        LOGGER.info("service calling removeStaffSupFromGroup method ==> ");
        Optional<Groups> group = groupsRepository.findById(groupId);
        if (group.isEmpty()) {
            throw new InfoNotFound("group with id " + groupId + " not found");
        } else {
            group.get().removeStaffSup();
            return groupsRepository.save(group.get());
        }
    }

    // PUT Add a Member to a group api/groups/{groupsId}/addMember/{personId}
    public Groups addMemberToGroupById(Long groupId, Long personId) {
        LOGGER.info("service calling addMemberToGroupById method ==> ");
        Optional<Groups> group = groupsRepository.findById(groupId);
        if (group.isEmpty()) {
            throw new InfoNotFound("group with id " + groupId + " not found");
        } else {
            Optional<People> person = peopleRepository.findById(personId);
            if (person.isEmpty()) {
                throw new InfoNotFound("person with id " + personId + " not found");
            } else {
                group.get().setMember(person.get());
                return groupsRepository.save(group.get());
            }
        }
    }

    // PUT remove a Member from a group api/groups/{groupsId}/removeMember/{personId}
    public Groups removeMemberFromGroupById(Long groupId, Long personId) {
        LOGGER.info("service calling removeMemberFromGroupById method ==> ");
        Optional<Groups> group = groupsRepository.findById(groupId);
        if (group.isEmpty()) {
            throw new InfoNotFound("group with id " + groupId + " not found");
        } else {
            Optional<People> person = peopleRepository.findById(personId);
            if (person.isEmpty()) {
                throw new InfoNotFound("person with id " + personId + " not found");
            } else {

                group.get().removeMember(person.get());

                return groupsRepository.save(group.get());
            }
        }
    }

    // PUT Add a Member to a group api/groups/{groupsId}/addMember/{personId}
    public Groups addLeaderToGroupById(Long groupId, Long personId) {
        LOGGER.info("service calling addLeaderToGroupById method ==> ");
        Optional<Groups> group = groupsRepository.findById(groupId);
        if (group.isEmpty()) {
            throw new InfoNotFound("group with id " + groupId + " not found");
        } else {
            Optional<People> person = peopleRepository.findById(personId);
            if (person.isEmpty()) {
                throw new InfoNotFound("person with id " + personId + " not found");
            } else {
                group.get().setLeader(person.get());
                return groupsRepository.save(group.get());
            }
        }
    }

    // PUT remove a Member from a group api/groups/{groupsId}/removeMember/{personId}
    public Groups removeLeaderFromGroupById(Long groupId, Long personId) {
        LOGGER.info("service calling removeLeaderFromGroupById method ==> ");
        Optional<Groups> group = groupsRepository.findById(groupId);
        if (group.isEmpty()) {
            throw new InfoNotFound("group with id " + groupId + " not found");
        } else {
            Optional<People> person = peopleRepository.findById(personId);
            if (person.isEmpty()) {
                throw new InfoNotFound("person with id " + personId + " not found");
            } else {

                group.get().removeLeader(person.get());

                return groupsRepository.save(group.get());
            }
        }
    }












}
