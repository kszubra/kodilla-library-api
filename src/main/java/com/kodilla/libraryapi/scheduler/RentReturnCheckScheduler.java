package com.kodilla.libraryapi.scheduler;

import com.kodilla.libraryapi.domain.Fine;
import com.kodilla.libraryapi.domain.Rent;
import com.kodilla.libraryapi.service.FineService;
import com.kodilla.libraryapi.service.RentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RentReturnCheckScheduler {
    private final RentService rentService;
    private final FineService fineService;

    @Scheduled(cron = "0 1 0 * * *") //everyday 1 min after midnight
    public void updateFines() {

        List<Rent> outdatedRents = rentService.getUnpaidAndOutdated();

        for (Rent rent: outdatedRents) {

            if(rent.getFine() == null) {
                Fine fine = new Fine();
                fine.setPaid(false);
                fine.setUser( rent.getUser() );
                fine.setValue(Fine.FINE_PER_DAY.doubleValue());
                fineService.addFine(fine);

                rent.setFine(fine);

            } else {
                rent.getFine().setValue( calculateFine(rent) );
            }
            rentService.addRent(rent);
        }

    }

    private static double calculateFine(final Rent rent) {
        long daysOfDelay = Duration.between( rent.getReturnDeadline(), LocalDate.now() ).toDays();
        return Fine.FINE_PER_DAY.multiply(BigDecimal.valueOf(daysOfDelay)).setScale(2, BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }
}
