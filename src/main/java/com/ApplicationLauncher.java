package com;

import com.reactive.ReactiveModel;
import com.reactive.ReactiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class ApplicationLauncher {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationLauncher.class, args);
    }

    @RestController
    @RequestMapping("/")
    public class Root {

        @Autowired private ReactiveService reactiveService;

        @GetMapping
        public Mono<ReactiveModel> getSaved() {
            return reactiveService.getSaved();
        }
    }
}
