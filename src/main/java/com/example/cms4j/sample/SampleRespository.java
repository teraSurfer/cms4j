package com.example.cms4j.sample;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRespository extends ReactiveCrudRepository<SampleArticleEntity, Integer> {
}
