package com.kodilla.libraryapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="USERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @NotNull
    @Column(name="NAME")
    private String name;

    @NotNull
    @Column(name="SURNAME")
    private String surname;

    @NotNull
    @Column(name="REGISTERED")
    private LocalDate registrationDate;

    @NotNull
    @Column(name="CURRENCY")
    private String prefferedCurrency;

    @NotNull
    @Column(name="IS_ADMIN")
    private boolean hasAdminRights;
}
