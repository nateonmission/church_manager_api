package com.projects.church_manager_api.models;


import javax.persistence.*;

@Entity
@Table(name = "people")
public class People {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

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
    private boolean memberRecordCreated;

    @Column
    private boolean profileCreated;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personId", referencedColumnName = "id")
    private MemberRecord memberRecord;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profileId", referencedColumnName = "id")
    private Profiles profile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "householdId", referencedColumnName = "id")
    private Households household;


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

    public boolean isMemberRecordCreated() {
        return memberRecordCreated;
    }

    public void setMemberRecordCreated(boolean memberRecordCreated) {
        this.memberRecordCreated = memberRecordCreated;
    }

    public boolean isProfileCreated() {
        return profileCreated;
    }

    public void setProfileCreated(boolean profileCreated) {
        this.profileCreated = profileCreated;
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

    public MemberRecord getMemberRecord() {
        return memberRecord;
    }

    public void setMemberRecord(MemberRecord memberRecord) {
        this.memberRecord = memberRecord;
    }

    public Profiles getProfile() {
        return profile;
    }

    public void setProfile(Profiles profile) {
        this.profile = profile;
    }

    public Households getHousehold() {
        return household;
    }

    public void setHousehold(Households household) {
        this.household = household;
    }
}
