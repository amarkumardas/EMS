package com.example.EMS.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Roles {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY)

    @Column(name="role_id",updatable = false)
    private Integer roleId;
    @Column(nullable = false,unique = true,name = "name")
    private  String roleName;
}
