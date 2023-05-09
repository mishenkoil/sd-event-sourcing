package ru.mishenkoil.sd.repository.event;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ru.mishenkoil.sd.model.event.Event;
import ru.mishenkoil.sd.model.event.EventType;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    Optional<Event> findFirstBySubscriptionIdAndEventTypeOrderByTimestampDesc(long id, EventType eventType);

}
