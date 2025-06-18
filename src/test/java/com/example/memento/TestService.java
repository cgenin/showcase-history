package com.example.memento;

import com.example.memento.data.Country;
import com.example.memento.data.CountryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public void save(Integer id1, Integer id2) {
        Country country2 = countryRepository.findById(id2).orElseThrow();
        Country country = countryRepository.findById(id1).orElseThrow();
        country.setName("1-value which must not store");
        countryRepository.save(country);
        country.setName("1-value to add in history");
        countryRepository.save(country);
        country2.setName("2-value to add in history");
        countryRepository.save(country2);
        // throw new IllegalStateException("dsqdqs");
        System.out.println("before return");
    }
}
