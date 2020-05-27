package com.reactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveService {

    @Autowired private ReactiveRepository reactiveRepository;

    public Mono<ReactiveModel> getSaved() {
        return reactiveRepository.save(new ReactiveModel());
    }
}
