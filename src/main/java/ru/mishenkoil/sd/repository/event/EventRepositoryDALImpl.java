package ru.mishenkoil.sd.repository.event;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.mishenkoil.sd.model.event.Event;
import ru.mishenkoil.sd.model.event.EventType;

@Repository
public class EventRepositoryDALImpl implements EventRepositoryDAL {

    private final EventRepository eventRepository;

    @Autowired
    public EventRepositoryDALImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public boolean isNotSubscriptionExists(long subscriptionId) {
        return getSubscriptionEvent(subscriptionId).isEmpty();
    }

    @Override
    public Optional<Event> getSubscriptionEvent(long subscriptionId) {
        return getLatestEventByType(subscriptionId, EventType.CREATE_SUBSCRIPTION);
    }

    @Override
    public Optional<Event> getLatestEventByType(long subscriptionId, EventType eventType) {
        return eventRepository.findFirstBySubscriptionIdAndEventTypeOrderByTimestampDesc(
                subscriptionId,
                eventType
        );
    }
}
