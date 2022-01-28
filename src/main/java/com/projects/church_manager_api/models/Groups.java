package com.projects.church_manager_api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.minidev.json.annotate.JsonIgnore;
import org.yaml.snakeyaml.util.ArrayUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String type;

    @Column
    private String description;

    @Column
    private String notes;

    @Column
    private String groupCreatedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @JsonIgnoreProperties("groups")
    private People staffSupervisor;

    @Column
    private boolean deleted = false;

    @ManyToMany
    @JoinTable(
            name = "group_leaders",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties({"groups", "memberRecord"})
    private Set<People> leaders = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "group_memberships",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "person_id", referencedColumnName = "id")
    )
    @JsonIgnoreProperties({"groups", "memberRecord"})
    private Set<People> members = new HashSet<>();



    public Groups() {
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public People getStaffSupervisor() {
        return staffSupervisor;
    }

    public void setStaffSupervisor(People staffSupervisor) {
        this.staffSupervisor = staffSupervisor;
    }

    public void removeStaffSup() {
        this.staffSupervisor = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupCreatedDate() {
        return groupCreatedDate;
    }

    public void setGroupCreatedDate(String groupCreatedDate) {
        this.groupCreatedDate = groupCreatedDate;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<People> getMembers() {
        return members;
    }

    public void setMember(People member) {
        this.members.add(member);
    }

    public void removeMember(People member) {
        this.members.remove(member);
    }

    public Set<People> getLeaders() {
        return leaders;
    }

    public void setLeader(People leader) {
        this.leaders.add(leader);
    }

    public void removeLeader(People leader) {
        this.leaders.remove(leader);
    }


}
