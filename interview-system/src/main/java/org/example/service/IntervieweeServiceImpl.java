package org.example.service;

import jakarta.annotation.Resource;
import org.example.dao.IntervieweeDao;
import org.example.domain.Interviewee;
import org.example.utils.MD5Util;
import org.example.utils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IntervieweeServiceImpl implements IntervieweeService {
    @Resource
    private IntervieweeDao intervieweeDao;

    @Override
    public Result<Interviewee> loginService(Interviewee interviewee) {
        Result<Interviewee> result = new Result<>();
        Interviewee sqlStudent = intervieweeDao.findByIntervieweeId(interviewee.getIntervieweeId());
        if (sqlStudent == null) {
            result.setMsg("该学号不存在！");
            return result;
        }
        String tempPassword = MD5Util.string2MD5(interviewee.getPassword());
        if (!sqlStudent.getPassword().equals(tempPassword)) {
            result.setMsg("密码错误！");
            return result;
        }

        interviewee.setPassword("");
        result.setData(interviewee);
        result.setCode("0");
        result.setMsg("登录成功！");

        return result;
    }

    @Override
    public Result<Interviewee> registerService(Interviewee interviewee) {
        Result<Interviewee> result = new Result<>();
        Interviewee sqlStudent = intervieweeDao.findByIntervieweeId(interviewee.getIntervieweeId());

        if (sqlStudent != null) {
            result.setMsg("该工号已存在！");
            return result;
        }

        interviewee.setPassword(MD5Util.string2MD5(interviewee.getPassword()));
        Interviewee newInterviewee = intervieweeDao.save(interviewee);

        newInterviewee.setPassword("");
        result.setMsg("注册成功！");

        return result;
    }

    @Override
    public Interviewee findByIntervieweeIdService(String workerID) {
        return intervieweeDao.findByIntervieweeId(workerID);
    }

    @Override
    public List<Interviewee> findByNameService(String name) {
        return intervieweeDao.findByName(name);
    }

    @Override
    public List<Interviewee> findALlService() {
        return intervieweeDao.findAll();
    }

    @Override
    public Interviewee updateIntervieweeInfoService(Interviewee interviewee) {
        Interviewee oldInterviewee = intervieweeDao.findByIntervieweeId(interviewee.getIntervieweeId());
        if (oldInterviewee == null){
            return null;
        }
        interviewee.setId(oldInterviewee.getId());

        return intervieweeDao.save(interviewee);
    }

    @Override
    public Interviewee deleteIntervieweeService(Interviewee interviewee) {
        Interviewee oldInterviewee = intervieweeDao.findByIntervieweeId(interviewee.getIntervieweeId());
        if (oldInterviewee == null){
            return null;
        }
        interviewee.setId(oldInterviewee.getId());
        intervieweeDao.delete(interviewee);
        return interviewee;
    }
}
