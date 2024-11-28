package org.example.service;

import jakarta.annotation.Resource;
import org.example.dao.WorkerDao;
import org.example.domain.Worker;
import org.example.utils.MD5Util;
import org.example.utils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkerServiceImpl implements WorkerService{
    @Resource
    private WorkerDao workerDao;

    @Override
    public Result<Worker> loginService(Worker worker) {
        Result<Worker> result = new Result<>();
        Worker sqlStudent = workerDao.findByWorkerID(worker.getWorkerID());
        if (sqlStudent == null) {
            result.setMsg("该学号不存在！");
            return result;
        }
        String tempPassword = MD5Util.string2MD5(worker.getPassword());
        if (!sqlStudent.getPassword().equals(tempPassword)) {
            result.setMsg("密码错误！");
            return result;
        }

        worker.setPassword("");
        result.setData(worker);
        result.setCode("0");
        result.setMsg("登录成功！");

        return result;
    }

    @Override
    public Result<Worker> registerService(Worker worker) {
        Result<Worker> result = new Result<>();
        Worker sqlStudent = workerDao.findByWorkerID(worker.getWorkerID());

        if (sqlStudent != null) {
            result.setMsg("该工号已存在！");
            return result;
        }

        worker.setPassword(MD5Util.string2MD5(worker.getPassword()));
        Worker newWorker = workerDao.save(worker);

        newWorker.setPassword("");
        result.setMsg("注册成功！");

        return result;
    }

    @Override
    public Worker findByWorkerIDService(String workerID) {
        return workerDao.findByWorkerID(workerID);
    }

    @Override
    public List<Worker> findByNameService(String name) {
        return workerDao.findByName(name);
    }

    @Override
    public List<Worker> findALlService() {
        return workerDao.findAll();
    }

    @Override
    public Worker updateWorkerInfoService(Worker worker) {
        Worker oldWorker = workerDao.findByWorkerID(worker.getWorkerID());
        if (oldWorker == null){
            return null;
        }
        worker.setId(oldWorker.getId());

        return workerDao.save(worker);
    }

    @Override
    public Worker deleteWorkerService(Worker worker) {
        Worker oldWorker = workerDao.findByWorkerID(worker.getWorkerID());
        if (oldWorker == null){
            return null;
        }
        worker.setId(oldWorker.getId());
        workerDao.delete(worker);
        return worker;
    }
}
