package com.projects.church_manager_api.models;

import javax.persistence.*;

@Entity
@Table(name = "memberRecords")
public class MemberRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    @OneToOne(mappedBy = "memberRecord")
    private People person;

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

}
