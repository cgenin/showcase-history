package com.example.memento.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryHistoryRepository extends CrudRepository<CountryHistory, Integer> {
}
