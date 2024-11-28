package org.example.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.domain.HR;
import org.example.service.HRService;
import org.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/hr")
public class HRController {
    public static final String SESSION_NAME = "userInfo";

    @Resource
    private HRService hrService;

    @PostMapping("/register")
    public Result<HR> register(@RequestBody @Valid HR hr, BindingResult errors, HttpServletRequest request) {
        Result<HR> result;
        if (hr.getUname().isEmpty() || hr.getPassword().isEmpty()) {
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
        result = hrService.registerService(hr);
        result.setCode("200");
        return result;
    }

    @PostMapping("/login")
    public Result<HR> login(@RequestBody HR hr, HttpServletRequest request) {
        Result<HR> result;
        // 调用登录服务
        result = hrService.loginService(hr);
        // 如果登录成功，则设定session
        if (result.getCode().equals("200")) {
            request.getSession().setAttribute(SESSION_NAME, result.getData());
            System.out.println("session: " + request.getSession().getId());
            System.out.println("session: " + request.getSession().getAttribute(SESSION_NAME));
        }
        return result;
    }

    @GetMapping("/is-login")
    public Result<HR> isLogin(HttpServletRequest request) {
        // 传入session到用户服务层
        return hrService.isLogin(request.getSession());
    }

    @PutMapping("/update")
    public Result<HR> update(@RequestBody HR hr, HttpServletRequest request) throws Exception {
        Result<HR> result = new Result<>();
        HttpSession session = request.getSession();
        // 检查session中的用户（即当前登录用户）是否和当前被修改用户一致
        HR sessionHr = (HR) session.getAttribute(SESSION_NAME);
        if (!Objects.equals(sessionHr.getUname(), hr.getUname())) {
            result.setMsg("当前登录用户和被修改用户不一致，终止！");
            return result;
        }
        result = hrService.update(hr);
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
