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
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name="BOOK_COPY_ID")
    private BookCopy bookCopy;

    @NotNull
    @Column(name="RENT_DATE")
    private LocalDate rentDate;

    @NotNull
    @Column(name="RETURN_DEADLINE")
    private LocalDate returnDeadline;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="FINE_ID")
    private Fine fine;

    @NotNull
    @Column(name="IS_RETURNED")
    private boolean isReturned;



}