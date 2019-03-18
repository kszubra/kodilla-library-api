package com.kodilla.libraryapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="FINES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fine {

    @Id
    @NotNull
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @NotNull
    @Column(name="VALUE")
    private double value;

    @NotNull
    @Column(name="IS_PAID")
    private boolean isPaid;

}
