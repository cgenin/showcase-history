package com.example.memento.data;

import com.example.memento.events.CreateCountryHistoryEvent;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostUpdate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationEventPublisher;

import java.util.UUID;

/**
 * It's a fucking singleton
 */
@Configurable
public class CountrySpecificListener {

    @Autowired
    private ApplicationEventPublisher publisher;


    @PostLoad
    public void postLoad(Country country) {
        country.setInitialName(country.getName());
    }

    @PostUpdate
    public void postUpdate(Country country) {
        var event = new CreateCountryHistoryEvent(country.getId(), country.getInitialName(), country.getName());
        publisher.publishEvent(event);
    }

}
