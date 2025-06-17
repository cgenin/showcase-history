package com.example.memento.controller;

import com.example.memento.data.Country;
import com.example.memento.data.CountryRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/entity")
public class EntityController {

    private final CountryRepository countryRepository;

    public EntityController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void save() {
        Country entity = new Country();
        entity.setName("value initial for 1");
        countryRepository.save(entity);

        Country entity2 = new Country();
        entity2.setName("value initial for 2");
        countryRepository.save(entity2);

    }

    @GetMapping
    @Transactional
    public String get() {
        Country country2 = countryRepository.findById(2).orElseThrow();
        Country country = countryRepository.findById(1).orElseThrow();
        country.setName("1-value which must not store");
        countryRepository.save(country);
        country.setName("1-value to add in history");
        countryRepository.save(country);
        country2.setName("2-value to add in history");
        countryRepository.save(country2);
        // throw new IllegalStateException("dsqdqs");
        return "test";
    }
}
