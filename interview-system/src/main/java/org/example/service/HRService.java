package org.example.service;

import jakarta.servlet.http.HttpSession;
import org.example.domain.HR;
import org.example.utils.Result;
import org.springframework.stereotype.Service;

@Service
public interface HRService {
    Result<HR> loginService(HR hr);

    Result<HR> registerService(HR hr);

    Result<HR> update(HR hr) throws Exception;

    Result<HR> isLogin(HttpSession session);
}
