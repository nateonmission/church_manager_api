package com.projects.church_manager_api.models;

import javax.persistence.*;

@Entity
@Table(name = "memberRecords")
public class MemberRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String recordCreatedDate;

    @Column
    private String joinDate;

    @Column
    private String joinMethod;

    @Column
    private String membershipEndDate;

    @Column
    private boolean volunteer;

    @Column
    private boolean volunteerLeader;

    @Column
    private boolean deacon;

    @Column
    private String comments;

    public MemberRecords() {
    }

    public String getRecordCreatedDate() {
        return recordCreatedDate;
    }

    public void setRecordCreatedDate(String recordCreatedDate) {
        this.recordCreatedDate = recordCreatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getJoinMethod() {
        return joinMethod;
    }

    public void setJoinMethod(String joinMethod) {
        this.joinMethod = joinMethod;
    }

    public String getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(String membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    public boolean isVolunteer() {
        return volunteer;
    }

    public void setVolunteer(boolean volunteer) {
        this.volunteer = volunteer;
    }

    public boolean isVolunteerLeader() {
        return volunteerLeader;
    }

    public void setVolunteerLeader(boolean volunteerLeader) {
        this.volunteerLeader = volunteerLeader;
    }

    public boolean isDeacon() {
        return deacon;
    }

    public void setDeacon(boolean deacon) {
        this.deacon = deacon;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
