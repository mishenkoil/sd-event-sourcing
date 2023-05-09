package ru.mishenkoil.sd.service.query;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.mishenkoil.sd.model.event.Event;
import ru.mishenkoil.sd.model.query.GetSubscriptionInfoQuery;
import ru.mishenkoil.sd.repository.event.EventRepositoryDALImpl;

@Service
public class SubscriptionsQueryHandler {

    private final EventRepositoryDALImpl eventRepository;

    @Autowired
    public SubscriptionsQueryHandler(EventRepositoryDALImpl eventRepository) {
        this.eventRepository = eventRepository;
    }

    public String getSubscriptionInfo(GetSubscriptionInfoQuery query) {
        Event event = eventRepository.getSubscriptionEvent(query.id())
                .orElseThrow(() -> new RuntimeException("Subscription does not exist"));
        Date subscriptionCreationDate = new Date(event.getTimestamp().getTime());
        return String.format("Created at %s", subscriptionCreationDate);
    }
}
