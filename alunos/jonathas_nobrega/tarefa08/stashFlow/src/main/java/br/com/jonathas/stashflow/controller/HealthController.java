package br.com.jonathas.stashflow.controller;

import java.time.Instant;
import java.util.Map;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {
    @GetMapping
    public Map<String, Object> health() {
        return Map.of(
                "status", "UP",
                "application", "StashFlow",
                "timestamp", Instant.now()
        );
    }
}
