package com.projects.church_manager_api.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "people")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String type;

    @Column
    private String firstName;

    @Column
    private String middleName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private String emailAddress;

    @Column
    private boolean isDeleted = false;

    @ManyToMany(mappedBy = "members")
    @JsonIgnoreProperties({
            "members",
            "spouse",
            "children",
            "parents",
            "groups",
            "staffSupervisor",
            "leaders",
            "memberRecord",
            "profile"
    })
    private Set<Groups> groups;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberRecord_id", referencedColumnName = "id")
    private MemberRecords memberRecord;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_Id", referencedColumnName = "id")
    private Profiles profile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "spouse_Id", referencedColumnName = "id")
    @JsonIgnoreProperties({
            "members",
            "spouse",
            "children",
            "parents",
            "groups",
            "staffSupervisor",
            "leaders",
            "memberRecord",
            "profile"
    })
    private People spouse;

    @ManyToMany
    @JsonIgnoreProperties({
            "members",
            "spouse",
            "children",
            "parents",
            "groups",
            "staffSupervisor",
            "leaders",
            "memberRecord",
            "profile"
    })
    private Set<People> children = new HashSet<>();

    @ManyToMany
    @JsonIgnoreProperties({
            "members",
            "spouse",
            "children",
            "parents",
            "groups",
            "staffSupervisor",
            "leaders",
            "memberRecord",
            "profile"
    })
    private Set<People> parents = new HashSet<>();






    public People() {
    }

    public People(String type, String firstName, String middleName, String lastName, String phoneNumber, String emailAddress) {
        this.type = type;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public MemberRecords getMemberRecord() {
        return memberRecord;
    }

    public void setMemberRecord(MemberRecords memberRecord) {
        this.memberRecord = memberRecord;
    }

    public Profiles getProfile() {
        return profile;
    }

    public void setProfile(Profiles profile) {
        this.profile = profile;
    }

    public Set<Groups> getGroups() {
        return groups;
    }

    public void setGroups(Groups group) {
        this.groups.add(group);
    }


    public People getSpouse() {
        return spouse;
    }

    public void setSpouse(People spouse) {
        this.spouse = spouse;
    }

    public void removeSpouse() {
        this.spouse = null;
    }

    public Set<People> getChildren() {
        return children;
    }

    public void setChild(People child) {
        this.children.add(child);
    }
    public void removeChild(People child) {
        this.children.remove(child);
    }

    public Set<People> getParents() {
        return parents;
    }

    public void setParent(People parent) {
        this.parents.add(parent);
    }
    public void removeParent(People parent) {
        this.parents.remove(parent);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
