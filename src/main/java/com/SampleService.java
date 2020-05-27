package com;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SampleService {

    @Autowired private SampleRepository sampleRepository;

    @PostConstruct
    void init() {
        sampleRepository.save(new Model())
                .doOnNext(model -> LoggerFactory.getLogger(this.getClass())
                        .info("Saved model with ID {}", model.getId()));
    }
}
