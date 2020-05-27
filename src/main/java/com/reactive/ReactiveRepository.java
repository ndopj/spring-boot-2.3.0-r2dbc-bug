package com.reactive;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactiveRepository extends ReactiveCrudRepository<ReactiveModel, Long> {
}
