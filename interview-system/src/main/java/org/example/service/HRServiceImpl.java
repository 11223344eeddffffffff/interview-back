package org.example.service;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.example.dao.HRDao;
import org.example.domain.HR;
import org.example.utils.MD5Util;
import org.example.utils.Result;
import org.springframework.stereotype.Component;

@Component
public class HRServiceImpl implements HRService{

    @Resource
    private HRDao hrDao;
    @Override
    public Result<HR> loginService(HR hr) {
        Result<HR> result = new Result<>();
        HR sqlHR = hrDao.findByUname(hr.getUname());
        if (sqlHR == null) {
            result.setMsg("用户名不存在！");
            return result;
        }
        String tempPassword = MD5Util.string2MD5(hr.getPassword());
        if (!sqlHR.getPassword().equals(tempPassword)) {
            result.setMsg("密码错误！");
            return result;
        }

        hr.setPassword("");
        result.setData(hr);
        result.setCode("200");
        result.setMsg("登录成功！");
        return result;
    }

    @Override
    public Result<HR> registerService(HR hr) {
        Result<HR> result = new Result<>();
        HR sqlHR = hrDao.findByUname(hr.getUname());

        if (sqlHR != null) {
            result.setMsg("用户名已存在！");
            return result;
        }

        hr.setPassword(MD5Util.string2MD5(hr.getPassword()));
        HR newHR = hrDao.save(hr);

        newHR.setPassword("");
        result.setMsg("注册成功！");
        result.setCode("200");
        result.setData(newHR);
        return result;
    }

    @Override
    public Result<HR> update(HR hr) throws Exception {
        Result<HR> result = new Result<>();
        HR sqlHR = hrDao.findByUname(hr.getUname());
        if (sqlHR == null) {
            result.setMsg("用户不存在！");
            return result;
        }
        hr.setPassword(MD5Util.string2MD5(hr.getPassword()));

        hr.setUid(hr.getUid());
        // 存入数据库
        hrDao.save(hr);
        result.setMsg("修改用户成功！");
        result.setData(hr);
        return result;
    }

    @Override
    public Result<HR> isLogin(HttpSession session) {
        Result<HR> result = new Result<>();
        HR sessionHR = null;
//        HR sessionHR = (HR) session.getAttribute(HRController.SESSION_NAME);
        if (sessionHR == null) {
            result.setMsg("用户未登录！");
            return result;
        }
        HR sqlHR = hrDao.findByUname(sessionHR.getUname());

        if (sqlHR == null || !sqlHR.getPassword().equals(sessionHR.getPassword())) {
            result.setMsg("用户信息无效！");
            return result;
        }
        result.setMsg("用户已登录！");
        return result;
    }
}
