package com.codepilot.jcache.bootjcache.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "USER")
@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User implements Serializable {

    @Id
    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "PROFILE_NAME")
    private String profileName;

    @Column(name = "AGE")
    private int age;

    @Column(name = "CITY")
    private String city;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "COUNTRY")
    private String country;

}
