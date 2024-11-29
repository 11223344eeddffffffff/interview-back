package org.example.service;

import org.example.domain.Interviewee;
import org.example.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IntervieweeService {
    Result<Interviewee> loginService(Interviewee interviewee);

    Result<Interviewee> registerService(Interviewee interviewee);

    Interviewee findByIntervieweeIdService(String workerID);

    List<Interviewee> findByNameService(String name);

    List<Interviewee> findALlService();

    Interviewee updateIntervieweeInfoService(Interviewee interviewee);

    Interviewee deleteIntervieweeService(Interviewee interviewee);
}
