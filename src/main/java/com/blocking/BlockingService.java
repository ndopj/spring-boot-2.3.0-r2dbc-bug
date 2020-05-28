package com.blocking;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BlockingService {

    private final BlockingRepository blockingRepository;

    public BlockingService(BlockingRepository blockingRepository) {this.blockingRepository = blockingRepository;}

    @PostConstruct
    void init() {
        var model = blockingRepository.save(new BlockingModel());
        LoggerFactory.getLogger(this.getClass()).info("Save blocking model with ID {}", model.getId());
    }
}
