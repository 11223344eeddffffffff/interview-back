package org.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.domain.InterviewEvaluations;
import org.example.mapper.InterviewEvaluationsMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;

@SpringBootTest
class interviewEvaluationsServiceImplMockito {
    @Mock
    private InterviewEvaluationsMapper interviewEvaluationsMapper;

    @InjectMocks
    private interviewEvaluationsServiceImpl interviewEvaluationsService;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGet() {
        InterviewEvaluations interviewEvaluations = new InterviewEvaluations();
        interviewEvaluations.setEvaluationId(1);
        interviewEvaluations.setInterviewId("20241201");
        IPage<InterviewEvaluations> page = interviewEvaluationsService.selectById(1,10,"20241201");
    }
}