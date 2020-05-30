package com.reactive;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Service
@Transactional(transactionManager = "reactiveTransactionManager")
public class ReactiveService {

    private final ReactiveRepository reactiveRepository;

    public ReactiveService(ReactiveRepository reactiveRepository) {this.reactiveRepository = reactiveRepository;}

    public Mono<ReactiveModel> getSaved() {
        return reactiveRepository.save(new ReactiveModel());
    }

    @PostConstruct
    void init() {
        reactiveRepository.save(new ReactiveModel())
                .subscribe(reactiveModel -> LoggerFactory.getLogger(this.getClass())
                        .info("Saved reactive model with ID {}", reactiveModel.getId()));
    }
}
