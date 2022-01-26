package com.projects.church_manager_api.models;

import javax.persistence.*;

@Entity
@Table(name = "households")
public class Households {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;



}
