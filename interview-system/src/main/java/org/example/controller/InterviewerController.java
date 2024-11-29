package org.example.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.domain.Interviewer;
import org.example.service.InterviewerService;
import org.example.utils.Result;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/hr")
public class InterviewerController {
    public static final String SESSION_NAME = "userInfo";

    @Resource
    private InterviewerService interviewerService;

    @PostMapping("/register")
    public Result<Interviewer> register(@RequestBody @Valid Interviewer interviewer, BindingResult errors, HttpServletRequest request) {
        Result<Interviewer> result;
        if (interviewer.getInterviewId().isEmpty() || interviewer.getPassword().isEmpty()) {
            result = new Result<>();
            result.setMsg("用户名或密码为空！");
            result.setCode("400");
            return result;
        }
        if (errors.hasErrors()) {
            result = new Result<>();
            result.setMsg(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
            return result;
        }
        result = interviewerService.registerService(interviewer);
        result.setCode("200");
        return result;
    }

    @PostMapping("/login")
    public Result<Interviewer> login(@RequestBody Interviewer interviewer, HttpServletRequest request) {
        Result<Interviewer> result;
        // 调用登录服务
        result = interviewerService.loginService(interviewer);
        // 如果登录成功，则设定session
        if (result.getCode().equals("200")) {
            request.getSession().setAttribute(SESSION_NAME, result.getData());
            System.out.println("session: " + request.getSession().getId());
            System.out.println("session: " + request.getSession().getAttribute(SESSION_NAME));
        }
        return result;
    }

    @GetMapping("/is-login")
    public Result<Interviewer> isLogin(HttpServletRequest request) {
        // 传入session到用户服务层
        return interviewerService.isLogin(request.getSession());
    }

    @PutMapping("/update")
    public Result<Interviewer> update(@RequestBody Interviewer interviewer, HttpServletRequest request) throws Exception {
        Result<Interviewer> result = new Result<>();
        HttpSession session = request.getSession();
        // 检查session中的用户（即当前登录用户）是否和当前被修改用户一致
        Interviewer sessionInterviewer = (Interviewer) session.getAttribute(SESSION_NAME);
        if (!Objects.equals(sessionInterviewer.getInterviewId(), interviewer.getInterviewId())) {
            result.setMsg("当前登录用户和被修改用户不一致，终止！");
            return result;
        }
        result = interviewerService.update(interviewer);
        // 修改成功则刷新session信息
        if (Objects.equals(result.getCode(), "200")) {
            session.setAttribute(SESSION_NAME, result.getData());
        }
        return result;
    }

    @GetMapping("/logout")
    public Result<Void> logout(HttpServletRequest request) {
        Result<Void> result = new Result<>();
        request.getSession().setAttribute(SESSION_NAME, null);
        result.setMsg("admin logout");
        return result;
    }
}
