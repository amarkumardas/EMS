package com.example.EMS.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Table(name = "organization")
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "orgId")
public class Organization {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY)

    @Column(name="id",updatable = false)
    private Integer orgId;
    @Column(name = "org_name",nullable = false,unique = true)
   // @NotBlank(message = "Organization name is missing")
    private String orgName;
    @JsonIgnore
    @OneToMany(mappedBy = "orgId",orphanRemoval=true,cascade =CascadeType.ALL)
    private List<Employee> employee;
    @JsonIgnore
    @OneToMany(mappedBy = "orgId",orphanRemoval=true,cascade =CascadeType.ALL)
    private List<Asset> orgAsset;
}



















/**@JsonIdentityInfo is a Jackson annotation in Java used to handle circular references between objects during
 * serialization and deserialization of JSON data. In certain cases, such as when there is a bidirectional relationship
 * between objects or when an object contains references to itself, the default serialization and deserialization
 * process may lead to infinite loops or redundant data.*/