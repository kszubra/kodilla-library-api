package com.kodilla.libraryapi.scheduler;

import com.kodilla.libraryapi.domain.Fine;
import com.kodilla.libraryapi.domain.Rent;
import com.kodilla.libraryapi.service.FineService;
import com.kodilla.libraryapi.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentReturnCheckScheduler {
    @Autowired
    private RentService rentService;

    @Autowired
    private FineService fineService;

    @Scheduled(cron = "0 1 0 * * *") //everyday 1 min after midnight?
    public void updateFines() {

        List<Rent> outdatedRents = rentService.getAllRents().stream()
                .filter(e -> isOutdated(e))
                .filter(e -> !e.isReturned() )
                .collect(Collectors.toList());

        for (Rent rent: outdatedRents) {

            if(rent.getFine() == null) {
                Fine fine = new Fine();
                fine.setPaid(false);
                fine.setUser( rent.getUser() );
                fine.setValue(Fine.FINE_PER_DAY);
                fineService.addFine(fine);

                rent.setFine(fine);
                rentService.addRent(rent);

            } else if ( rent.getFine() != null ) {
                rent.getFine().setValue( calculateFine(rent) );

                rentService.addRent(rent);
            }
        }

    }

    private boolean isOutdated(final Rent rent) {
        LocalDate today = LocalDate.now();
        LocalDate deadline = rent.getReturnDeadline();

        return today.isAfter(deadline);
    }

    private double calculateFine(final Rent rent) {
        LocalDate today = LocalDate.now();
        LocalDate deadline = rent.getReturnDeadline();
        long daysOfDelay = Duration.between(deadline, today).toDays();

        return daysOfDelay*Fine.FINE_PER_DAY;
    }
}
