package com.example.memento;

import com.example.memento.data.Country;
import com.example.memento.data.CountryHistoryRepository;
import com.example.memento.data.CountryRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
public class HistoryTest {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CountryHistoryRepository countryHistoryRepository;

    @Autowired
    private TestService testService;

    private Integer id1;
    private Integer id2;

    @BeforeEach
    public void init() {
        countryHistoryRepository.deleteAll();
        countryRepository.deleteAll();
        Country entity = new Country();
        entity.setName("value initial for 1");
       var c =  countryRepository.save(entity);
       id1 = c.getId();

        Country entity2 = new Country();
        entity2.setName("value initial for 2");
        c = countryRepository.save(entity2);
        id2 = c.getId();
    }

    @Test
    public void testWithoutTransaction() {

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

        var history = Lists.newArrayList( countryHistoryRepository.findAll());
        assertThat(history).hasSize(3); // There is no transaction. So, it's each time that we save a country
    }


    @Test
    public void testWithTransaction() {

        testService.save(id1, id2);

        var history = Lists.newArrayList( countryHistoryRepository.findAll());
        assertThat(history).hasSize(2); // There is a transaction. So, only 2
    }
}
