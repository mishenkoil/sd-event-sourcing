package ru.mishenkoil.sd.repository.event;

import java.util.List;
import java.util.Optional;

import ru.mishenkoil.sd.model.event.Event;
import ru.mishenkoil.sd.model.event.EventType;

public interface EventRepositoryDAL {

    Event save(Event event);

    boolean isNotSubscriptionExists(long subscriptionId);

    Optional<Event> getSubscriptionEvent(long subscriptionId);

    Optional<Event> getLatestEventByType(long subscriptionId, EventType eventType);

    List<Event> getAll();
}
