package com.example.memento.events;

import com.example.memento.data.CountryHistory;
import com.example.memento.data.CountryHistoryRepository;
import com.example.memento.data.CountryRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CreateCountryHistoryEventListener {

    private final CountryHistoryRepository countryHistoryRepository;

    public CreateCountryHistoryEventListener(CountryHistoryRepository countryHistoryRepository) {
        this.countryHistoryRepository = countryHistoryRepository;
    }


    @EventListener()
    public void onCreateCountryHistoryEvent(CreateCountryHistoryEvent event) {
        System.out.println(event.id + "/" + event.oldName + "/" + event.NewName);
        var history = new CountryHistory(event.id, event.oldName, event.NewName);
        countryHistoryRepository.save(history);
        // throw new IllegalStateException("error");
    }

}
