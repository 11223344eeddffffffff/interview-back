package org.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "example-service", fallback = ExampleFeignFallback.class)
public interface ExampleFeignClient {
    
    @GetMapping("/api/example")
    @CircuitBreaker(name = "default")
    String getExample();
} 