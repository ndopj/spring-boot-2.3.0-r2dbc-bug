package com.blocking;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@Transactional(transactionManager = "blockingTransactionManager")
public class BlockingService {

    private final BlockingRepository blockingRepository;

    public BlockingService(BlockingRepository blockingRepository) {this.blockingRepository = blockingRepository;}

    @PostConstruct
    void init() {
        var model = blockingRepository.save(new BlockingModel());
        LoggerFactory.getLogger(this.getClass()).info("Save blocking model with ID {}", model.getId());
    }
}
