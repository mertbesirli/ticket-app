package com.ticket.app;

import com.ticket.app.messaging.TicketNotification;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

// EnableBinding, StreamListener yapıları değişecek, başka formata geçecek!!
@EnableBinding(Sink.class)
public class NotificationDistributionService {
    @StreamListener(Sink.INPUT)
    public void onNotification(TicketNotification ticketNotification){
        // *Emailing section will be added later.
        // **Maybe, added to other queue this events.
        System.out.println("———————————————————————————————————————————");
        System.out.println("Notification taken..");
        System.out.println("Notification -> " + ticketNotification.toString());
    }
}
