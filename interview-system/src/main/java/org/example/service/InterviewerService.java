package org.example.service;

import jakarta.servlet.http.HttpSession;
import org.example.domain.Interviewer;
import org.example.utils.Result;
import org.springframework.stereotype.Service;

@Service
public interface InterviewerService {
    Result<Interviewer> loginService(Interviewer interviewer);

    Result<Interviewer> registerService(Interviewer interviewer);

    Result<Interviewer> update(Interviewer interviewer) throws Exception;

    Result<Interviewer> isLogin(HttpSession session);
}
