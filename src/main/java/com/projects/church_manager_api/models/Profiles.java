package com.projects.church_manager_api.models;

import javax.persistence.*;


@Entity
@Table(name = "profiles")
public class Profiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String profileCreatedDate;
//
    @Column
    private boolean married;

    @Column
    private String BirthDate;

    @Column
    private String AnniversaryDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getAnniversaryDate() {
        return AnniversaryDate;
    }

    public void setAnniversaryDate(String anniversaryDate) {
        AnniversaryDate = anniversaryDate;
    }

    public String getProfileCreatedDate() {
        return profileCreatedDate;
    }

    public void setProfileCreatedDate(String profileCreatedDate) {
        this.profileCreatedDate = profileCreatedDate;
    }
}
