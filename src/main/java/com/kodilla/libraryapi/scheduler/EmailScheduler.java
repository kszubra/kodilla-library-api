package com.kodilla.libraryapi.scheduler;

import com.kodilla.libraryapi.domain.Mail;
import com.kodilla.libraryapi.domain.Rent;
import com.kodilla.libraryapi.service.RentService;
import com.kodilla.libraryapi.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class EmailScheduler {
    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    private RentService rentService;

    private static final String DEADLINE_REMIND_SUBJECT = "Book return deadline";
    private static final String DEADLINE_REMIND_MESSAGE = "You receive this email because one of your rented books is due tomorrow. Please return it before deadline";


    @Scheduled(cron = "0 1 0 * * *")
    public void remindAboutDeadline() {
        rentService.getAllRents().stream()
                .filter(e -> hasDeadlineTomorrow(e))
                .filter(e -> !e.isReturned() )
                .forEach(this::sendRemindingEmail);

    }

    private boolean hasDeadlineTomorrow(final Rent rent) {
        return rent.getReturnDeadline().minusDays(1).isEqual( LocalDate.now() );
    }

    private void sendRemindingEmail(final Rent rent) {
        simpleEmailService.send( Mail.builder()
                .mailTo( rent.getUser().getEmailAddress() )
                .subject( DEADLINE_REMIND_SUBJECT )
                .message( DEADLINE_REMIND_MESSAGE )
                .build()
        );

    }


}
