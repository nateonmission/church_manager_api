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
    @OneToOne(mappedBy = "profile")
    private People person;

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

    public People getPerson() {
        return person;
    }

    public void setPerson(People person) {
        this.person = person;
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
}
