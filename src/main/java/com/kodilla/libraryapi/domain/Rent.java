package com.kodilla.libraryapi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="RENTS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rent {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ID")
    private long id;

    @NotNull
    @Column(name="USER_ID")
    private long userId;

    @NotNull
    @Column(name="BOOK_INSTANCE_ID")
    private long bookInstanceId;

    @NotNull
    @Column(name="RENT_DATE")
    private LocalDate rentDate;

    @NotNull
    @Column(name="RETURN_DEADLINE")
    private LocalDate returnDeadline;

    @Column(name="FINE_ID")
    private long fineId;

    @NotNull
    @Column(name="IS_RETURNED")
    private boolean isReturned;



}
