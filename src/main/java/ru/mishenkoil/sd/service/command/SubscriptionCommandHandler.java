package ru.mishenkoil.sd.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.mishenkoil.sd.model.command.CreateSubscriptionCommand;
import ru.mishenkoil.sd.model.command.UpdateSubscriptionCommand;
import ru.mishenkoil.sd.model.event.Event;
import ru.mishenkoil.sd.model.event.EventType;
import ru.mishenkoil.sd.repository.event.EventRepositoryDALImpl;

@Service
public class SubscriptionCommandHandler {

    private final EventRepositoryDALImpl eventRepository;

    @Autowired
    public SubscriptionCommandHandler(EventRepositoryDALImpl eventRepository) {
        this.eventRepository = eventRepository;
    }

    public long createSubscription(CreateSubscriptionCommand command) {
        Event event = eventRepository.save(Event.create(EventType.CREATE_SUBSCRIPTION));
        return event.getSubscriptionId();
    }

    public void updateSubscription(UpdateSubscriptionCommand command) {
        if (eventRepository.isNotSubscriptionExists(command.id())) {
            throw new RuntimeException("Subscription does not exist");
        }

        eventRepository.save(Event.create(EventType.UPDATE_SUBSCRIPTION, command.id()));
    }
}
