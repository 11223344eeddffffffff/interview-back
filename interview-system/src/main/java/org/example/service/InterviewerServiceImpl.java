package org.example.service;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.example.dao.InterviewerDao;
import org.example.domain.Interviewer;
import org.example.utils.MD5Util;
import org.example.utils.Result;
import org.springframework.stereotype.Component;

@Component
public class InterviewerServiceImpl implements InterviewerService {

    @Resource
    private InterviewerDao interviewerDao;
    @Override
    public Result<Interviewer> loginService(Interviewer interviewer) {
        Result<Interviewer> result = new Result<>();
        Interviewer sqlInterviewer = interviewerDao.findByName(interviewer.getName());
        if (sqlInterviewer == null) {
            result.setMsg("用户名不存在！");
            return result;
        }
        String tempPassword = MD5Util.string2MD5(interviewer.getPassword());
        if (!sqlInterviewer.getPassword().equals(tempPassword)) {
            result.setMsg("密码错误！");
            return result;
        }

        interviewer.setPassword("");
        result.setData(interviewer);
        result.setCode("200");
        result.setMsg("登录成功！");
        return result;
    }

    @Override
    public Result<Interviewer> registerService(Interviewer interviewer) {
        Result<Interviewer> result = new Result<>();
        Interviewer sqlInterviewer = interviewerDao.findByName(interviewer.getName());

        if (sqlInterviewer != null) {
            result.setMsg("用户名已存在！");
            return result;
        }

        interviewer.setPassword(MD5Util.string2MD5(interviewer.getPassword()));
        Interviewer newInterviewer = interviewerDao.save(interviewer);

        newInterviewer.setPassword("");
        result.setMsg("注册成功！");
        result.setCode("200");
        result.setData(newInterviewer);
        return result;
    }

    @Override
    public Result<Interviewer> update(Interviewer interviewer) throws Exception {
        Result<Interviewer> result = new Result<>();
        Interviewer sqlInterviewer = interviewerDao.findByName(interviewer.getName());
        if (sqlInterviewer == null) {
            result.setMsg("用户不存在！");
            return result;
        }
        interviewer.setPassword(MD5Util.string2MD5(interviewer.getPassword()));

        interviewer.setId(interviewer.getId());
        // 存入数据库
        interviewerDao.save(interviewer);
        result.setMsg("修改用户成功！");
        result.setData(interviewer);
        return result;
    }

    @Override
    public Result<Interviewer> isLogin(HttpSession session) {
        Result<Interviewer> result = new Result<>();
        Interviewer sessionInterviewer = null;
//        HR sessionHR = (HR) session.getAttribute(HRController.SESSION_NAME);
        if (sessionInterviewer == null) {
            result.setMsg("用户未登录！");
            return result;
        }
        Interviewer sqlInterviewer = interviewerDao.findByName(sessionInterviewer.getName());

        if (sqlInterviewer == null || !sqlInterviewer.getPassword().equals(sessionInterviewer.getPassword())) {
            result.setMsg("用户信息无效！");
            return result;
        }
        result.setMsg("用户已登录！");
        return result;
    }
}
