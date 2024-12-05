package org.example.feign;

import org.springframework.stereotype.Component;

@Component
public class ExampleFeignFallback implements ExampleFeignClient {
    
    @Override
    public String getExample() {
        return "服务调用失败，这是降级响应";
    }
} 