package com;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends ReactiveCrudRepository<Model, Long> {
}
