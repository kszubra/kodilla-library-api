package com.kodilla.libraryapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                hasAdminRights == user.hasAdminRights &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(registrationDate, user.registrationDate) &&
                Objects.equals(prefferedCurrency, user.prefferedCurrency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, registrationDate, prefferedCurrency, hasAdminRights);
    }
}
