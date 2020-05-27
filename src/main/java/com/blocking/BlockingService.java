package com.blocking;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BlockingService {

    @Autowired
    private BlockingRepository blockingRepository;

    @PostConstruct
    void init() {
        var model = blockingRepository.save(new BlockingModel());
        LoggerFactory.getLogger(this.getClass()).info("Save blocking model with ID {}", model.getId());
    }
}
